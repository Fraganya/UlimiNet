package ht.me.fraganya.uliminet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    ProgressDialog signupProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onLoginBtnClicked(View view)
    {
        Intent loginIntent = new Intent(this,Login.class);
        startActivity(loginIntent);
    }

    public void onSignupBtnClicked(View view)
    {
       //retrieve details and sign up to server
        //retrieve details and login user
        signupProgress = ProgressDialog.show(this,"Please wait","Registering your account ...", true);

        CountDownTimer timer  = new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished){

            }

            @Override
            public void onFinish(){
                signupProgress.dismiss();
            }
        }.start();


    }
}
