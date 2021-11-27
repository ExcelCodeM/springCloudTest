package com.test.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class WordTest {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Administrator\\Desktop\\Dichiara - itax.docx";
        OutputStream os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\hehe.docx");

        HashMap<String, Object> stringStringHashMap = new HashMap<String, Object>() {{
            put("${name}", "zhangsan");
            put("${table1}", "table1");
            put("${table2}", "table2");
            put("${hehe.image}", "C:\\Users\\Administrator\\Desktop\\erp_application_doc.jpg");
        }};

        try (FileInputStream is = new FileInputStream(path);
             XWPFDocument document = new XWPFDocument(is)) {
            WordUtils.changeText(document, stringStringHashMap);
            document.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
