package ht.me.fraganya.uliminet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

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
    }
}
