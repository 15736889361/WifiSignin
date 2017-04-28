package com.chj.wifisignin.signin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chj.wifisignin.R;
import com.chj.wifisignin.beans.Sign;

import java.util.List;

/**
 * author: WEI
 * date: 2017-04-28.
 */

public class SignAdapter extends BaseAdapter
{
    private Context mContext;
    private List<Sign> mSigns;

    public SignAdapter(Context context, List<Sign> signs) {
        mContext = context;
        mSigns = signs;
    }

    @Override
    public int getCount() {
        return mSigns.size();
    }

    @Override
    public Object getItem(int i) {
        return mSigns.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;
        if (view == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_sign, null);
            holder = new ViewHolder();
            holder.numTv = (TextView) view.findViewById(R.id.tv_num);
            holder.inTimeTv = (TextView) view.findViewById(R.id.tv_signin);
            holder.outTimeTv = (TextView) view.findViewById(R.id.tv_signout);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        Sign sign = (Sign) getItem(i);
        if (null != sign)
        {
            holder.numTv.setText(sign.getNum());
            holder.inTimeTv.setText(sign.getSignin_time());
            holder.outTimeTv.setText(sign.getSignout_time());
        }
        return view;
    }

    class ViewHolder
    {
        private TextView numTv, inTimeTv, outTimeTv;
    }

}
