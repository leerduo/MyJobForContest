package me.chenfuduo.myjobforcontest.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import me.chenfuduo.myjobforcontest.holder.BaseViewHolder;
import me.chenfuduo.myjobforcontest.holder.MoreViewHolder;
import me.chenfuduo.myjobforcontest.manager.ThreadManager;
import me.chenfuduo.myjobforcontest.utils.UIUtils;


/**
 * Created by chenfuduo on 2015/10/3.
 */
public abstract class DefaultAdapter<Data> extends BaseAdapter implements AdapterView.OnItemClickListener {

    protected List<Data> datas;

    private ListView listView;

    public DefaultAdapter(List<Data> datas, ListView listView) {
        this.datas = datas;
        this.listView = listView;
        listView.setOnItemClickListener(this);
    }

    private static final int DEFAULT_ITEM = 0;
    private static final int MORE_ITEM = 1;

    @Override
    public int getCount() {
        return datas.size() + 1;//+1是因为加载更多
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) { // 当前是最后一个条目
            return MORE_ITEM;
        }
        return getInnerItemViewType(position); // 如果不是最后一个条目 返回默认类型
    }

    protected int getInnerItemViewType(int position) {
        return DEFAULT_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;//加载更多  2种不同类型
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder viewHolder;
        switch (getItemViewType(position)) {
            case MORE_ITEM:
                if (convertView == null) {
                    viewHolder = getMoreViewHolder();
                } else {
                    viewHolder = (BaseViewHolder) convertView.getTag();
                }
                break;
            default:
                if (convertView == null) {
                    viewHolder = getHolder();
                } else {
                    viewHolder = (BaseViewHolder) convertView.getTag();
                }
                if (position < datas.size()) {
                    viewHolder.setData(datas.get(position));
                }
                break;
        }
        return viewHolder.getConvertView();
    }


    private MoreViewHolder holder;

    private BaseViewHolder getMoreViewHolder() {
        if(holder!=null){
            return holder;
        }else{
            holder=new MoreViewHolder(this,hasMore());
            return holder;
        }
    }

    protected boolean hasMore() {
        return true;
    }


    public abstract BaseViewHolder<Data> getHolder();


    public void loadMore() {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                final List<Data> newData = onload();
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData == null) {
                            holder.setData(MoreViewHolder.LOAD_ERROR);//
                        } else if (newData.size() == 0) {
                            holder.setData(MoreViewHolder.HAS_NO_MORE);
                        } else {
                            // 成功了
                            holder.setData(MoreViewHolder.HAS_MORE);
                            datas.addAll(newData);//  给listView之前的集合添加一个新的集合
                            notifyDataSetChanged();// 刷新界面

                        }
                    }
                });
            }
        });
    }

    /**
     * 加载更多数据
     */
    protected  abstract List<Data> onload();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取顶部条目的数量
        int headerViewsCount = listView.getHeaderViewsCount();
        position = position - headerViewsCount;
        //Toast.makeText(UIUtils.getContext(),"position:" + position,Toast.LENGTH_SHORT).show();
        onInnerItemClick(position);
    }

    /**
     * 该方法去处理条目点击事件
     * @param position
     */
    public void onInnerItemClick(int position) {

    }
}
