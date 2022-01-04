package com.test.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：machunyu
 * @date ：Created in 2022/1/4
 * <p>
 * 脸书
 */
public class Facebook implements SocialNetwork {

    private List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        if (cache != null) {
            this.profiles = cache;
        } else {
            this.profiles = new ArrayList<>();
        }

    }

    public Profile requestProfileFromFacebook(String profileEmail) {
        simulateNetworkLatency();
        System.out.println("Facebook: Loading profile '" + profileEmail + "' over the network...");
        return findProfile(profileEmail);
    }


    /**
     * 当前用户，社交联系人，邮箱集合
     *
     * @param profileEmail 当前用户邮箱
     * @param contactType  获取联系人类型
     * @return 邮箱集合
     */
    public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
        simulateNetworkLatency();
        System.out.println("Facebook: Loading '" + contactType + "' list of '" + profileEmail + "' over the network...");
        Profile profile = findProfile(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : profiles) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }

    /**
     * Here would be a POST request to one of the Facebook API endpoints.
     * Instead, we emulates long network connection, which you would expect
     * in the real life...
     * <p>
     * 模拟网络请求延迟
     */
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }
}
