package ht.me.fraganya.uliminet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

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
        String username = ((EditText)findViewById(R.id.usernameEdit)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEdit)).getText().toString();


        final HashMap<String,String> userDetails = new HashMap<>();

        userDetails.put("username",username);
        userDetails.put("password",password);

        class LoginTask extends AsyncTask<Void,Void,String>{
            ProgressDialog loginProgress;

            public LoginTask(LoginActivity activity){
                loginProgress = new ProgressDialog(activity);
            }


            @Override
            protected String doInBackground(Void... voids){
                HttpRequestHandler registerRequest = new HttpRequestHandler();
                Log.d("UserDetails",userDetails.toString());
                return registerRequest.sendPostRequest(APPURL.LOGIN,userDetails);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //display progress status to user
                loginProgress.setMessage("Authenticating");
                loginProgress.setTitle("Please wait");
                loginProgress.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("DBG",s);
                if(loginProgress.isShowing()){
                    loginProgress.dismiss();
                }


                //handle the response
                try{
                    JSONObject responseJson = new JSONObject(s);

                    //registration was successful and no errors
                    if(responseJson.getBoolean("status")){
                        JSONObject userData = responseJson.getJSONObject("user");

                        User user = new User(
                                userData.getInt("id"),
                                userData.getString("username"),
                                userData.getString("firstname"),
                                userData.getString("surname")
                        );

                        //store user in share preferences to start their session
                        SharedPreferencesMgr.getInstance(getApplicationContext()).login(user);

                        //start the main ulimi activity
                        startActivity(new Intent(getApplicationContext(),UlimiActivity.class));
                    }
                    else{
                        //registration was unsuccessful
                        Toast.makeText(getApplicationContext(),responseJson.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Some error occured",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }

        LoginTask loginTask = new LoginTask(LoginActivity.this);
        loginTask.execute();

    }

}
