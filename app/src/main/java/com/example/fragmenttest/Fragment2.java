package com.example.fragmenttest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment implements View.OnClickListener {
    private CosumLayoutTitle cosumLayoutTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tow, null);
        cosumLayoutTitle = view.findViewById(R.id.ll_title);
        cosumLayoutTitle.setTitleText("ddddd");
        cosumLayoutTitle.setLeftImageOnListener(this);
        cosumLayoutTitle.setRightImageOnListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Toast.makeText(getActivity(), "left", Toast.LENGTH_LONG).show();
                break;
            case R.id.iv_sub:
                Toast.makeText(getActivity(), "right", Toast.LENGTH_LONG).show();
                break;
        }


    }
}
