package com.chj.wifisignin.signin.model;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.chj.wifisignin.base.Global;
import com.chj.wifisignin.beans.Sign;
import com.chj.wifisignin.signin.view.MainActivity;
import com.chj.wifisignin.util.LogUtil;
import com.chj.wifisignin.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.icu.lang.UCharacter.JoiningGroup.E;
import static cn.bmob.v3.BmobRealTimeData.TAG;

/**
 * author: WEI
 * date: 2017/4/28
 */

public class SigninModelImpl implements ISigninModel {
    private final String TAG = getClass().getSimpleName();
    private String objectId;

    @Override
    public void signin(final Context context, final Sign sign, final Handler handler) {
        if (TextUtils.isEmpty(sign.getSignout_time()))
        {
            sign.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        objectId = sign.getObjectId();
                        ToastUtil.showMsg(context, "签到成功！", true);
                        handler.obtainMessage(MainActivity.SIGNIN_SUCCEED).sendToTarget();
                    } else {
                        LogUtil.e(TAG, e.getMessage());
                        ToastUtil.showMsg(context, "签到失败！", true);
                        handler.obtainMessage(MainActivity.SIGNIN_FAILURE).sendToTarget();
                    }
                }
            });
        }
        else
        {
            Sign sign1 = new Sign();
            sign1.setValue("signout_time", sign.getSignout_time());
            sign.update(objectId, new UpdateListener() {
                 @Override
                 public void done(BmobException e) {
                     if(e==null){
                         ToastUtil.showMsg(context, "签退成功!", true);
                         handler.obtainMessage(MainActivity.SIGNOUT_SUCCEED).sendToTarget();
                     }else{
                         ToastUtil.showMsg(context, "签退失败!", true);
                         handler.obtainMessage(MainActivity.SIGNOUT_FAILURE).sendToTarget();
                     }
                 }
             });
        }
    }

    @Override
    public void getSigns(Context context, final ISignInfos iSignInfos) {
        BmobQuery<Sign> query = new BmobQuery<>();
        String sql;
        if (Global.sUser.getType() == Global.STUDENT) {
            // 学生身份登录，只能查看自己在该路由器的签到记录。 根据routerMac及学号查询列表集合
            sql = "select * from Sign where num = '" + Global.sUser.getNum() + "' and routerMac = '" + Global.sUser.getRouterMac() + "'";
        } else {
            // 老师身份登录，能查看所有学生在该路由下的签到记录。 根据routerMac查询所有列表集合
            sql = "select * from Sign where routerMac = '" + Global.sUser.getRouterMac() + "'";
        }
        query.setSQL(sql);
        query.doSQLQuery(sql, new SQLQueryListener<Sign>() {
            @Override
            public void done(BmobQueryResult<Sign> bmobQueryResult, BmobException e) {
                if (e == null) {
                    List<Sign> signs = bmobQueryResult.getResults();
                    if (null != signs && signs.size() > 0) {
                        iSignInfos.getSigns(signs);
                    }
                } else {
                    LogUtil.e(TAG, "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                }
            }
        });
    }

    public interface ISignInfos {
        void getSigns(List<Sign> signs);
    }

    private ISignInfos mISignInfos;

    public ISignInfos getISignInfos() {
        return mISignInfos;
    }

    public void setISignInfos(ISignInfos ISignInfos) {
        mISignInfos = ISignInfos;
    }

}
