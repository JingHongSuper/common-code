package com.eju.test_android;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionghaocai on 16/7/19.
 * BaseAdapter 的基类
 */

public abstract class BasicAdapter<E> extends BaseAdapter {
    protected Context mContext;
    /**
     * 数据缓存
     */
    protected List<E> mDataCache;
    private LayoutInflater mInflater;
    public BasicAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据
     * @param set
     */
    public void setData(List<E> set) {
        if (set == null) {
            return;
        }
        if (null == mDataCache) {
            mDataCache = new ArrayList<E>();
        }
        mDataCache.clear();
        mDataCache.addAll(set);
        notifyDataSetChanged();
    }


    /**
     * 更新指定位置数据
     * @param position
     * @param e
     */
    public void updateData(int position,E e) {
        if (e==null){
            return;
        }
        if (mDataCache==null||mDataCache.size()==0){
            return;
        }
        if (position>mDataCache.size()){
            return;
        }
        mDataCache.remove(position);
        mDataCache.add(position,e);
        notifyDataSetChanged();
    }

    /**
     * 增加数据
     * @param set
     */
    public void addData(List<E> set) {
        if (set == null) {
            return;
        }
        mDataCache.addAll(set);
        notifyDataSetChanged();
    }


    @Override
    public E getItem(int position) {
        return null == mDataCache ? null : mDataCache.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public List<E> getmDataCache() {
        return mDataCache;
    }


    @Override
    public int getCount() {
        return null == mDataCache ? 0 : mDataCache.size();
    }

    public static class ViewHolder {
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }
}
