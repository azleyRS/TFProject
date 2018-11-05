package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    List<String> testList;

    public CourseAdapter(List<String> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.bind(testList.get(position));
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public void setNewList(List<String> testList2) {
        testList.clear();
        testList.addAll(testList2);
        this.notifyDataSetChanged();
    }
}
