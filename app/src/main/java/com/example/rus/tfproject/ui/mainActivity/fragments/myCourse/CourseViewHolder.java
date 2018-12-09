package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.DetailedTask;
import com.example.rus.tfproject.ui.testQuestions.TestContainerActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CourseViewHolder extends RecyclerView.ViewHolder {
    TextView testTitle, testStatus;
    private DetailedTask tast;
    private Context context;

    public static CourseViewHolder create(ViewGroup parent, Context context){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_courses_recycler_view_item, parent, false);
        return new CourseViewHolder(view, context);
    }

    public void bind(DetailedTask task){
        testTitle.setText(task.title);
        testStatus.setText(task.contestInfo.contestStatus.status);
        this.tast = task;
    }

    public CourseViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        testTitle = itemView.findViewById(R.id.my_course_fragment_recycler_view_text_view);
        testStatus = itemView.findViewById(R.id.test_status_text_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTestContainerActivity();
            }
        });
    }

    private void goToTestContainerActivity() {
        Intent intent = TestContainerActivity.newIntent(context, tast);
        context.startActivity(intent);
    }
}
