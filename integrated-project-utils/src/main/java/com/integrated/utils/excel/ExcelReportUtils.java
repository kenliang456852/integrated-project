package com.integrated.utils.excel;

import com.integrated.utils.common.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * ClassName: ExcelReportUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/14 16:01
 * History: Copy from wangweijuan
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class ExcelReportUtils {

    /**
     * 导出Excel到HttpServletResponse的响应流中
     *
     * @param title    表格Sheet页名
     * @param rowName  行名列表
     * @param dataList 数据集合
     * @param response HttpServletResponse响应对象
     * @throws Exception
     */
    public static void export(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response) throws Exception {
        try {
            // 创建工作簿对象  03版excel
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建工作表
            HSSFSheet sheet = workbook.createSheet(title);

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            // 获取列头样式对象
            HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
            // 单元格样式对象
            HSSFCellStyle style = getStyle(workbook);
            // 合并单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));

            //sheet.addMergedRegion(new CellRangeAddress(2, 5, 0, 0));

            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            // 在索引2的位置创建行(最顶端的行开始的第二行)
            HSSFRow rowRowName = sheet.createRow(2);

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                // 创建列头对应个数的单元格
                HSSFCell cellRowName = rowRowName.createCell(n);
                // 设置列头单元格的数据类型
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                // 设置列头单元格的值
                cellRowName.setCellValue(text);
                // 设置列头单元格样式
                cellRowName.setCellStyle(columnTopStyle);
            }

            //将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {
                // 遍历每个对象
                Object[] obj = dataList.get(i);
                // 创建所需的行数
                HSSFRow row = sheet.createRow(i + 3);

                for (int j = 0; j < obj.length; j++) {
                    // 设置单元格的数据类型
                    HSSFCell cell = null;
                    cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        // 设置单元格的值
                        cell.setCellValue(obj[j].toString());
                    } else {
                        cell.setCellValue("");
                    }
                    //                    }
                    // 设置单元格样式
                    cell.setCellStyle(style);
                }
            }
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = 0;
                            if (null != currentCell.getStringCellValue() && !"".equals(currentCell.getStringCellValue())) {
                                length = currentCell.getStringCellValue().getBytes().length;
                            }

                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            if (workbook != null) {
                try {
                    String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
                    String headStr = "attachment; filename=\"" + fileName + "\"";
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.setHeader("Content-Disposition", headStr);
                    OutputStream out = response.getOutputStream();
                    workbook.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 列头单元格样式
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * 列数据信息单元格样式
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }


    /**
     * 集合竞价中导出
     * @param workbook
     * @param title 第一行标题
     * @param sheetName sheet页名
     * @param headers 标题列表
     * @param dataList 数据列表
     * @return
     */
    public static HSSFWorkbook exportOfCallAuction(HSSFWorkbook workbook, String title, String sheetName, String[] headers, List<Object[]> dataList) {
        workbook = workbook == null ? new HSSFWorkbook() : workbook;
        sheetName = sheetName == null ? "Sheet1" : sheetName;
        // 创建Sheet页
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // 获取列头样式对象
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
        // 单元格样式对象
        HSSFCellStyle style = getStyle(workbook);

        int intervalRow = 0;
        // 创建标题
        if (StringUtils.isNotEmpty(title)) {
            HSSFRow titleRow = sheet.createRow(0);
            HSSFCell titleCell = titleRow.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, headers.length - 1));
            titleCell.setCellStyle(columnTopStyle);
            titleCell.setCellValue(title);
            intervalRow = intervalRow + 2;
        }

        // 创建表头
        HSSFRow headerRow = sheet.createRow(intervalRow);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = headerRow.createCell(i, HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(columnTopStyle);
            cell.setCellValue(new HSSFRichTextString(headers[i]));
            // 调整列宽
            sheet.autoSizeColumn(i);
        }

        // 创建数据内容
        for (int i = 0; i < dataList.size(); i++) {
            HSSFRow dataRow = sheet.createRow(i + 3);
            Object[] datas = dataList.get(i);
            for (int j = 0; j < datas.length; j++) {
                Object data = datas[j];
                HSSFCell dataCell = dataRow.createCell(j, HSSFCell.CELL_TYPE_STRING);
                dataCell.setCellValue(new HSSFRichTextString(data == null ? "" : data.toString()));
                dataCell.setCellStyle(style);
            }
        }

        return workbook;
    }
}

