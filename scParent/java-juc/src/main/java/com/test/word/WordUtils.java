package com.test.word;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.util.CollectionUtils;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordUtils {

    public static void changeText(XWPFDocument document, Map<String, Object> textMap) throws Exception {
        // 获取段落集合
        // 返回包含页眉或页脚文本的段落
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        //表格处理
        List<XWPFTable> tables = document.getTables();
        if (!CollectionUtils.isEmpty(tables)) {
            for (XWPFTable table : tables) {    //表格
                for (XWPFTableRow row : table.getRows()) {  //行
                    for (XWPFTableCell tableCell : row.getTableCells()) {   //列
                        String text = tableCell.getText();
                        if (checkText(text)) {
                            // 替换模板原来位置
                            Object ob = textMap.get(text);
                            if (ob != null) {
                                tableCell.removeParagraph(0);
                                tableCell.setText((String) ob);
                            }
                        }
                    }
                }
            }
        }

        //通用字段处理
        // 增强型for循环语句，前面一个为声明语句，后一个为表达式
        for (XWPFParagraph paragraph : paragraphs) {
            // 判断此段落是否需要替换
            String text = paragraph.getText();// 检索文档中的所有文本
            if (checkText(text)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    // 替换模板原来位置
                    Object ob = textMap.get(run.toString());
                    if (ob != null) {
                        if (run.toString().contains("image")) {
                            run.setText("", 0);
                            try (FileInputStream is = new FileInputStream((String) ob)) {
                                run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, (String) ob, Units.toEMU(80),
                                        Units.toEMU(50));
                            }
                        } else {
                            run.setText((String) ob, 0);
                        }
                    }
                }
            }
        }

    }

    /* 检查文本中是否包含指定的字符(此处为“$”)，并返回值 */
    public static boolean checkText(String text) {
        return text.contains("$");
    }

    public static Object changeValue(String value, Map<String, Object> textMap) {
        Object o = textMap.get(value);
        Set<Map.Entry<String, Object>> textSets = textMap.entrySet();
        Object valu = "";
        for (Map.Entry<String, Object> textSet : textSets) {
            // 匹配模板与替换值 格式${key}
            String key = textSet.getKey();
            if (value.contains(key)) {
                valu = textSet.getValue();
            }
        }
        return valu;
    }
}
