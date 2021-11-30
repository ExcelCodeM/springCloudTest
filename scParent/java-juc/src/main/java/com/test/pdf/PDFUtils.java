package com.test.pdf;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import org.springframework.util.CollectionUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PDFUtils {

    public static void genPdf(Map map, Map imageData, String sourceFile, String targetFile) {
        InputStream inputStream = null;
        ByteArrayOutputStream output = null;
        byte[] buffer = new byte[0];
        try {
            if (HttpUtil.isHttp(sourceFile) || HttpUtil.isHttps(sourceFile)) {
                HttpUtil.downloadBytes(sourceFile);
                inputStream = PDFUtils.getInputStreamByHttpUrl(sourceFile);
            } else {
                inputStream = new FileInputStream(sourceFile);
            }
            output = new ByteArrayOutputStream();
            buffer = new byte[inputStream.available()];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            fillPdfParam(map, imageData, output.toByteArray(), targetFile);
            output.flush();
        } catch (IOException e) {
//            log.error("数据填充pdf异常！{}", e.getMessage());
        } finally {
            IoUtil.flush(output);
            IoUtil.close(output);
            IoUtil.close(inputStream);
        }
    }

    /**
     * pdf填充（设置字体）
     */
    public static void fillPdfParam(Map<String, String> fieldValueMap, Map<String, String> imageData, byte[] file, String contractFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(contractFileName);
            PdfReader reader = null;
            PdfStamper stamper = null;
            BaseFont base = null;
            try {
                reader = new PdfReader(file);
                stamper = new PdfStamper(reader, fos);
                fos.flush();
                base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                AcroFields acroFields = stamper.getAcroFields();
                for (String key : acroFields.getFields().keySet()) {
                    acroFields.setFieldProperty(key, "textfont", base, null);
                    acroFields.setFieldProperty(key, "textsize", Float.valueOf("8"), null);
                }
                if (fieldValueMap != null) {
                    for (String fieldName : fieldValueMap.keySet()) {
                        acroFields.setField(fieldName, String.valueOf(fieldValueMap.get(fieldName)));
                    }
                }
                insertImage(stamper, acroFields, imageData);
                stamper.setFormFlattening(false);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stamper != null) {
                    try {
                        stamper.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    reader.close();
                }
            }

        } catch (Exception e) {
            System.out.println("填充参数异常");
            e.printStackTrace();
        } finally {
            //fulshOutputStrean(fos);
            //closeOutputStrean(fos);
            IoUtil.flush(fos);
            IoUtil.close(fos);

        }
    }

    /**
     * PDF插入图片
     */
    public static void insertImage(PdfStamper ps, AcroFields s, Map<String, String> imageData) {
        if (CollectionUtils.isEmpty(imageData)) {
            return;
        }
        try {

            for (Map.Entry<String, String> entry : imageData.entrySet()) {
                String fieldKey = entry.getKey();
                String fieldValue = entry.getValue();
                if (s.getField(fieldKey) != null) {
                    PushbuttonField newPushbuttonFromField = s.getNewPushbuttonFromField(fieldKey);
                    newPushbuttonFromField.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
                    newPushbuttonFromField.setProportionalIcon(true);
                    newPushbuttonFromField.setImage(Image.getInstance(fieldValue));
                    s.replacePushbuttonField(fieldKey, newPushbuttonFromField.getField());
                }
            }


//            List<AcroFields.FieldPosition> list = s.getFieldPositions(fieldKey);
//            for (AcroFields.FieldPosition fieldPosition : list) {
////                Rectangle signRect = fieldPosition.position;
////                is = PDFUtils.getInputStreamByHttpUrl(fieldValue);
//                Image image = Image.getInstance(IoUtil.readBytes(new FileInputStream(fieldValue)));
////                PdfContentByte under = ps.getOverContent(fieldPosition.page);
////                float x = signRect.getLeft();
////                float y = signRect.getBottom();
//////                float y = signRect.getBottom() - (signRect.getHeight() / 2);
////                image.setAbsolutePosition(x, y);
//
//                PushbuttonField newPushbuttonFromField = s.getNewPushbuttonFromField(fieldKey);
//                newPushbuttonFromField.setLayout(PushbuttonField.LAYOUT_ICON_ONLY);
//                newPushbuttonFromField.setProportionalIcon(true);
//                newPushbuttonFromField.setImage(Image.getInstance(fieldValue));
//
//                s.replacePushbuttonField(fieldKey, newPushbuttonFromField.getField());
//
////                image.scaleToFit(signRect.getWidth() * 2f, signRect.getHeight() * 2f);
////                under.addImage(image);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    /**
     * http请求远程资源
     *
     * @param url
     * @return 返回byte 数组
     */
    public static InputStream getInputStreamByHttpUrl(String url) {
        URL urlfile = null;
        HttpURLConnection connection = null;
        BufferedInputStream bis = null;
        try {
            urlfile = new URL(url);
            connection = (HttpURLConnection) urlfile.openConnection();
            connection.connect();
            bis = new BufferedInputStream(connection.getInputStream());
        } catch (Exception e) {
//            log.info(e.getMessage());
        }
        return bis;
    }

    /**
     * 图片生成pdf
     *
     * @param source
     * @param target
     */
    public static void convert(String source, String target) {
        Document document = new Document();
        // 设置文档页边距
        document.setMargins(0, 0, 0, 0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            PdfWriter.getInstance(document, fos);
            // 打开文档
            document.open();
            // 获取图片的宽高
            Image image = Image.getInstance(source);
            float imageHeight = image.getScaledHeight();
            float imageWidth = image.getScaledWidth();
            // 设置页面宽高与图片一致
            Rectangle rectangle = new Rectangle(imageWidth, imageHeight);
            document.setPageSize(rectangle);
            // 图片居中
            image.setAlignment(Image.ALIGN_CENTER);
            // 新建一页添加图片
            document.newPage();
            document.add(image);
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            // 关闭文档
            document.close();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 图片生成pdf
     *
     * @param sources
     * @param target
     */
    public static void convert(List<String> sources, String target) {
        Document document = new Document();
        // 设置文档页边距
        document.setMargins(0, 0, 0, 0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(target);
            PdfWriter.getInstance(document, fos);
            // 打开文档
            document.open();
            // 获取图片的宽高
            for (String source : sources) {
                Image image = Image.getInstance(source);
                // 设置页面大小
                Rectangle rectangle = new Rectangle(794, 1122);
                document.setPageSize(rectangle);
                if (image.getScaledWidth() > 794) {
                    image.scaleAbsolute(794, (794 / image.getScaledWidth()) * 1122);
                }
                // 图片居中
                image.setAlignment(Image.ALIGN_CENTER);
                // 新建一页添加图片
                document.newPage();
                document.add(image);
            }
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            // 关闭文档
            document.close();
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
