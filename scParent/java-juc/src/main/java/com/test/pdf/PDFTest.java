package com.test.pdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        List<String> strings = new ArrayList<>();
        strings.add("C:\\Users\\Administrator\\Desktop\\erp_application_doc.jpg");
        strings.add("C:\\Users\\Administrator\\Desktop\\erp文档.jpg");
        strings.add("C:\\Users\\Administrator\\Desktop\\1e228cd1-6e26-45b2-be09-ac70f48c19ab__39831a96eb3294faac4c947c55b04e49.png");
//        strings.add("https://yuzhi--edu.oss-cn-beijing.aliyuncs.com/cover/cover-default.jpg");
//        strings.add("https://yuzhi--edu.oss-cn-beijing.aliyuncs.com/avatar/avatar-boy.gif");

        PDFUtils.convert(strings, "C:\\Users\\Administrator\\Desktop\\hello.pdf");

    }

}
