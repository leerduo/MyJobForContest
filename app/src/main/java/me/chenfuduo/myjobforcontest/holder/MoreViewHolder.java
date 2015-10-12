package me.chenfuduo.myjobforcontest.holder;

import android.view.View;
import android.widget.RelativeLayout;

import me.chenfuduo.myjobforcontest.R;
import me.chenfuduo.myjobforcontest.adapter.DefaultAdapter;
import me.chenfuduo.myjobforcontest.utils.UIUtils;


/**
 * Created by chenfuduo on 2015/10/3.
 */
public class MoreViewHolder extends BaseViewHolder<Integer> {
    public static final int HAS_NO_MORE = 0;
    public static final int LOAD_ERROR = 1;
    public static final int HAS_MORE = 2;


    private boolean hasMore;

    private RelativeLayout rl_more_loading, rl_more_error;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.load_more);
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }


    private DefaultAdapter adapter;

    public MoreViewHolder(DefaultAdapter adapter, boolean hasMore) {
        super();
        this.adapter = adapter;
        this.hasMore = hasMore;
        if (!hasMore){
            setData(HAS_NO_MORE);
        }
    }


    @Override
    public View getConvertView() {
        if (hasMore) {
            loadMore();
        }
        return super.getConvertView();
    }

    private void loadMore() {
        adapter.loadMore();
    }

    @Override
    public void refreshView(Integer integer) {
        rl_more_error.setVisibility(data == LOAD_ERROR ? View.VISIBLE : View.GONE);
        rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
    }
}
