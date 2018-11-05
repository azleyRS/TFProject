package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.content.Context;
import android.view.ViewGroup;

import com.example.rus.tfproject.network.DTO.DetailedTask;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    List<DetailedTask> detailedTaskList;
    private Context context;

/*    List<String> testList;

    public CourseAdapter(List<String> testList) {
        this.testList = testList;
    }*/

    public CourseAdapter(List<DetailedTask> detailedTaskList, Context context) {
        this.detailedTaskList = detailedTaskList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bind(detailedTaskList.get(position));
    }

    @Override
    public int getItemCount() {
        return detailedTaskList.size();
    }

    public void setNewList(List<DetailedTask> testList2) {
        detailedTaskList.clear();
        detailedTaskList.addAll(testList2);
        Collections.sort(detailedTaskList);
        this.notifyDataSetChanged();
    }
}
