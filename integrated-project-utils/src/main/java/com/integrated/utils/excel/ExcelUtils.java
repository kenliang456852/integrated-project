package com.integrated.utils.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

/**
 * ClassName: ExcelUtils
 * Description:
 * Author: liangchao
 * Date: 2018/4/12 16:26
 * History: Created by dongbb on 2017/03/17.
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class ExcelUtils<T> {

    private String fileName; //下载文件名
    private String sourceFileName; //没有转码的文件名

    public ExcelUtils(String fileName, HttpServletRequest request) {

        this.sourceFileName = fileName;
        //防止下载是中文文件名出现乱码
        String agent = request.getHeader("USER-AGENT");
        try {
            if (null != agent && -1 != agent.indexOf("MSIE")) {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else {
                fileName = new String(fileName.getBytes(), "ISO-8859-1");
            }
        } catch (Exception e) {
            fileName = "datas";
        }
        this.fileName = fileName;
    }

    /**
     * 根据传递参数下载excel文件
     *
     * @param datas    数据集合
     * @param c        数据集合中的entity对应的Class
     * @param titles   字段和对应的名称
     * @param exp      表达式
     * @param response HttpServletResponse
     * @return
     */
    public void toExcel(List<T> datas, Class<T> c, LinkedHashMap<String, String> titles,HttpServletResponse response, Map<String, Map<Object, Object>> exp ) {

        if(datas == null || datas.isEmpty()){
            throw new RuntimeException("数据集合为空！");
        }
        if (titles == null || titles.isEmpty()) {
            throw new RuntimeException("字段表头对应字段为空");
        }
        //*************创建excel文件*************************
        HSSFWorkbook workbook = new HSSFWorkbook(); //创建excel文件
        HSSFSheet sheet = workbook.createSheet("数据列表"); //创建工作表，并设置名称
        Map<String, HSSFCellStyle> styleMap = initStyle(workbook); //初始化样式

        //*************缓存反射方法名，提高性能*************
        List<Method> methodCache = new LinkedList<>(); //缓存反射方法集合
        Map<Integer, Map<Object, Object>> expCache = new HashMap<>(); //缓存判断表达式

        String[] keys = titles.keySet().toArray(new String[]{}); //获取传递的字段
        for (int i = 0, length = keys.length; i < length; i++) {
            try {

                Method method = c.getMethod(getMethodName(keys[i]));
                //缓存方法
                methodCache.add(method);
                //缓存判断表达式
                if (exp !=null && exp.get(keys[i]) != null) {
                    expCache.put(i, exp.get(keys[i]));
                }

                HSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.cloneStyleFrom(styleMap.get("data"));//数据默认样式
                //设置列的数据格式化
                String valueFormat = getDataFormat(method.getReturnType());
                if (StringUtils.isNotBlank(valueFormat)) {
                    HSSFDataFormat dataFormat = workbook.createDataFormat();//创建数据格式化
                    cellStyle.setDataFormat(dataFormat.getFormat(valueFormat));
                }
                sheet.setDefaultColumnStyle(i, cellStyle);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 2);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("字段名不存在！");
            }
        }

        //第一行表格名称
        HSSFRow titleRow = sheet.createRow(0);//创建第一行（0表示第一行）
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(sourceFileName);
        titleCell.setCellStyle(styleMap.get("title"));
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, keys.length - 1);
        sheet.addMergedRegion(range);//合并单元格

        //*************创建excel表头*************************
        HSSFRow headerRow = sheet.createRow(1);//创建行（0表示第一行）
        String[] values = titles.values().toArray(new String[]{}); //获取传递的表头名称
        for (int i = 0, length = values.length; i < length; i++) {
            HSSFCell cell = headerRow.createCell(i);//创建单元格（0表示第一列）
            cell.setCellValue(values[i]); //设置表头名称
            cell.setCellStyle(styleMap.get("header"));//重新设置表头样式
        }

        //**************填充excel数据*****************
        for (int i = 0, size = datas.size(); i < size; i++) {
            HSSFRow dataRow = sheet.createRow(i + 2);//从第三行开始
            for (int j = 0, length = methodCache.size(); j < length; j++) {
                HSSFCell dataCell = dataRow.createCell(j);
                try {
                    cellValue(dataCell, methodCache.get(j).invoke(datas.get(i)), expCache.get(j));
                } catch (Exception e) {
                    throw new RuntimeException("获取数据错误！");
                }
            }
        }

        try {
            String contentType = "application/vnd.ms-excel";//定义导出文件的格式的字符串
            response.setHeader("Content-disposition", "attachment;filename=" + this.fileName + ".xls");
            response.setContentType(contentType);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("写入文件错误！");
        }
    }

    /**
     * 返回字段的get方法名，列如：name 返回 getName
     *
     * @param filed 字段名
     * @return 字段的get方法名
     */
    private String getMethodName(String filed) {
        return "get" + StringUtils.capitalize(filed);
    }


    /**
     * 为单元格设置值
     *
     * @param cell  单元格对象
     * @param value 通过反射获取的值
     * @param exp   与key比较，如果相等，设置对应value的值
     */
    private void cellValue(HSSFCell cell, Object value, Map<Object, Object> exp) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        //自定义类型转换
        if (exp != null && exp.size() > 0) {
            Iterator<Object> it = exp.keySet().iterator();
            while (it.hasNext()) {
                Object column = it.next();
                if (String.valueOf(value).equals(String.valueOf(column))) {
                    value = exp.get(column);
                    break;
                }
            }
        }

        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else if (value instanceof Byte) {
            cell.setCellValue((Byte) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        }

    }

    /**
     * 返回格式化字符串
     *
     * @param c 根据Class判断所属的类型
     * @return 返回该类型格式化字符串
     */
    private String getDataFormat(Class<?> c) {
        String dataFormat = "";
        if (c.isAssignableFrom(Double.class) || c.isAssignableFrom(double.class)) {
            dataFormat = "0.00";
        } else if (c.isAssignableFrom(Float.class) || c.isAssignableFrom(float.class)) {
            dataFormat = "0.00";
        } else if (c.isAssignableFrom(Date.class)) {
            dataFormat = "yyyy-MM-dd HH:mm:ss";
        }
        return dataFormat;
    }

    /**
     * 初始化样式
     *
     * @param workbook
     * @return
     */
    private Map<String, HSSFCellStyle> initStyle(HSSFWorkbook workbook) {
        Map<String, HSSFCellStyle> styleMap = new HashMap<>();

        //设置数据样式
        HSSFCellStyle style = workbook.createCellStyle();
        //**************设置表格边框颜色 start***************
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        //**************设置表格边框颜色 end******************
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        styleMap.put("data", style);

        //设置表头样式
        style = workbook.createCellStyle();
        style.cloneStyleFrom(styleMap.get("data"));//复制样式表
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());//设置背景色
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setFontName("Arial"); //  设置字体样式
        font.setFontHeightInPoints((short) 12); //设置字体大小
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); //设置粗体
        font.setColor(IndexedColors.WHITE.getIndex());//设置文字颜色
        style.setFont(font);
        styleMap.put("header", style);

        //设置标题样式
        style = workbook.createCellStyle();
        style.cloneStyleFrom(styleMap.get("data"));
        style.setFont(font);
        font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        styleMap.put("title", style);

        return styleMap;
    }


}

