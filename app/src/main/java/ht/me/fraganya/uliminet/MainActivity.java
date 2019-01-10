package ht.me.fraganya.uliminet;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //@todo check if an account already exist
        boolean account_exists = false;

        if(account_exists){
            //@todo pass account data
            Intent startUlimiActivityIntent = new Intent(this,UlimiActivity.class);
            startActivity(startUlimiActivityIntent);
        }
        else{
            setContentView(R.layout.activity_main);
        }

    }

    public void onLoginBtnClicked(View view)
    {
         Intent loginIntent = new Intent(this,LoginActivity.class);
         startActivity(loginIntent);
    }

    public void onProceedBtnClicked(View view)
    {
        Intent signUpIntent = new Intent(this,SignupActivity.class);
        startActivity(signUpIntent);
    }
}
