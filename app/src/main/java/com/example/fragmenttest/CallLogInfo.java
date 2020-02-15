package com.example.fragmenttest;

public class CallLogInfo {
    public String number;
    public long date;
    public int type;
    public String name;
    private String duration;
    public CallLogInfo(String number, long date, int type,String name,String duration) {
        super();
        this.number = number;
        this.date = date;
        this.type = type;
        this.name=name;
        this.duration=duration;
    }
    public CallLogInfo() {
        super();
    }

}
