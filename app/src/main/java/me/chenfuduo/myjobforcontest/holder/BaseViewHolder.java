package me.chenfuduo.myjobforcontest.holder;

import android.view.View;

/**
 * Created by chenfuduo on 2015/10/3.
 */
public abstract class BaseViewHolder<Data> {

    View convertView;

    Data data;

    public BaseViewHolder() {
        convertView =  initView();
        convertView.setTag(this);
    }

    public abstract View initView();

    public void setData(Data data) {
        this.data = data;
        refreshView(data);
    }

    public View getConvertView() {
        return convertView;
    }


    public abstract void refreshView(Data data);
}
