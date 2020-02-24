package com.mystartup.iaestejecrc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import android.view.View;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, PunchSpeed, PunchPower, KickSpeed, KickPower;
    private TextView txtGetData;
    private Button btnGetAllData;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(MainActivity.this);
        edtName = findViewById(R.id.edtName);
        PunchPower = findViewById(R.id.PunchPower);
        PunchSpeed = findViewById(R.id.PunchSpeed);
        KickPower = findViewById(R.id.KickPower);
        KickSpeed = findViewById(R.id.KickSpeed);
        txtGetData = findViewById(R.id.txtGetData);

        btnGetAllData = findViewById(R.id.btnGetAllData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("5sTu6LNQ9E", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if (object != null && e == null){

                            txtGetData.setText(object.get("Name") + "" + " has Punch Power " + object.get("PunchPower")+ " and kick Power " + object.get("KickPower"));
                        } else{
                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                        }
                    }
                });

            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                allKickBoxers= "";
                ParseQuery<ParseObject>queryAll = ParseQuery.getQuery("KickBoxer");

                queryAll.whereGreaterThan("PunchPower", 200);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null) {
                            if (objects.size()>0){
                                for (ParseObject kickBoxer: objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("Name") + " has Punch Power " + kickBoxer.get("PunchPower")+ " and kick Power " + kickBoxer.get("KickPower") + "\n";
                                }


                                FancyToast.makeText(MainActivity.this, allKickBoxers , FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();


                            } else{
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                            }
                        }

                    }
                });

            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("Name", edtName.getText().toString());
            kickBoxer.put("PunchSpeed", Integer.parseInt(PunchSpeed.getText().toString()));
            kickBoxer.put("PunchPower", Integer.parseInt(PunchPower.getText().toString()));
            kickBoxer.put("KickSpeed", Integer.parseInt(KickSpeed.getText().toString()));
            kickBoxer.put("KickPower", Integer.parseInt(KickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickBoxer.get("Name") + " is Successfully Added !!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                    }
                }
            });
        } catch(Exception e) {
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }


    }

}




