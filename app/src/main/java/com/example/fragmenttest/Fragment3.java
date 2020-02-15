package com.example.fragmenttest;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import opensource.jpinyin.PinyinHelper;

public class Fragment3 extends Fragment implements SideBarAdapter.DetailViewHolderListener {

    private ListView listView;
    private TextView txtShowCurrentLetter;
    private SideBar sideBar;
    private SideBarAdapter myAdapter;
    private Button bt, bt_all, bt_delete, bt_add;
    private List<ContactsModel> list;
    private boolean flag;
    private BaseApplication baseApplication;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseApplication=new BaseApplication();
        txtShowCurrentLetter = getActivity().findViewById(R.id.txt_show_current_letter);
        sideBar = getActivity().findViewById(R.id.side_bar);
        listView = getActivity().findViewById(R.id.list_view);
        bt = getActivity().findViewById(R.id.bt);
        bt_all = getActivity().findViewById(R.id.bt_all);
        bt_delete = getActivity().findViewById(R.id.bt_delete);
        bt_add = getActivity().findViewById(R.id.bt_add);
        bt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                myAdapter.setChackBok(true);
                myAdapter.notifyDataSetInvalidated();
            }
        });
        bt_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(true);
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Iterator<ContactsModel> iterator = list.iterator();
//                while (iterator.hasNext()) {
//                    ContactsModel bean = iterator.next();
//                    if (bean.getCheck()) {
//                        iterator.remove();
//                    }
//                }
//                myAdapter.reflshData(list);
                Intent intent = new Intent(getActivity(), Two2Activity.class);
                getActivity().startActivity(intent);
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsModel bean = new ContactsModel();
                bean.setCheck(false);
                bean.setName("1ff");
               baseApplication.addList(bean);
                myAdapter.notifyDataSetChanged();
            }
        });
        setCallbackInterface();
        list = listSort(baseApplication.getData());
        myAdapter = new SideBarAdapter(getActivity(), list, R.layout.adapter_side_bar, this);
        listView.setAdapter(myAdapter);

    }

    public List<ContactsModel> listSort(List<ContactsModel> list) {
        chineseToPinyin(list);

        // 将联系人列表的标题字母排序
        Collections.sort(list, new Comparator<ContactsModel>() {
            @Override
            public int compare(ContactsModel lhs, ContactsModel rhs) {
                return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
            }
        });


        // 将联系人列表的标题字母放到 List<String>列表中，准备数据去重
        List<String> getLetter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            getLetter.add(list.get(i).getFirstLetter());
        }

        // 数据去重
        getLetter = removeDuplicate(getLetter);

        // 将联系人列表的字母标题排序
        Collections.sort(getLetter, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        // 设置已排序好的标题
        sideBar.setLetter(getLetter);
        return list;
    }

    /**
     * 将中文转化为拼音
     */
    public void chineseToPinyin(List<ContactsModel> list) {
        for (int i = 0; i < list.size(); i++) {
            ContactsModel contactsModel1 = list.get(i);
            // 将汉字转换为拼音
            String pinyinString = PinyinHelper.getShortPinyin(list.get(i).getName());
            // 将拼音字符串转换为大写拼音
            String upperCasePinyinString = String.valueOf(pinyinString.charAt(0)).toUpperCase();
            // 获取大写拼音字符串的第一个字符
            char tempChar = upperCasePinyinString.charAt(0);

            if (tempChar < 'A' || tempChar > 'Z') {
                contactsModel1.setFirstLetter("#");
            } else {
                contactsModel1.setFirstLetter(String.valueOf(tempChar));
            }
        }
    }

    /**
     * 设置回调接口
     */
    public void setCallbackInterface() {
        // 回调接口
        sideBar.setOnCurrentLetterListener(new SideBar.OnCurrentLetterListener() {
            @Override
            public void showCurrentLetter(String currentLetter) {
                txtShowCurrentLetter.setVisibility(View.VISIBLE);
                txtShowCurrentLetter.setText(currentLetter);

                int position = myAdapter.getCurrentLetterPosition(currentLetter);
                if (position != -1)
                    listView.setSelection(position);
            }

            @Override
            public void hideCurrentLetter() {
                txtShowCurrentLetter.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 初始化数据
     */
    public List<ContactsModel> initData() {
        List<ContactsModel> list = new ArrayList<>();

        ContactsModel contactsModel;
        String[] nameStrings = {"覃", "岑 ", "$ 来啊，来互相伤害啊 ", "疍姬", "梵蒂冈 ", "亳州", "佟", "郄 ", "张三", "Edward", "李四", "萌萌哒", "霾耷 ", "离散", "赵信", "啦啦 ", "辣妹子", "嗷嗷", "妹妹 ", "]asd", "%Hello"};
        for (int i = 0; i < nameStrings.length; i++) {
            contactsModel = new ContactsModel();
            contactsModel.setName(nameStrings[i]);
            contactsModel.setCheck(false);
            list.add(contactsModel);
        }
        return list;
    }

    /**
     * 去重数据
     *
     * @param list
     * @param <T>
     * @return
     */
    public <T> List<T> removeDuplicate(List<T> list) {

        Set<T> h = new HashSet<>(list);
        list.clear();
        list.addAll(h);
        return list;

    }


    @Override
    public void setData(final SideBarAdapter.ViewHolder viewHolder, int position) {


    }
}
