package com.example.rus.tfproject.ui.testQuestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rus.tfproject.MyApplication;
import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.DetailedTask;
import com.example.rus.tfproject.network.DTO.TestQuestion;
import com.example.rus.tfproject.network.TFApi;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class TestContainerActivity extends AppCompatActivity implements OnPassPressedListener, OnNextPressedListener {
    private static final String TITLE_EXTRA = "title";
    private static final String CONTEST_URL_EXTRA = "contest_url";
    private static final String STATUS_EXTRA = "status";
    FragmentManager fragmentManager;
    TextView questionNumberTextView;

    List<TestQuestion> testQuestionList = new ArrayList<>();
    private Button startButton;
    private int currentQuestion = 0;

    public static Intent newIntent(Context context, DetailedTask tast) {
        Intent intent = new Intent(context, TestContainerActivity.class);
        intent.putExtra(TITLE_EXTRA, tast.title);
        intent.putExtra(CONTEST_URL_EXTRA, tast.contestInfo.contestUrl);
        intent.putExtra(STATUS_EXTRA, tast.contestInfo.contestStatus.status);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_container);

        init();
    }

    private void init() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(TITLE_EXTRA));
        }



        fragmentManager = getSupportFragmentManager();

        startButton = findViewById(R.id.start_test_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });
        questionNumberTextView = findViewById(R.id.test_number_text_view);
        questionNumberTextView.setVisibility(View.GONE);

/*        TestFragment fragment = TestFragment.newInstance(getIntent().getStringExtra(TITLE_EXTRA),
                getIntent().getStringExtra(CONTEST_URL_EXTRA),
                getIntent().getStringExtra(STATUS_EXTRA));

        //add tag later
        fragmentManager.beginTransaction().replace(R.id.test_fragment_container, fragment).commit();*/
    }

    private void startTest() {
        TFApi.getInstance().getHomeworksEndpoint().startTest(getIntent().getStringExtra(CONTEST_URL_EXTRA), new JsonObject())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<retrofit2.Response<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(retrofit2.Response<Object> objectResponse) {
                        loadTestQuestions();
                        startButton.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadTestQuestions(){
        TFApi.getInstance().getHomeworksEndpoint().getQuestionsList(getIntent().getStringExtra(CONTEST_URL_EXTRA))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TestQuestion>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<TestQuestion> testQuestions) {
                        testQuestionList = testQuestions;
                        questionNumberTextView.setVisibility(View.VISIBLE);
                        questionNumberTextView.setText(String.valueOf(currentQuestion +1) + " / " + testQuestionList.size());
                        loadQuestion(currentQuestion);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadQuestion(int currentQuestion) {
        TFApi.getInstance().getHomeworksEndpoint().getQuestion(getIntent().getStringExtra(CONTEST_URL_EXTRA), String.valueOf(currentQuestion+1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TestQuestion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TestQuestion testQuestion) {
                        //questionNumberTextView.setText(testQuestion.toString());
                        TestFragment fragment = TestFragment.newInstance(testQuestion);
                        fragmentManager.beginTransaction().replace(R.id.test_fragment_container, fragment).commit();
                    }

                    @Override
                    public void onError(Throwable e) {
                        questionNumberTextView.setText(e.getMessage());

                    }
                });


        //TestFragment fragment = TestFragment.newInstance(testQuestionList.get(currentQuestion));
        //fragmentManager.beginTransaction().replace(R.id.test_fragment_container, fragment).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPassPressed() {
        if (currentQuestion > testQuestionList.size()-2){
            currentQuestion = 0;
        } else {
            currentQuestion++;
        }
        loadQuestion(currentQuestion);
    }

    @Override
    public void onNextPressed(String answer) {


        TFApi.getInstance().getHomeworksEndpoint().answerQuestion(getIntent().getStringExtra(CONTEST_URL_EXTRA), String.valueOf(currentQuestion), answer, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TestQuestion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TestQuestion testQuestion) {
                        //questionNumberTextView.setText(testQuestion.toString());
                        TestFragment fragment = TestFragment.newInstance(testQuestion);
                        fragmentManager.beginTransaction().replace(R.id.test_fragment_container, fragment).commit();
                    }

                    @Override
                    public void onError(Throwable e) {
                        questionNumberTextView.setText(e.getMessage());
                    }
                });
    }
}
