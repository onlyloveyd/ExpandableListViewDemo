package cn.onlyloveyd.expandablelistviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 文 件 名: YEAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/08/27 08/04
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class YEAdapter extends BaseExpandableListAdapter {

    //一级节点数据
    private List<GroupItem> mGroupItems;
    private Context mContext;

    public YEAdapter(Context context, List<GroupItem> mData) {
        mGroupItems = mData;
        mContext = context;
    }

    /**
     * 一级节点数量
     * @return
     */
    @Override
    public int getGroupCount() {
        return mGroupItems.size();
    }

    /**
     * 指定位置一级节点下二级节点数量
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupItems.get(groupPosition).mChildList.size();
    }

    /**
     * 获取一级节点对象
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mGroupItems.get(groupPosition);
    }

    /**
     * 获取二级节点对象
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupItems.get(groupPosition).mChildList.get(childPosition);
    }

    /**
     * 获取一级节点ID，这里用位置值表示
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取二级节点ID，这里用位置值表示
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * ID是否稳定
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取一级节点view
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=null;
        TextView mTv;
        if(convertView!=null){
            view = convertView;
            mTv = (TextView) view.getTag();
        }else{
            view = View.inflate(mContext,R.layout.group, null);
            mTv = (TextView) view.findViewById(R.id.group_text);
            view.setTag(mTv);
        }
        mTv.setText(mGroupItems.get(groupPosition).title);
        return view;
    }


    /**
     * 获取二级节点View
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=null;
        TextView mTextView;
        if(convertView!=null){
            view = convertView;
            mTextView = (TextView) view.getTag();
        }else{
            view = View.inflate(mContext,R.layout.child, null);
            mTextView = (TextView) view.findViewById(R.id.textOne);
            view.setTag(mTextView);
        }
        mTextView.setText(mGroupItems.get(groupPosition).mChildList.get(childPosition).message);
        return view;
    }

    /**
     * 二级菜单是否可选（true为可选，false为不可选，也就是响应和不响应点击事件）
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 一级节点对象
     */
    public static class GroupItem {
        String title;
        List<ChildItem> mChildList;
    }

    /**
     * 二级节点对象
     */
    public static class ChildItem {
        String message;
    }
}
