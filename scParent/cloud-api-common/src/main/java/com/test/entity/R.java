package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:34
 * @description：
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -618015788902548443L;
    private Integer code;
    private String msg;
    private T data;

    public R(int code, String msg){
        this(code,msg,null);
    }

}
