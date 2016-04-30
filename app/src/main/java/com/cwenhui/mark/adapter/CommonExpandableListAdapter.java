package com.cwenhui.mark.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.utils.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public abstract class CommonExpandableListAdapter implements ExpandableListAdapter {
    private static final String TAG = "ExpandableListAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private int parentLayout;
    private int childLayout;
    private HashMap<String, List<Practice>> pratices;
    private List<String> parentTypes;

    public CommonExpandableListAdapter(Context context, int parentLayout, int childLayout,
                                       HashMap<String, List<Practice>> pratices) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.parentLayout = parentLayout;
        this.childLayout = childLayout;
        this.pratices = pratices;
        parentTypes = new ArrayList<String>();
        for (Iterator i = pratices.keySet().iterator(); i.hasNext(); ) {
            parentTypes.add((String) i.next());
        }
    }

    @Override

    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public int getGroupCount() {
        return parentTypes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String parent = parentTypes.get(groupPosition);
        return pratices.get(parent).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentTypes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String parent = parentTypes.get(groupPosition);
        return pratices.get(parent).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, parentLayout, groupPosition);
        convertToParent(holder, parentTypes.get(groupPosition), isExpanded);
        return holder.getConvertView();
    }

    protected abstract void convertToParent(ViewHolder holder, String s, boolean isExpanded);

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, childLayout, childPosition);
        convertToChild(holder, (Practice)getChild(groupPosition, childPosition), isLastChild);
        return holder.getConvertView();
    }

    protected abstract void convertToChild(ViewHolder holder, Practice child, boolean isLastChild);

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}