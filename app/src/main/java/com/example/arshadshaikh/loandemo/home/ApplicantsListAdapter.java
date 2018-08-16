package com.example.arshadshaikh.loandemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arshadshaikh.loandemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicantsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ApplicantDetails>applicantDetailsArrayList;

    public ApplicantsListAdapter(Context context, ArrayList<ApplicantDetails> applicantsArrayList) {

        this.context=context;
        this.applicantDetailsArrayList=applicantsArrayList;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){

            case 1:

                return new HeadersView(LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_list_header_layout,parent,false));


            case 2:

                return new DataViews(LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_list_data_layout,parent,false));


        }

        return new DataViews(LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_list_data_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){

            case 1:

                HeadersView headers=(HeadersView) holder;


                break;

            case 2:

                DataViews dataViews= (DataViews) holder ;


                dataViews.firstNameTextView.setText(applicantDetailsArrayList.get(position).getFirstName());
                dataViews.lastNameTextView.setText(applicantDetailsArrayList.get(position).getLastName());
                dataViews.loanAmountTextView.setText(String.valueOf(applicantDetailsArrayList.get(position).getLoanAmount()));

                break;

        }
    }

    @Override
    public int getItemCount() {
        return applicantDetailsArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){

            return 1;
        }else{

            return 2;
        }


    }



    public  class HeadersView extends RecyclerView.ViewHolder{


        public HeadersView(View itemView) {
            super(itemView);
        }
    }

    public  class DataViews extends RecyclerView.ViewHolder{

        @BindView(R.id.firstNameTextView)TextView firstNameTextView;
        @BindView(R.id.lastNameTextView)TextView lastNameTextView;
        @BindView(R.id.loanAmountTextView)TextView loanAmountTextView;


        public DataViews(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
