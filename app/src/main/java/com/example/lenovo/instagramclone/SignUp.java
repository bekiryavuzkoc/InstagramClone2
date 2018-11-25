package com.example.lenovo.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPunchPower;
    private EditText txtPunchSpeed;
    private EditText txtKickSpeed;
    private EditText txtKickPower;

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
