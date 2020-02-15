package com.example.fragmenttest;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {
  public static   List<ContactsModel> list =new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        list= initData();
    }

    public List<ContactsModel> getData() {
        return list;
    }

    /**
     * 初始化数据
     */
    public List<ContactsModel> initData() {

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

    public void addList(ContactsModel contactsModel) {
        list.add(contactsModel);

    }
}
