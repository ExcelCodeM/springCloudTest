package com.test.decorator;

public interface DataSource {
    void writeData(String data);

    String readData();
}