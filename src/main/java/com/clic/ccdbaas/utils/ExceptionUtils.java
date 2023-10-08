package com.clic.ccdbaas.utils;

public class ExceptionUtils {
    public static String parseExceptionInfo(Exception e) {
        switch (e.getClass().getSimpleName()) {
            case "DuplicateKeyException":
                return "数据重复，请检查后提交";
            case "DataIntegrityViolationException":
                return "数据完整性异常，请检查后提交";
            case "NullPointerException":
                return "空指针异常，请检查后提交";
            case "NumberFormatException":
                return "数字格式异常，请检查后提交";
            case "ArrayIndexOutOfBoundsException":
                return "数组越界异常，请检查后提交";
            case "ClassCastException":
                return "类型转换异常，请检查后提交";
            case "SecurityException":
                return "安全异常，请检查后提交";
            case "SQLException":
                return "数据库异常，请检查后提交";
            case "IOException":
                return "IO异常，请检查后提交";
            case "NoSuchMethodException":
                return "方法未找到异常，请检查后提交";
            case "NoSuchFieldException":
                return "字段未找到异常，请检查后提交";
            case "ArithmeticException":
                return "算术异常，请检查后提交";
            case "RuntimeException":
                return "运行时异常，请检查后提交";
            case "Exception":
                return "异常，请检查后提交";
            default:
                return "未知异常，请检查后提交";
        }
    }
}
