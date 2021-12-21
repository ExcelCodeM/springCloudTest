package com.test.facade;

import java.io.File;

/**
 * 外观模式，其实是对一个复杂的子系统进行封装，提供一个简单的接口。从而忽略子系统内部复杂的逻辑。
 */

public class Demo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        // ...
    }
}