package me.chenfuduo.myjobforcontest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import me.chenfuduo.myjobforcontest.R;
import me.chenfuduo.myjobforcontest.utils.UIUtils;

/**
 * Created by chenfuduo on 2015/10/9.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context) {
        this(context, null);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //点击条目的颜色
        this.setSelector(R.drawable.nothing);
        //拖拽的颜色
        this.setCacheColorHint(R.drawable.nothing);
        //每个条目的分割线
        this.setDivider(UIUtils.getDrawable(R.drawable.nothing));
    }
}
