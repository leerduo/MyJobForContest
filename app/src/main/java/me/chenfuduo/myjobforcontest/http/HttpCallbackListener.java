package me.chenfuduo.myjobforcontest.http;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public interface HttpCallbackListener {
    void onSuccess(String response);
    void onError(Exception e);
}
