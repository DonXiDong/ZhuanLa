package com.zhuanla.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhuanla.R;
import com.zhuanla.adapter.MyExpanableListViewAdapter;
import com.zhuanla.config.PointView;
import com.zhuanla.config.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private PointView pointView;
    private ExpandableListView expandableListView;
    private List<String> parent;
    private Map<String, List<String>> child;
    private List<String> card;
    private List<String> more;
    private int[] j;
    private boolean IsFlag;
    private MyExpanableListViewAdapter myExpanableListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlidingMenu slidingMenu1 = new SlidingMenu(this);

        slidingMenu1.setMode(SlidingMenu.LEFT);
        slidingMenu1.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 5);
        slidingMenu1.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu1.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu1.setMenu(R.layout.activity_left);
        j = new int[]{R.mipmap.app_logo,R.mipmap.ic_menu};
        ininView();
        initData();
        myExpanableListViewAdapter = new MyExpanableListViewAdapter(parent, child, MainActivity.this);
        expandableListView.setAdapter(myExpanableListViewAdapter);
        expandableListView.setGroupIndicator(null);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ToastUtils.shortToast("哈哈");

                return true;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                if (id==0||id== 4) {


                    return false;
                }else {

                ToastUtils.shortToast("id"+id);
                }
                return true;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < 6; i++) {
                    if (groupPosition != i) {
                        expandableListView.collapseGroup(i);


                    }
                }

            }

        });
    }

    private void initData() {
        parent = new ArrayList<>();
        parent.add("银行卡");
        parent.add("活动");
        parent.add("密码");
        parent.add("消息");
        parent.add("更多");
        parent.add("版本");

        child = new HashMap<>();
         card = new ArrayList<>();
        card.add("支持银行及限额");
        child.put("银行卡",card);
        more = new ArrayList<>();
        more.add("帮助中心");
        more.add("意见反馈");
        more.add("关于我们");
        more.add("鼓励一下");
        child.put("更多",more);
    }

    private void ininView() {
        expandableListView = ((ExpandableListView) findViewById(R.id.expandablelistview));

    }

}
