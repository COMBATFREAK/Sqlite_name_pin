package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editname,editpin;
    private Button viewallbtn, addbtn;
    private Switch isactiveswitch;
    private ListView list;
    private ArrayAdapter customeradapter;
    private List<CustomerModel> everyone;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper= new DatabaseHelper(MainActivity.this);

        isinit();
        extracted();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel csmodel;
                try {
                    csmodel = new CustomerModel(-1,editname.getText().toString(), Integer.parseInt(editpin.getText().toString()), isactiveswitch.isChecked());
                    Toast.makeText(MainActivity.this, csmodel.toString(), Toast.LENGTH_SHORT).show();
                    DatabaseHelper databaseHelper= new DatabaseHelper(MainActivity.this);
                    boolean addone = databaseHelper.addone(csmodel);
                    Toast.makeText(MainActivity.this, "Customer added succesfully", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error adding Customer as Fields were left blank", Toast.LENGTH_SHORT).show();
                    csmodel=new CustomerModel(-1,"error",0,false);
                }
                editname.setText("");
                editpin.setText("");
                editname.setHint("Name");
                editpin.setHint("Pin");


                extracted();
            }
        });

        viewallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customeradapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,dbhelper.view_everyone());



                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel clickedcustomer =(CustomerModel) adapterView.getItemAtPosition(i);
                dbhelper.deleteone(clickedcustomer);
                extracted();
            }
        });

    }

    private void extracted() {
        customeradapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1,dbhelper.view_everyone());
        list.setAdapter(customeradapter);
    }


    public void isinit(){
        editname=findViewById(R.id.edtname);
        editpin=findViewById(R.id.edtpin);

        viewallbtn=findViewById(R.id.view_btn);
        addbtn=findViewById(R.id.add_btn);

        isactiveswitch=findViewById(R.id.active);

        list=findViewById(R.id.list_view);
    }


}

