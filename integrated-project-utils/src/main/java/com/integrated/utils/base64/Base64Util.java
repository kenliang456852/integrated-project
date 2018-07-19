package com.integrated.utils.base64;

import java.io.*;

/**
 * ClassName: Base64Util
 * Description:
 * Author: liangchao
 * Date: 2018/4/12 15:32
 * History:
 * <author>          <time>          <version>          <desc>
 * liangc           修改时间           0.0.1              描述
 */
public class Base64Util {
    public Base64Util() {
    }

    public static String encodeByteToStr(byte[] bytes) throws RuntimeException {
        try {
            return new String(encode(bytes), "ASCII");
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("ASCII is not supported!", var2);
        }
    }

    public static byte[] decodeStrToByte(String str) throws RuntimeException {
        try {
            byte[] bytes = str.getBytes("ASCII");
            return decode(bytes);
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("ASCII is not supported!", var2);
        }
    }

    public static String encode(String str) throws RuntimeException {
        byte[] bytes = str.getBytes();
        byte[] encoded = encode(bytes);

        try {
            return new String(encoded, "ASCII");
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException("ASCII is not supported!", var4);
        }
    }

    public static String decode(String str) throws RuntimeException {
        try {
            byte[] bytes = str.getBytes("ASCII");
            return new String(decode(bytes));
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("ASCII is not supported!", var2);
        }
    }

    public static byte[] encode(byte[] bytes) throws RuntimeException {
        return encode(bytes, 0);
    }

    public static byte[] encode(byte[] bytes, int wrapAt) throws RuntimeException {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        byte[] var4;
        try {
            inputStream = new ByteArrayInputStream(bytes);
            outputStream = new ByteArrayOutputStream();
            encode((InputStream)inputStream, (OutputStream)outputStream, wrapAt);
            var4 = outputStream.toByteArray();
        } catch (IOException var8) {
            throw new RuntimeException("Unexpected I/O error", var8);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

        return var4;
    }

    public static byte[] decode(byte[] bytes) throws RuntimeException {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        byte[] var3;
        try {
            inputStream = new ByteArrayInputStream(bytes);
            outputStream = new ByteArrayOutputStream();
            decode((InputStream)inputStream, (OutputStream)outputStream);
            var3 = outputStream.toByteArray();
        } catch (IOException var7) {
            throw new RuntimeException("Unexpected I/O error", var7);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

        return var3;
    }

    public static void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        encode((InputStream)inputStream, (OutputStream)outputStream, 0);
    }

    public static void encode(InputStream inputStream, OutputStream outputStream, int wrapAt) throws IOException {
        Base64OutputStream aux = new Base64OutputStream(outputStream, wrapAt);
        copy(inputStream, aux);
        aux.commit();
    }

    public static void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        copy(new Base64InputStream(inputStream), outputStream);
    }

    public static void encode(File source, File target, int wrapAt) throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            encode((InputStream)inputStream, (OutputStream)outputStream, wrapAt);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

    }

    public static void encode(File source, File target) throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            encode((InputStream)inputStream, (OutputStream)outputStream);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

    }

    public static void decode(File source, File target) throws IOException {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            decode((InputStream)inputStream, (OutputStream)outputStream);
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

    }

    private static void copy(InputStream inp, OutputStream out) throws IOException {
        byte[] buff = new byte[4096];

        int count;
        while((count = inp.read(buff)) != -1) {
            if (count > 0) {
                out.write(buff, 0, count);
            }
        }

    }

    protected static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception var2) {
            ;
        }

    }
}
