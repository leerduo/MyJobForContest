package me.chenfuduo.myjobforcontest.fragment;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import me.chenfuduo.myjobforcontest.adapter.USTCAdapter;
import me.chenfuduo.myjobforcontest.bean.JobInfo;
import me.chenfuduo.myjobforcontest.protocol.USTCProtocol;
import me.chenfuduo.myjobforcontest.view.BaseListView;
import me.chenfuduo.myjobforcontest.view.LoadingPage;

/**
 * Created by chenfuduo on 2015/10/9.
 * 中国科学技术大学
 */
public class USTCFragment extends BaseFragment {

    List<JobInfo> jobInfos;

    private USTCAdapter adapter;

    @Override
    public LoadingPage.LoadResult load() {
        USTCProtocol ustcProtocol = new USTCProtocol();
        jobInfos = ustcProtocol.load(47, 1);
        return checkData(jobInfos);
    }

    @Override
    protected View createSuccessView() {
        BaseListView listView = new BaseListView(getActivity());
        if (adapter == null) {
            adapter = new USTCAdapter(jobInfos,listView);
        }

        listView.setAdapter(adapter);
        return listView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

}
