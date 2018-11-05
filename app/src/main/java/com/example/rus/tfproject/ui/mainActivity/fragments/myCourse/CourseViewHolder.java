package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rus.tfproject.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CourseViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public static CourseViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_courses_recycler_view_item, parent, false);
        return new CourseViewHolder(view);
    }

    public void bind(String text){
        textView.setText(text);
    }

    public CourseViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.my_course_fragment_recycler_view_text_view);
    }
}
