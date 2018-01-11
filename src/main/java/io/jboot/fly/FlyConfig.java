package io.jboot.fly;

import io.jboot.Jboot;
import io.jboot.config.annotation.PropertyConfig;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jboot.fly
 */
@PropertyConfig(prefix = "jboot.fly")
public class FlyConfig {

    private String encryptKey;

    /**
     * 用户初始化元宝
     */
    private int initPoint = 100;

    /**
     * 用户发帖得分
     */
    private int newPostPoint = 10;

    /**
     * 结贴 得分
     */
    private int finishedPostPoint = 5;

    /**
     * 用户回帖得分
     */
    private int newCommentPoint = 2;

    /**
     * 签到得分
     */
    private int signPoint = 5;




    public static FlyConfig get() {
        return Jboot.config(FlyConfig.class);
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public int getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(int initPoint) {
        this.initPoint = initPoint;
    }


    public int getNewPostPoint() {
        return newPostPoint;
    }

    public void setNewPostPoint(int newPostPoint) {
        this.newPostPoint = newPostPoint;
    }

    public int getFinishedPostPoint() {
        return finishedPostPoint;
    }

    public void setFinishedPostPoint(int finishedPostPoint) {
        this.finishedPostPoint = finishedPostPoint;
    }

    public int getNewCommentPoint() {
        return newCommentPoint;
    }

    public void setNewCommentPoint(int newCommentPoint) {
        this.newCommentPoint = newCommentPoint;
    }

    public int getSignPoint() {
        return signPoint;
    }

    public void setSignPoint(int signPoint) {
        this.signPoint = signPoint;
    }

}
