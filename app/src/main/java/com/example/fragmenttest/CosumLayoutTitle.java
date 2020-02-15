package com.example.fragmenttest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmenttest.R;

public class CosumLayoutTitle extends RelativeLayout {
    private ImageView back, submit;
    private TextView title;

    public CosumLayoutTitle(Context context) {
        super(context);


    }

    public CosumLayoutTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this);
        back = findViewById(R.id.iv_back);
        submit = findViewById(R.id.iv_sub);
        title = findViewById(R.id.tv_title);
    }

    public CosumLayoutTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitleText(String titleStr) {
        title.setText(titleStr);
    }

    public void setLeftImageOnListener(OnClickListener listener) {
        back.setOnClickListener(listener);
    }

    public void setRightImageOnListener(OnClickListener listener) {
        submit.setOnClickListener(listener);
    }
}
