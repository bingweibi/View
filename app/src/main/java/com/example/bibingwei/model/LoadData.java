package com.example.bibingwei.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.example.bibingwei.bean.OtherReading;
import com.example.bibingwei.model.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author bibingwei
 *
 * 加载数据
 */
public class LoadData {

    private static List<OtherReading.ResultBean.DataBean> mDataBeanList = new ArrayList<>();

    @SuppressLint("CheckResult")
    public static List<OtherReading.ResultBean.DataBean> initData(Map params, final Context mContext){
        Network.getOtherApi()
                .getData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OtherReading>() {
                    @Override
                    public void accept(OtherReading otherReading) {
                        mDataBeanList = otherReading.getResult().getData();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(mContext ,"余额不足，请充值",Toast.LENGTH_SHORT).show();
                    }
                });
        return mDataBeanList;
    }
}
