package me.chenfuduo.myjobforcontest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import me.chenfuduo.myjobforcontest.bean.JobDetailResultInfo;
import me.chenfuduo.myjobforcontest.bean.MyDetailInfo;
import me.chenfuduo.myjobforcontest.http.HttpCallbackListener;
import me.chenfuduo.myjobforcontest.http.HttpUtils;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = DetailActivity.class.getSimpleName();


    private TextView school;

    private TextView time;

    private TextView addressTv;

    private TextView source;

    private TextView text;

    private TextView nameTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        setupToolbar();

        school = (TextView) findViewById(R.id.school);
        time = (TextView) findViewById(R.id.time);
        addressTv = (TextView) findViewById(R.id.address);
        source = (TextView) findViewById(R.id.source);
        text = (TextView) findViewById(R.id.text);

        nameTv = (TextView) findViewById(R.id.name);

        String url = getIntent().getStringExtra("URL");
        Log.e(TAG, "onCreate " + url);

        final ProgressDialog pd = new ProgressDialog(this);

        pd.setMessage("加载中...");

        pd.show();

        HttpUtils.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(String response) {



                Log.e(TAG, "onSuccess " + response);
                JobDetailResultInfo jobDetailResultInfo = JSON.parseObject(response, JobDetailResultInfo.class);
                final MyDetailInfo info = jobDetailResultInfo.getInfo();
                final String address = info.getAddress();
                final String company = info.getCompany();
                final String name = info.getName();

                final String holdtime = info.getHoldtime();

                final String infotext = info.getInfotext();

                final String web = info.getWeb();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        pd.dismiss();

                        school.setText("学校:" + name);
                        time.setText("时间:" + holdtime);
                        addressTv.setText("地点:" + address);
                        source.setText("来源:" + web);
                        text.setText(Html.fromHtml(infotext));
                        nameTv.setText(company);
                    }
                });


            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
