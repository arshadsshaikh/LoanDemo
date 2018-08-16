package com.example.arshadshaikh.loandemo.home;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arshadshaikh.loandemo.R;
import com.example.arshadshaikh.loandemo.managers.DatabaseManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.applicantsRecyclerView)RecyclerView applicantsRecyclerView;
    @BindView(R.id.addApplicantButton)FloatingActionButton addApplicantButton;
    private Context context;

    private ArrayList<ApplicantDetails>applicantsArrayList;
    private ApplicantsListAdapter applicantsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context=HomeActivity.this;
        ButterKnife.bind(this);

        getApplicantsData();


    }

    private void getApplicantsData() {

        applicantsArrayList= new ArrayList<>();

        applicantsArrayList= DatabaseManager.getAllData();
        applicantsRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        applicantsListAdapter= new ApplicantsListAdapter(context,applicantsArrayList);
        applicantsRecyclerView.setAdapter(applicantsListAdapter);



    }

    @OnClick(R.id.addApplicantButton)
    void addApplicant(){

        Intent intent= new Intent(context,AddApplicantActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();

        getApplicantsData();
    }
}
