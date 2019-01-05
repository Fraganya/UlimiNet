package ht.me.fraganya.uliminet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    ProgressDialog loginProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onSignupBtnClicked(View view)
    {
        Intent signUpIntent = new Intent(this,SignupActivity.class);
        startActivity(signUpIntent);
    }
    public void onLoginBtnClicked(View view)
    {
        //retrieve details and login user
        loginProgress = ProgressDialog.show(this,"Please wait","Authenticating ...", true);

        CountDownTimer timer  = new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){

            }

            @Override
            public void onFinish(){
                loginProgress.dismiss();
            }
        }.start();

        Intent startMainActivityIntent = new Intent(this,UlimiActivity.class);
        startActivity(startMainActivityIntent);
    }

}
