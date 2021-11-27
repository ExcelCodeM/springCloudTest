package com.test.pdf;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
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

    public static void genPdf(Map map, String sourceFile, String targetFile) {
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
            fillParam(map, output.toByteArray(), targetFile);
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
    public static void fillParam(Map<String, String> fieldValueMap, byte[] file, String contractFileName) {
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
                if (!CollectionUtils.isEmpty(acroFields.getFieldPositions("companyLegalPersonSign_af_image")) && fieldValueMap.containsKey("companyLegalPersonSign")) {
                    insertImage(stamper, acroFields, fieldValueMap.get("companyLegalPersonSign"), "companyLegalPersonSign_af_image");
                }
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
    public static void insertImage(PdfStamper ps, AcroFields s, String fieldValue, String fieldKey) {
//        if (StringUtil.isBlank(fieldValue)) {
//            return;
//        }
        InputStream is = null;
        try {
            List<AcroFields.FieldPosition> list = s.getFieldPositions(fieldKey);
            for (AcroFields.FieldPosition fieldPosition : list) {
                Rectangle signRect = fieldPosition.position;
                is = PDFUtils.getInputStreamByHttpUrl(fieldValue);
                Image image = Image.getInstance(IoUtil.readBytes(is));
                PdfContentByte under = ps.getOverContent(fieldPosition.page);
                float x = signRect.getLeft();
                float y = signRect.getBottom() - (signRect.getHeight() / 2);
                image.setAbsolutePosition(x, y);
                image.scaleToFit(signRect.getWidth() * 2f, signRect.getHeight() * 2f);
                under.addImage(image);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IoUtil.close(is);
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


}
