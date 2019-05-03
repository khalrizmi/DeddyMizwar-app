package com.example.hamz.dedymizwarapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.adapter.GalleryAdapter;
import com.example.hamz.dedymizwarapp.adapter.QuestionAdapter;
import com.example.hamz.dedymizwarapp.api.ApiClient;
import com.example.hamz.dedymizwarapp.api.ApiInterface;
import com.example.hamz.dedymizwarapp.list.QuestionList;
import com.example.hamz.dedymizwarapp.model.Answer;
import com.example.hamz.dedymizwarapp.model.Question;
import com.example.hamz.dedymizwarapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {

    private String TAG = QuestionsActivity.class.getSimpleName();

    Toolbar toolbar;

    QuestionAdapter listAdapter;
    ExpandableListView expListView;
    FloatingActionButton fab;

    ArrayList<Question> questions;

    ApiInterface apiInterface;
//    AlertDialog dialog;

    ProgressDialog mProgressDialog;
    RelativeLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        container = findViewById(R.id.container);
        container.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tanya Jawab");

        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuestionsActivity.this, AskActivity.class));
            }
        });

        expListView = (ExpandableListView) findViewById(R.id.listview);

        apiInterface = new ApiClient().getClient().create(ApiInterface.class);
//        dialog = new SpotsDialog(this);

        // preparing list data
//        questions = inputData();
        questions = new ArrayList<>();

        listAdapter = new QuestionAdapter(this, questions);

        expListView.setAdapter(listAdapter);

        /* onClick */
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                String namagrup = questions.get(groupPosition).getQuestion();
//
//                ArrayList<Answer> childList = questions.get(groupPosition).getAnswers();
//
//                String namachild = childList.get(childPosition).getAnswer();
//
//                Toast.makeText(QuestionsActivity.this, "Group : "+namagrup+" Child : "+namachild, Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
        /*   OnExpand    */
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                String namagrup = questions.get(groupPosition).getQuestion();
//                Toast.makeText(QuestionsActivity.this, "Group : "+namagrup+" dibuka", Toast.LENGTH_SHORT).show();
//            }
//        });
//
        /*   OnCollapse    */
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                String namagrup = questions.get(groupPosition).getQuestion();
//                Toast.makeText(QuestionsActivity.this, "Group : "+namagrup+" ditutup", Toast.LENGTH_SHORT).show();
//            }
//        });

        loadJSON();

    }

    private void loadJSON()
    {
        questions.clear();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
        apiInterface.questions().enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                String http_status = Integer.toString(response.code()); // get http status

                if (response.isSuccessful()) {
                    questions = response.body().getQuestions();
                    listAdapter = new QuestionAdapter(QuestionsActivity.this, questions);
                    expListView.setAdapter(listAdapter);

                    container.setVisibility(View.VISIBLE);

                    mProgressDialog.cancel();
                }

                Log.d(TAG, "onResponse: HTTP status = " + http_status);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private ArrayList<Question> inputData()
    {
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Answer> answers;

        answers = new ArrayList<>();
        Question question1 = new Question();

        question1.setQuestion("Pertanyaan 1 ?");

        Answer answer1 = new Answer();
        answer1.setAnswer("Jawab 1");
        answers.add(answer1);

        Answer answer2 = new Answer();
        answer2.setAnswer("Jawab 2");
        answers.add(answer2);

        question1.setAnswers(answers);

        answers = new ArrayList<>();

        Question question2 = new Question();
        question2.setQuestion("Pertanyaan 2 ?");

        Answer an1 = new Answer();
        an1.setAnswer("Halo");
        answers.add(an1);

        question2.setAnswers(answers);

        questions.add(question1);
        questions.add(question2);

        return questions;

    }


}
