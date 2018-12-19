package ht.me.fraganya.uliminet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

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
    }

}
