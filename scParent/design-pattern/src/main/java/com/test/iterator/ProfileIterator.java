package com.test.iterator;

/**
 * @author ：machunyu
 * @date ：Created in 2022/1/4
 * <p>
 * 社交档案迭代器接口
 */
public interface ProfileIterator {

    Boolean hasNext();

    Profile getNext();

    void reset();
}
