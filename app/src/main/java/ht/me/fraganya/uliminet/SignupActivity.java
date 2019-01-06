package ht.me.fraganya.uliminet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    private String user_pref_lang = "En";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onLoginBtnClicked(View view) {
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }

    public void onSignupBtnClicked(View view) {

        final HashMap<String, String> userDetails = new HashMap<>();

        //retrieve details and sign up to server
        String username = (((EditText) findViewById(R.id.usernameEdit)).getText().toString());
        String password = (((EditText) findViewById(R.id.passwordEdit)).getText().toString());
        String firstname = (((EditText) findViewById(R.id.firstnameEdit)).getText().toString());
        String surname = (((EditText) findViewById(R.id.surnameEdit)).getText().toString());
        String specialty = (((Spinner) findViewById(R.id.specialtyEdit)).getSelectedItem().toString());
        String location = (((EditText) findViewById(R.id.locationEdit)).getText().toString());
        String email = (((EditText) findViewById(R.id.emailEdit)).getText().toString());
        String phone = (((EditText) findViewById(R.id.phoneEdit)).getText().toString());


        Log.i("PHONE", phone);
        Log.i("username", username);
        Log.i("password", password);
        Log.i("firstname", firstname);
        Log.i("specialty", specialty);
        Log.i("email", email);


        userDetails.put("username", username);
        userDetails.put("password", password);
        userDetails.put("firstname", firstname);
        userDetails.put("surname", surname);
        userDetails.put("specialization", specialty);
        userDetails.put("pref_lang", user_pref_lang);
        userDetails.put("location", location);
        userDetails.put("phone_number", phone);
        userDetails.put("email", email);

        if (!validateDetails(userDetails)) {
            return;
        }


        class RegistrationTask extends AsyncTask<Void,Void,String>{
            ProgressDialog signupProgress;

            public RegistrationTask(SignupActivity activity){
                signupProgress = new ProgressDialog(activity);
            }


            @Override
            protected String doInBackground(Void... voids){
               HttpRequestHandler registerRequest = new HttpRequestHandler();
               Log.d("UserDetails",userDetails.toString());
               return registerRequest.sendPostRequest(APPURL.REGISTER,userDetails);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //display progress status to user
                signupProgress.setMessage("Registering your account");
                signupProgress.setTitle("Please wait");
                signupProgress.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("DBG",s);
                if(signupProgress.isShowing()){
                    signupProgress.dismiss();
                }


                //handle the response
                try{
                    JSONObject responseJson = new JSONObject(s);

                    //registration was successful and no errors
                    if(responseJson.getBoolean("status")){
                        Toast.makeText(getApplicationContext(),responseJson.getString("message"),Toast.LENGTH_LONG).show();
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


        RegistrationTask registrationTask = new RegistrationTask(SignupActivity.this);
        registrationTask.execute();
    }

    private boolean validateDetails(HashMap<String, String> userdDetails) {
        //validate each of the fields
        boolean validity = true;
        if (userdDetails.get("username").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "The username is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("firstname").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "Firstname is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("surname").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "Surname is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("phone_number").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "Phone is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("password").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "The password is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("location").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "Location is required", Toast.LENGTH_LONG).show();
            validity = false;
        } else if (userdDetails.get("specialization").trim().isEmpty()) {
            Toast.makeText(SignupActivity.this, "Specialty is required", Toast.LENGTH_LONG).show();
            validity = false;
        }

        return validity;

    }
}
