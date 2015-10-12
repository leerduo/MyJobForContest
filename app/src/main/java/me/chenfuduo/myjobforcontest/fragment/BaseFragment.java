package me.chenfuduo.myjobforcontest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.chenfuduo.myjobforcontest.utils.ViewUtils;
import me.chenfuduo.myjobforcontest.view.LoadingPage;


/**
 * Created by chenfuduo on 2015/10/2.
 */
public abstract class BaseFragment extends Fragment {

    LoadingPage loadingPage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()){

                @Override
                public LoadingPage.LoadResult load() {
                    return BaseFragment.this.load();
                }

                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };
        } else {
            ViewUtils.removeSelfFromParent(loadingPage);
        }
        return loadingPage;
    }


    /**
     * 请求服务器数据
     *
     * @return
     */
    public abstract LoadingPage.LoadResult load();

    /**
     * 创建成功的界面
     *
     * @return
     */
    protected abstract View createSuccessView();


    public void show(){
        if (loadingPage != null){
            loadingPage.show();
        }
    }


    public LoadingPage.LoadResult checkData(List datas) {
        if (datas == null){
            return LoadingPage.LoadResult.error;
        }else{
            if (datas.size() == 0){
                return LoadingPage.LoadResult.empty;
            }else{
                return LoadingPage.LoadResult.success;
            }
        }
    }

}
