package com.zhuanla.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuanla.R;
import com.zhuanla.config.PointView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyExpanableListViewAdapter extends BaseExpandableListAdapter {
    private final List<String> parent1;
    private final Map<String, List<String>> map;
    private Context context;
    String [] category = new String []{"银行卡","活动","密码","消息","更多","版本"};
    String [][] subcategory  = new String [][]{{"支持银行及限额"},{},{},{},{"帮助中心","意见反馈","关于我们","鼓励一下" },{}};

    int[] logos =new int[]{R.mipmap.ic_banks,R.mipmap.left_action,R.mipmap.ic_password_un,R.mipmap.left_message,R.mipmap.ic_mores,R.mipmap.ic_version};
    public TextView getTextView(){
        AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
        TextView textView=new TextView(context);
        //设置 textView控件的布局
        textView.setLayoutParams(lp);
        //设置该textView中的内容相对于textView的位置
        textView.setGravity(Gravity.CENTER_VERTICAL);
        //设置txtView的内边距
        textView.setPadding(36, 8, 3, 8);

        //设置文本颜色
        textView.setTextColor(Color.WHITE);
        return textView;

    }
    public MyExpanableListViewAdapter(List<String> parent1 , Map<String,List<String>> map, Context context) {
        this.map = map;
        this.parent1 = parent1;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return category.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parent1.get(groupPosition);
        int size = map.get(key).size();

        return subcategory[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return category[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent1.get(groupPosition);
        return subcategory[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LinearLayout ll=new LinearLayout(context );
        //设置子控件的显示方式为水平
        ll.setOrientation(LinearLayout.HORIZONTAL);
      ll.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);
        ll.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ImageView logo=new ImageView(context);
        logo.setPadding(50,45, 0, 45);
        //设置logo的大小(50（padding）+46=96)
       AbsListView.LayoutParams lparParams=new AbsListView.LayoutParams(196,136);

        logo.setLayoutParams(lparParams);
        logo.setImageResource(logos[groupPosition]);
        ll.addView(logo);
        TextView textView=getTextView();

        textView.setTextSize(17);
        textView.setSingleLine();
        textView.setText(category[groupPosition]);
        textView.setLayoutParams(lparParams);
        ll.addView(textView);
        PointView pointView = new PointView(context);
        pointView.setPointStyle(PointView.NUMBER_MESSAGE);
        pointView.setMessageNumber(14);
        pointView.setPadding(30,80,20,10);
        pointView.setLayoutParams(lparParams);
        if (groupPosition==3) {
        ll.addView(pointView);
        }
        TextView textView1=getTextView();

        textView1.setTextSize(15);
        textView1.setSingleLine();
        textView1.setText("v1.4.7");
        textView1.setPadding(10,10,20,10);
        textView1.setLayoutParams(lparParams);
        textView1.setGravity(View.TEXT_ALIGNMENT_CENTER);
        if (groupPosition==5) {

            ll.addView(textView1);
            ;
        }


        return ll;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LinearLayout ll=new LinearLayout(context);
        //设置子控件的显示方式为水平
        ll.setOrientation(LinearLayout.HORIZONTAL);
        //定义一个ImageView用于显示列表图片

        //设置logo的大小
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(450, 130);


        TextView textView=getTextView();
        textView.setText(subcategory[groupPosition][childPosition]);
        textView.setPadding(150, 20, 0, 20);
        textView.setSingleLine();
        textView.setLayoutParams(lp);
        ll.addView(textView);
        return ll;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
