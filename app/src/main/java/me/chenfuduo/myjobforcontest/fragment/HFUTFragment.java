package me.chenfuduo.myjobforcontest.fragment;

import android.view.View;

import java.util.List;

import me.chenfuduo.myjobforcontest.adapter.HFUTAdapter;
import me.chenfuduo.myjobforcontest.bean.JobInfo;
import me.chenfuduo.myjobforcontest.protocol.HFUTProtocol;
import me.chenfuduo.myjobforcontest.view.BaseListView;
import me.chenfuduo.myjobforcontest.view.LoadingPage;

/**
 * Created by chenfuduo on 2015/10/9.
 * 合肥工业大学
 */
public class HFUTFragment extends BaseFragment {

    List<JobInfo> jobInfos;

    private HFUTAdapter adapter;

    @Override
    public LoadingPage.LoadResult load() {
        HFUTProtocol hfutProtocol = new HFUTProtocol();
        jobInfos = hfutProtocol.load(49, 1);
        return checkData(jobInfos);
    }

    @Override
    protected View createSuccessView() {
        BaseListView listView = new BaseListView(getActivity());
        if (adapter == null) {
            adapter = new HFUTAdapter(jobInfos,listView);
        }

        listView.setAdapter(adapter);
        return listView;
    }
}
