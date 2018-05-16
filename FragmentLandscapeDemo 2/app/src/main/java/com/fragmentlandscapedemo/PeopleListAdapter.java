package com.fragmentlandscapedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class PeopleListAdapter extends BaseAdapter {

    private final List<ListItemModel> mValues;
    private Context context;

    public PeopleListAdapter(Context context,List<ListItemModel> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListItemModel listItemModel = (ListItemModel) getItem(position);
        if (listItemModel.getType().equals("Administrator")) {
            viewHolder.tvProgram.setText(listItemModel.getAdministrator().getProgram());
            viewHolder.tvName.setText(listItemModel.getAdministrator().getName());
        } else if (listItemModel.getType().equals("Teacher")) {
            viewHolder.tvProgram.setText(listItemModel.getTeacher().getCourse());
            viewHolder.tvName.setText(listItemModel.getTeacher().getName());
        } else if (listItemModel.getType().equals("Student")) {
            viewHolder.tvProgram.setText(listItemModel.getStudent().getGrade());
            viewHolder.tvName.setText(listItemModel.getStudent().getName());
        }

        return convertView;
    }

    public class ViewHolder {
        public final TextView tvName,tvProgram;
        public ViewHolder(View view) {
            tvName = view.findViewById(R.id.tvFirstName);
            tvProgram = view.findViewById(R.id.tvProgram);
        }
    }
}
