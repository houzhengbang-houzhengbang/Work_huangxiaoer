package com.example.asus.huangxiaoer.view.activity;


import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.huangxiaoer.R;
import com.example.asus.huangxiaoer.model.model.SearchModel;
import com.example.asus.huangxiaoer.presenter.SearchPresenter;
import com.example.asus.huangxiaoer.view.custom.MySearchView;
import com.example.asus.huangxiaoer.view.interfaces.SearchInterface;
import com.example.base.BaseActivity;
import com.example.base.mvp.BaseModel;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchInterface,View.OnClickListener {

    private ImageView search_back;
    private EditText mSearchInputSearch;
    private ImageView mResultSearch;
    private Button mSearchClear;
    private TextView textView;
    private MySearchView mSearchFlowlayout;


    @Override
    protected int bindLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected BaseModel initModel() {
        return new SearchModel();
    }

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initView() {

        search_back = (ImageView) findViewById(R.id.search_back);
        mSearchInputSearch = (EditText) findViewById(R.id.search_input_search);
        mResultSearch = (ImageView) findViewById(R.id.result_search);

        mSearchFlowlayout = (MySearchView) findViewById(R.id.search_flowlayout);

        mSearchClear = (Button) findViewById(R.id.search_clear);
        mSearchClear.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        /*
        * 返回上一层*/
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //点击删除 编辑栏里的 字
        mSearchInputSearch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = mSearchInputSearch.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > mSearchInputSearch.getWidth()
                        - mSearchInputSearch.getPaddingRight()
                        - drawable.getIntrinsicWidth()){
                   mSearchInputSearch.setText("");


                }
                return false;
            }
        });


     //点击搜索添加历史记录
        mResultSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mSearchInputSearch.getText().toString();
                textView = new TextView(SearchActivity.this);
                textView.setBackground(getDrawable(R.drawable.searchviewbg));
                textView.setPadding(5, 5, 5, 5);
                textView.setTextSize(15);
                textView.setText(s);
                mSearchFlowlayout.addView(textView);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_clear:
                mSearchFlowlayout.removeAllViews();
                break;
            default:
                break;
        }
    }

}
