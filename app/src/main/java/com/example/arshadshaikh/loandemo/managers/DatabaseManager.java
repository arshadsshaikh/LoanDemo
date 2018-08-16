package com.example.arshadshaikh.loandemo.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arshadshaikh.loandemo.database.DBConstant;
import com.example.arshadshaikh.loandemo.database.DBHelper;
import com.example.arshadshaikh.loandemo.home.ApplicantDetails;

import java.util.ArrayList;

/**
 * Created by arshad.shaikh on 8/14/2018.
 */

public class DatabaseManager {

    private static DBHelper dbHelper;
    static Context mContext;

    private int mOpenCounter;
    private static DatabaseManager instance;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager();
            dbHelper = new DBHelper(context);
            mContext = context;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
            dbHelper = new DBHelper(mContext);
        }
        return instance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter++;
        if (mOpenCounter == 1) {
            mDatabase = dbHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter--;
        if (mOpenCounter == 0) {
            mDatabase.close();
        }
    }

    public static long insertData(String firstName, String lastName, String email,
                                  double loanAmount, String panCard, String aadharCard, String voterId) {
        ContentValues values = new ContentValues();
        values.put(DBConstant.FIRSTNAME, firstName);
        values.put(DBConstant.LASTNAME, lastName);
        values.put(DBConstant.EMAIL, email);
        values.put(DBConstant.LOANAMOUNT, loanAmount);
        values.put(DBConstant.PAN, panCard);
        values.put(DBConstant.AADHAR, aadharCard);
        values.put(DBConstant.VOTER, voterId);
        long insertedID = DatabaseManager
                .getInstance()
                .openDatabase()
                .insertWithOnConflict(DBConstant.T_RECENT, null, values,
                        SQLiteDatabase.CONFLICT_REPLACE);
        DatabaseManager.getInstance().closeDatabase();
        return insertedID;
    }


    public static ArrayList<ApplicantDetails> getAllData() {
        ArrayList<ApplicantDetails> applicantsList = new ArrayList<>();
      //  String selectQuery = "SELECT * FROM " + DBConstant.T_RECENT ;

        String selectQuery = "SELECT * FROM " + DBConstant.T_RECENT +  " ORDER BY " + DBConstant.LOANAMOUNT + " DESC ";

        Cursor c = DatabaseManager.getInstance().openDatabase()
                .rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                ApplicantDetails applicantDetails = new ApplicantDetails();

                applicantDetails.setFirstName(c.getString(c.getColumnIndex(DBConstant.FIRSTNAME)));
                applicantDetails.setLastName(c.getString(c.getColumnIndex(DBConstant.LASTNAME)));
                applicantDetails.setEmailId(c.getString(c.getColumnIndex(DBConstant.EMAIL)));
                applicantDetails.setLoanAmount(c.getDouble(c.getColumnIndex(DBConstant.LOANAMOUNT)));
                applicantDetails.setPanNumber(c.getString(c.getColumnIndex(DBConstant.PAN)));
                applicantDetails.setAadharNumber(c.getString(c.getColumnIndex(DBConstant.AADHAR)));
                applicantDetails.setVoterId(c.getString(c.getColumnIndex(DBConstant.VOTER)));



                applicantsList.add(applicantDetails);
            } while (c.moveToNext());
        }
        c.close();
        DatabaseManager.getInstance().closeDatabase();
        return applicantsList;
    }

}
