package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT," + ACTIVE_CUSTOMER + " BOOL)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addone(CustomerModel cssmodel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME, cssmodel.getName());
        cv.put(CUSTOMER_AGE, cssmodel.getPin());
        cv.put(ACTIVE_CUSTOMER, cssmodel.Isactive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if (insert == -1)
            return false;
        else
            return true;
    }

    public List<CustomerModel> view_everyone() {

        List<CustomerModel> returnlist = new ArrayList<>();


        String queryString="SELECT * FROM "+ CUSTOMER_TABLE;

        SQLiteDatabase db= this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                int customerid= cursor.getInt(0);
                String customername= cursor.getString(1);
                int customerage=cursor.getInt(2);
                boolean customeractive= cursor.getInt(3)==1 ?true:false;

                CustomerModel newcustomer = new CustomerModel(customerid,customername,customerage,customeractive);
                returnlist.add(newcustomer);

            }while(cursor.moveToNext());
        }
        else{


        }
        cursor.close();
        db.close();

        return returnlist;
        }

        public boolean deleteone(CustomerModel cssmodel){

        SQLiteDatabase db= this.getWritableDatabase();
        String querystring= "DELETE FROM "+CUSTOMER_TABLE + " WHERE "+ ID +" = "+ cssmodel.getId();

            Cursor cursor = db.rawQuery(querystring, null);

            if(cursor.moveToFirst()){
                return  true;
            }
            else
                return false;
        }
    }


