package com.example.rus.tfproject.ui.testQuestions;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.rus.tfproject.R;
import com.example.rus.tfproject.network.DTO.TestQuestion;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment {

    private static final String ARGS_TITLE = "title";
    private static final String ARGS_CONTEST_URL = "contest_url";
    private static final String ARGS_STATUS = "status";


    private static final String ARGS_TEST_QUESTION = "testQuestion";
    TestQuestion testQuestion;
    TextView questionTextView;
    private RadioGroup radioGroup;
    private List<Boolean> answerList;
    private int checkBoxId = 0;
    OnPassPressedListener passPressedListener;
    OnNextPressedListener nextPressedListener;
    private String result ="";

    public static TestFragment newInstance(String stringExtra, String extra, String s) {
        Log.v("tag", "TestFragment created");
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, stringExtra);
        args.putString(ARGS_CONTEST_URL, extra);
        args.putString(ARGS_STATUS, s);
        fragment.setArguments(args);
        return fragment;
    }

    public static TestFragment newInstance(TestQuestion testQuestion) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_TEST_QUESTION,testQuestion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        passPressedListener = (OnPassPressedListener) context;
        nextPressedListener = (OnNextPressedListener) context;
    }

    private void initViews(View view) {
        testQuestion = getArguments().getParcelable(ARGS_TEST_QUESTION);
        questionTextView = view.findViewById(R.id.question_text_view);
        questionTextView.setText(testQuestion.problem.cmsPage.unstyledStatement);
        LinearLayout layout = view.findViewById(R.id.test_fragment_layout);
        String lastSubmission = "";
        if (testQuestion.lastSubmission!=null){
            lastSubmission = testQuestion.lastSubmission.file;
            Log.v("sub", testQuestion.lastSubmission.file);
        }
        if (testQuestion.problem.problemType.equals("SELECT_ONE")){
            radioGroup = new RadioGroup(getContext());
            for (int i = 0; i < testQuestion.problem.answerChoices.size(); i++){
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setId(i);
                String answerChoices = testQuestion.problem.answerChoices.get(i);
                radioButton.setText(answerChoices);
                if (!lastSubmission.isEmpty() && lastSubmission.charAt(i)=='1'){
                    radioButton.toggle();
                }
                radioGroup.addView(radioButton);
            }
            layout.addView(radioGroup);
        } else {
            answerList = new ArrayList<>();

            for (int i = 0; i < testQuestion.problem.answerChoices.size(); i++){
                answerList.add(false);

                if (!lastSubmission.isEmpty() && lastSubmission.charAt(i)=='1'){
                    answerList.set(i,true);
                }

                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(testQuestion.problem.answerChoices.get(i));
                checkBox.setId(i);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        boolean answer = answerList.get(checkBox.getId());
                        answerList.set(checkBox.getId(), !answer);
                    }
                });
                layout.addView(checkBox);
            }

           /* for (String answerChoices : testQuestion.problem.answerChoices){
                answerList.add(false);
                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(answerChoices);
                checkBox.setId(checkBoxId);
                checkBoxId++;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        boolean answer = answerList.get(checkBox.getId());
                        answerList.set(checkBox.getId(), !answer);
                    }
                });
                layout.addView(checkBox);
            }*/
        }

        Button passButton = view.findViewById(R.id.pass_button);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passPressedListener.onPassPressed();
            }
        });

        Button nextButton = view.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (testQuestion.problem.problemType.equals("SELECT_ONE")){
                    for (int i = 0; i< testQuestion.problem.answerChoices.size(); i++){
                        if (radioGroup.getCheckedRadioButtonId()==i){
                            result = result + "1";
                        } else {
                            result = result + "0";
                        }
                    }
                } else {
                    for (int i = 0; i< testQuestion.problem.answerChoices.size(); i++){
                        if (answerList.get(i)){
                            result = result + "1";
                        } else {
                            result = result + "0";
                        }
                    }
                }
                nextPressedListener.onNextPressed(result);
            }
        });
    }

    /*private void initViews(View view) {
        TextView titleTextView = view.findViewById(R.id.test_title);
        TextView urlTextView = view.findViewById(R.id.test_contest_url);
        TextView statusTextView = view.findViewById(R.id.test_status);
        if (getArguments() != null) {
            titleTextView.setText(getArguments().getString(ARGS_TITLE));
            urlTextView.setText(getArguments().getString(ARGS_CONTEST_URL));
            statusTextView.setText(getArguments().getString(ARGS_STATUS));
        }

        startButton = view.findViewById(R.id.start_text_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest(view);
            }
        });
    }

    private void startTest(View view) {
        TFApi.getInstance().getHomeworksEndpoint().startTest(getArguments().getString(ARGS_CONTEST_URL), new JsonObject())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<Object>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(retrofit2.Response<Object> objectResponse) {
                        if (objectResponse.body() instanceof TestStatusAnswer){
                            TextView textView = view.findViewById(R.id.question_text_view);
                            textView.setText(objectResponse.body().toString());
                        } else {
                            TextView textView = view.findViewById(R.id.question_text_view);
                            textView.setText(objectResponse.toString());
                        }

                        startButton.setVisibility(View.GONE);

                        loadQuestions();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void loadQuestions() {
        TFApi.getInstance().getHomeworksEndpoint().getQuestionsList(getArguments().getString(ARGS_CONTEST_URL))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<TestQuestion>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<TestQuestion> testQuestions) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }*/

}
