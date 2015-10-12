package me.chenfuduo.myjobforcontest.fragment;

import android.view.View;

import java.util.List;

import me.chenfuduo.myjobforcontest.adapter.AHUAdapter;
import me.chenfuduo.myjobforcontest.bean.JobInfo;
import me.chenfuduo.myjobforcontest.protocol.AHUProtocol;
import me.chenfuduo.myjobforcontest.view.BaseListView;
import me.chenfuduo.myjobforcontest.view.LoadingPage;

/**
 * Created by chenfuduo on 2015/10/9.
 * 安徽大学
 */
public class AHUFragment extends BaseFragment {

    List<JobInfo> jobInfos;

    private AHUAdapter adapter;

    @Override
    public LoadingPage.LoadResult load() {
        AHUProtocol ahuProtocol = new AHUProtocol();
        jobInfos = ahuProtocol.load(48, 1);
        return checkData(jobInfos);
    }

    @Override
    protected View createSuccessView() {
        BaseListView listView = new BaseListView(getActivity());
        if (adapter == null) {
            adapter = new AHUAdapter(jobInfos,listView);
        }

        listView.setAdapter(adapter);
        return listView;
    }
}
