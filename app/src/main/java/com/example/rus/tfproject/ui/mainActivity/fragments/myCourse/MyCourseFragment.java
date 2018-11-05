package com.example.rus.tfproject.ui.mainActivity.fragments.myCourse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.HomeWorks;
import com.example.rus.tfproject.network.DTO.Homework;
import com.example.rus.tfproject.network.TFApi;

import java.util.ArrayList;
import java.util.List;

public class MyCourseFragment extends Fragment {
    RecyclerView recyclerView;
    List<String> testListGlobal = new ArrayList<>();

    public static MyCourseFragment newInstance() {
        Log.v("tag", "MyCourse created");
        return new MyCourseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {

        recyclerView = view.findViewById(R.id.my_course_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            testList.add("Number " + i);
        }
        CourseAdapter adapter = new CourseAdapter(testList);
        recyclerView.setAdapter(adapter);

        if (testListGlobal.isEmpty()) {

            TFApi.getInstance().getHomeworksEndpoint().getHomeWorks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<HomeWorks>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(HomeWorks homeWorks) {
                            List<Homework> homeworkList = homeWorks.homeworks;
                            List<String> testList2 = new ArrayList<>();
                            for (Homework homework : homeworkList) {
                                testList2.add(homework.title);
                            }
                            adapter.setNewList(testList2);

                            testListGlobal = testList2;
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.v("TAg", e.getMessage());

                        }
                    });

        } else {
            adapter.setNewList(testListGlobal);
        }

        /*
        recyclerView = view.findViewById(R.id.my_course_fragment_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        List<String> testList = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            testList.add("Number " + i);
        }
        recyclerView.setAdapter(new CourseAdapter(testList));*/
    }
}
