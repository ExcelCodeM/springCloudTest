package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:03
 * @description：
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = -5382998642959337134L;
    private Long id;
    private String serial;
}
