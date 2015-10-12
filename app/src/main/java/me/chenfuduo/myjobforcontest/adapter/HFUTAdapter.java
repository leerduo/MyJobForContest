package me.chenfuduo.myjobforcontest.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import me.chenfuduo.myjobforcontest.DetailActivity;
import me.chenfuduo.myjobforcontest.R;
import me.chenfuduo.myjobforcontest.bean.JobInfo;
import me.chenfuduo.myjobforcontest.holder.BaseViewHolder;
import me.chenfuduo.myjobforcontest.protocol.HFUTProtocol;
import me.chenfuduo.myjobforcontest.utils.UIUtils;

/**
 * Created by chenfuduo on 2015/10/9.
 */
public class HFUTAdapter extends DefaultAdapter<JobInfo> {
    private ListView listView;

    public HFUTAdapter(List<JobInfo> jobInfos, ListView listView) {
        super(jobInfos, listView);
        this.listView = listView;
    }

    @Override
    public void onInnerItemClick(int position) {
        super.onInnerItemClick(position);
        Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
        JobInfo jobInfo = datas.get(position);
        int id = jobInfo.getId();
        String urlId = String.valueOf(id);
        String URL= "http://mobile.haitou.cc/client3/article?id=" + urlId +  "&source=xjh&ver=1.0";
        intent.putExtra("URL",URL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
    }


    @Override
    public BaseViewHolder<JobInfo> getHolder() {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
        }
        return viewHolder;
    }

    @Override
    protected List<JobInfo> onload() {
        HFUTProtocol hfutProtocol = new HFUTProtocol();
        List<JobInfo> jobInfos = hfutProtocol.load(49, 30);
        datas.addAll(jobInfos);
        return jobInfos;
    }


    static class ViewHolder extends BaseViewHolder<JobInfo>{
        ImageView icon;
        TextView company;
        TextView time;
        TextView address;
        @Override
        public View initView() {
            View convertView = View.inflate(UIUtils.getContext(), R.layout.list_item, null);
            this.icon = (ImageView) convertView.findViewById(R.id.icon);
            this.company = (TextView) convertView.findViewById(R.id.company);
            this.time = (TextView) convertView.findViewById(R.id.time);
            this.address = (TextView) convertView.findViewById(R.id.address);
            return convertView;
        }

        @Override
        public void refreshView(JobInfo jobInfo) {
            String name = jobInfo.getName();
            if (name.equals("中国科学技术大学")){
                this.icon.setImageResource(R.drawable.a47);
            }else if (name.equals("合肥工业大学")){
                this.icon.setImageResource(R.drawable.a49);
            }else if(name.equals("安徽大学")){
                this.icon.setImageResource(R.drawable.a48);
            }
            this.company.setText(jobInfo.getCompany());
            this.time.setText(jobInfo.getHoldtime());
            this.address.setText(jobInfo.getAddress());
        }


    }

}
