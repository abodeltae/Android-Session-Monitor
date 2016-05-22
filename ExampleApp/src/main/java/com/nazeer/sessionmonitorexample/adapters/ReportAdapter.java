package com.nazeer.sessionmonitorexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nazeer.sessionmonitor.models.ScreenReportItem;
import com.nazeer.sessionmonitorexample.R;

import java.util.ArrayList;

/**
 * Created by nazeer on 5/21/16.
 */
public class ReportAdapter extends ArrayAdapter<ScreenReportItem>{
    private final Context context;
    ArrayList<ScreenReportItem>items;
    public ReportAdapter(Context context,ArrayList<ScreenReportItem>list) {
        super(context, 0,list);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.report_row_item,parent,false);
            vh=new ViewHolder();
            vh.nameTv= (TextView) convertView.findViewById(R.id.scrennNameTv);
            vh.durationTv=(TextView)convertView.findViewById(R.id.screenDurationTv);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        vh.durationTv.setText(getItem(position).getTotalDuration()/1000+" seconds");
        vh.nameTv.setText(getItem(position).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView nameTv,durationTv;
    }
}
