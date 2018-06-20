package com.example.bibingwei.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author bibingwei
 * 获得固定时间格式的工具
 */
public class GetDateUtil {
    public String getDate(int passDay){
        Calendar calendar1 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        calendar1.add(Calendar.DATE, passDay);
        return sdf1.format(calendar1.getTime());
    }
}
