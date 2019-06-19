package com.abc.testapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StaffCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StaffModel> staffModelArrayList;

    public StaffCustomAdapter(Context context, ArrayList<StaffModel> staffModelArrayList) {

        this.context = context;
        this.staffModelArrayList = staffModelArrayList;
    }

    @Override
    public int getCount() {
        return staffModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return staffModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.staff_lv_items, null, true);

            holder.sname = (TextView) convertView.findViewById(R.id.sname);
            holder.sAge = (TextView) convertView.findViewById(R.id.sAge);
            holder.sGen = (TextView) convertView.findViewById(R.id.sGen);
            holder.sAddress = (TextView) convertView.findViewById(R.id.sAddress);
            holder.ContactNo = (TextView) convertView.findViewById(R.id.ContactNo);
            holder.Department = (TextView) convertView.findViewById(R.id.Department);


            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.sname.setText("Name: "+staffModelArrayList.get(position).getName());
        holder.sAge.setText("Age: "+staffModelArrayList.get(position).getAge());
        holder.sGen.setText("Gender: "+staffModelArrayList.get(position).getGender());
        holder.sAddress.setText("Address: "+staffModelArrayList.get(position).getAddress());
        holder.ContactNo.setText("Contact No: "+staffModelArrayList.get(position).getTp());
        holder.Department.setText("Department: "+staffModelArrayList.get(position).getDepartment());

        return convertView;
    }

    private class ViewHolder {

        protected TextView sname, sAge, sAddress, ContactNo, Department,sGen;
    }
}
