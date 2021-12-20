package com.test.decorator;

/**
 * 装饰者模式
 * <p>
 * CompressionDecorator 装饰了 EncryptionDecorator， EncryptionDecorator 装饰了 FileDataSource
 * <p>
 * java实现 java.io.InputStream  java.io.OutputStream
 */
public class Demo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("C:\\Users\\Administrator\\Desktop\\OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("C:\\Users\\Administrator\\Desktop\\OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}