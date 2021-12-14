package com.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：machunyu
 * @date ：Created in 2021/12/9
 */
public class ExcelTest {

    public static void main(String[] args) {
        String templateName = "C:\\Users\\Administrator\\Desktop\\admin_excel_template.xlsx";
        String fileName = "C:\\Users\\Administrator\\Desktop\\admin_excel_template111.xlsx";

        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("首次使用日期1111", 123456);
        stringObjectHashMap.put("销售链接1111", "销售链接");

        Map<Integer, Map<String, Object>> fillData = new HashMap<>();
        fillData.put(0, stringObjectHashMap);

        fillExcel(templateName, fileName, fillData);

    }

    /**
     * 填充excel模板
     *
     * @param templateName 模板名称（全路径）
     * @param fileName     输出文件名称（全路径）
     * @param fillData     填充的数据，第一层map：sheet -> map，第二层map：待填充字段 -> 值
     */
    public static void fillExcel(String templateName, String fileName, Map<Integer, Map<String, Object>> fillData) {
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateName).build();
//        ExcelReader excelReader = EasyExcel.read(templateName).build();
//        ReadSheet build = EasyExcel.readSheet(0).build();
//        ExcelReader read = excelReader.read(build);

        ExcelReaderSheetBuilder excelReaderSheetBuilder = EasyExcel.read(templateName).sheet();
        // 返回每条数据的键值对
        List<Map<Integer, String>> listMap = excelReaderSheetBuilder.doReadSync();

        try {
            fillData.forEach((sheet, map) -> {
                WriteSheet writeSheet = EasyExcel.writerSheet(sheet).build();
//                ReadSheet build = EasyExcel.readSheet(sheet).build();
                excelWriter.fill(map, writeSheet);
            });
        } finally {
            excelWriter.finish();
        }
    }

}
