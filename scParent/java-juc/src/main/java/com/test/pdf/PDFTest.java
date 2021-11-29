package com.test.pdf;

import java.util.HashMap;
import java.util.Map;

public class PDFTest {

    public static void main(String[] args) {

        Map<String, String> fillData = new HashMap<>();
        fillData.put("box1", "on");
        fillData.put("box2", "on");
        fillData.put("text", "hello world");

        Map<String, String> imageData = new HashMap<>();
        imageData.put("hello_image", "C:\\Users\\Administrator\\Desktop\\erp_application_doc.jpg");


//        PDFUtils.genPdf(fillData, imageData, "C:\\Users\\Administrator\\Desktop\\ped.pdf", "C:\\Users\\Administrator\\Desktop\\Anlage_test.pdf");

        PDFUtils.convert("C:\\Users\\Administrator\\Desktop\\erp_application_doc.jpg", "C:\\Users\\Administrator\\Desktop\\hello.pdf");

    }

}
