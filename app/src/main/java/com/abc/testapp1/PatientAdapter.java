package com.abc.testapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PatientModel> patientModelArrayList;


    public PatientAdapter(Context context, ArrayList<PatientModel> patientModelArrayList) {

        this.context = context;
        this.patientModelArrayList = patientModelArrayList;
    }

    @Override
    public int getCount() {
        return patientModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return patientModelArrayList.get(position);
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
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.patientlist, null, true);

            holder.pname = (TextView) convertView.findViewById(R.id.pname);
            holder.page = (TextView) convertView.findViewById(R.id.page);
            holder.paddress = (TextView) convertView.findViewById(R.id.paddress);
            holder.pphone = (TextView) convertView.findViewById(R.id.pphone);
            holder.pdisease = (TextView) convertView.findViewById(R.id.pdisease);
            holder.pgender = (TextView) convertView.findViewById(R.id.pgender);
            holder.pgurdian = (TextView) convertView.findViewById(R.id.pgurdian);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.pname.setText("Name: "+patientModelArrayList.get(position).getName());
        holder.page.setText("Age: "+patientModelArrayList.get(position).getAge());
        holder.paddress.setText("Location: "+patientModelArrayList.get(position).getAddress());
        holder.pphone.setText("Contact No: "+patientModelArrayList.get(position).getContNo());
        holder.pdisease.setText("Disease : "+patientModelArrayList.get(position).getDisease());
        holder.pgender.setText("Gender: "+patientModelArrayList.get(position).getGender());
        holder.pgurdian.setText("Guardian Name: "+patientModelArrayList.get(position).getGurName());


        return convertView;
    }

    private class ViewHolder {

        protected TextView pname,page,paddress,pphone,pdisease,pgender,pgurdian;
    }
}
