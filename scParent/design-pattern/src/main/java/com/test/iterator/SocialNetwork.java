package com.test.iterator;

/**
 * @author ：machunyu
 * @date ：Created in 2022/1/4
 * <p>
 * 通用的社交网络接口
 */
public interface SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);

}
