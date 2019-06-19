package com.abc.testapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DoctorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DoctorModel> doctorModelArrayList;

    public DoctorAdapter(Context context, ArrayList<DoctorModel> doctorModelArrayList) {

        this.context = context;
        this.doctorModelArrayList = doctorModelArrayList;
    }


    @Override
    public int getCount() {
        return doctorModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return doctorModelArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.doc_item, null, true);

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            holder.gender = (TextView) convertView.findViewById(R.id.gender);
            holder.special = (TextView) convertView.findViewById(R.id.special);
            holder.phone = (TextView) convertView.findViewById(R.id.phone);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            holder.ward = (TextView) convertView.findViewById(R.id.ward);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText("Name: "+doctorModelArrayList.get(position).getName());
        holder.age.setText("Age: "+doctorModelArrayList.get(position).getAge());
        holder.gender.setText("Gender: "+doctorModelArrayList.get(position).getGender());
        holder.special.setText("Special In: "+doctorModelArrayList.get(position).getSpecial());
        holder.phone.setText("Phone: "+doctorModelArrayList.get(position).getPhone());
        holder.address.setText("Address: "+doctorModelArrayList.get(position).getAddress());
        holder.ward.setText("Ward No: "+doctorModelArrayList.get(position).getWard());

        return convertView;
    }

    private class ViewHolder {

        protected TextView name, age, gender, special, phone, address, ward;
    }
}
