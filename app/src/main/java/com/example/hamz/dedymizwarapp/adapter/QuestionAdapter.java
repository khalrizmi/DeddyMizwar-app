package com.example.hamz.dedymizwarapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.model.Answer;
import com.example.hamz.dedymizwarapp.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Question> groups;

    //deklarasi var context dan groups




    public QuestionAdapter(Context context, ArrayList<Question> groups) {
        this.context = context;
        this.groups = groups;

        //constructor
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {


        ArrayList<Answer> childList = groups.get(groupPosition).getAnswers();

        return childList.get(childPosition);

        //mengambil nilai atau value dari child
    }



    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
        //mengambil id dari child
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        Answer child = (Answer) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.question_item,null);

        }

        TextView textView = (TextView)convertView.findViewById(R.id.lblListItem);

        String text = child.getAnswer();
        textView.setText(text);

        //menampilkan data ke view layout child_item.xml
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
        //mengambil nilai atau value dari jumlah grup
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<Answer> child ;
        child = groups.get(groupPosition).getAnswers();
        //Mengambil jumlah child berdasarkan grup tertentu
        return child.size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        // Mengambil data yang terkait object grup
        return groups.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
//Mengambil id yang terkait grup
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
        //Menunjukan apakah id dari grup dan child stabil terkait perubahan data di dalamnya
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Question group = (Question) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.question_group,null);

            TextView textView = (TextView)convertView.findViewById(R.id.lblListHeader);
            String text = group.getQuestion();

            textView.setText(text);


        }


        // //menampilkan data ke view layout grup_item.xml
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
        //Menunjukan apakah posisi child tertentu dapat di selectable
    }

}
