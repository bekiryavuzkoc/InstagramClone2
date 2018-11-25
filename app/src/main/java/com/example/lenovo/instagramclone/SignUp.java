package com.example.lenovo.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPunchPower;
    private EditText txtPunchSpeed;
    private EditText txtKickSpeed;
    private EditText txtKickPower;
    private TextView txtGetData;
    private String allKickBoxers;
    private Button btnGetKickBoxer;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        txtName=findViewById(R.id.txtName);
        txtPunchPower=findViewById(R.id.txtPunchPower);
        txtPunchSpeed=findViewById(R.id.txtPunchSpeed);
        txtKickSpeed=findViewById(R.id.txtKickSpeed);
        txtKickPower=findViewById(R.id.txtKickPower);
        txtGetData = findViewById(R.id.txtGetData);
        btnGetKickBoxer = findViewById(R.id.btnGetKickBoxer);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("XOebwW0XJK", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object!=null && e==null){
                            txtGetData.setText(object.get("name") + "- Punch Power: " + object.get("punch_power").toString());
                        }
                    }
                });
            }
        });
        btnGetKickBoxer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");


                queryAll.whereGreaterThanOrEqualTo("punch_power",100);
                queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){

                            if(objects.size()>0){
                            for(ParseObject kickBoxer: objects){
                                allKickBoxers = allKickBoxers + kickBoxer.get("name")  + "\n";
                            }
                                FancyToast.makeText(SignUp.this,
                                        allKickBoxers ,
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                            else{
                                FancyToast.makeText(SignUp.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }
                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,
                        SignUpLoginActivity.class);
                startActivity(intent);
            }
        });
    }



    public void helloWorldTapped(View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if(e==null){
//                    Toast.makeText(SignUp.this,"boxer is saved succesfully",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
          try {
              final ParseObject kickBoxer = new ParseObject("KickBoxer");
              kickBoxer.put("name", txtName.getText().toString());
              kickBoxer.put("punch_speed", Integer.valueOf(txtPunchSpeed.getText().toString()));
              kickBoxer.put("punch_power", Integer.valueOf(txtPunchPower.getText().toString()));
              kickBoxer.put("kick_speed", Integer.valueOf(txtKickSpeed.getText().toString()));
              kickBoxer.put("kick_power", Integer.valueOf(txtKickPower.getText().toString()));
              kickBoxer.saveInBackground(new SaveCallback() {
                  @Override
                  public void done(ParseException e) {

                      if (e == null) {
                          FancyToast.makeText(SignUp.this,
                                  "Kickboxer " + kickBoxer.get("name") + " added to the server",
                                  FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                      } else {
                          FancyToast.makeText(SignUp.this, e.getMessage(),
                                  FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                      }
                  }
              });
          } catch(Exception e){
              FancyToast.makeText(SignUp.this, e.getMessage(),
                      FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }

}
