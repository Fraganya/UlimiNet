package ht.me.fraganya.uliminet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPreferencesMgr {

    private static final String SHARED_PREF_NAME="userdetails";
    private static final String KEY_USERNAME="keyusername";
    private static final String KEY_FIRSTNAME="keyfirstname";
    private static final String KEY_SURNAME="keysurname";
    private static final String KEY_ID="keyid";


    private static SharedPreferencesMgr mgrInstance;
    private static Context mgrCtx;

    private SharedPreferencesMgr(Context context){
        mgrCtx = context;
    }

    public static synchronized SharedPreferencesMgr getInstance(Context context){
        if(mgrInstance == null){
            mgrInstance = new SharedPreferencesMgr(context);
        }

        return mgrInstance;
    }


    //method to let a user login
    public void login(User user){
        SharedPreferences sharedPreferences = mgrCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_USERNAME,user.getUsername());
        editor.putString(KEY_FIRSTNAME,user.getFirstname());
        editor.putString(KEY_SURNAME,user.getSurname());

        editor.apply();
    }


    //check if a user is logged in
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mgrCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null) != null;
    }


    public User getUser(){
        SharedPreferences sharedPreferences = mgrCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERNAME,null),
                sharedPreferences.getString(KEY_FIRSTNAME,null),
                sharedPreferences.getString(KEY_SURNAME,null)
        );
    }

    public void logout(){
        SharedPreferences sharedPreferences = mgrCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        mgrCtx.startActivity(new Intent(mgrCtx,Login.class));
    }

}
