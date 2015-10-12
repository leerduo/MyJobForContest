package me.chenfuduo.myjobforcontest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class ResultInfo {

    private List<JobInfo> info = new ArrayList<>();

    private int page;

    private int count;

    public ResultInfo(List<JobInfo> info, int page, int count) {
        this.info = info;
        this.page = page;
        this.count = count;
    }

    public ResultInfo() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<JobInfo> getInfo() {
        return info;
    }

    public void setInfo(List<JobInfo> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "info=" + info +
                ", page=" + page +
                ", count=" + count +
                '}';
    }
}
