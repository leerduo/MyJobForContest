package me.chenfuduo.myjobforcontest.bean;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class JobDetailResultInfo {

    private MyDetailInfo info = new MyDetailInfo();

    public JobDetailResultInfo(MyDetailInfo info) {
        this.info = info;
    }


    public JobDetailResultInfo() {
    }


    public MyDetailInfo getInfo() {
        return info;
    }

    public void setInfo(MyDetailInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "JobDetailResultInfo{" +
                "info=" + info +
                '}';
    }
}
