package me.chenfuduo.myjobforcontest.protocol;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import me.chenfuduo.myjobforcontest.bean.JobInfo;
import me.chenfuduo.myjobforcontest.bean.ResultInfo;

/**
 * Created by chenfuduo on 2015/10/9.
 */
public class USTCProtocol extends BaseProtocol<List<JobInfo>> {

    private List<JobInfo> jobInfoList = new ArrayList<>();

    @Override
    protected List<JobInfo> parserJson(String json) {
        ResultInfo resultInfo = JSON.parseObject(json, ResultInfo.class);
        int count = resultInfo.getCount();
        int page = resultInfo.getPage();
        Log.e("Test", "parserJson " + count + "  " + page);
        jobInfoList = resultInfo.getInfo();
        return jobInfoList;
    }
}