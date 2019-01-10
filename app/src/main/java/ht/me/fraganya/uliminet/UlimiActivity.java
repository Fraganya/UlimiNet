package ht.me.fraganya.uliminet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UlimiActivity extends AppCompatActivity {

    private DrawerLayout appDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check if a user is logged in
        if(!SharedPreferencesMgr.getInstance(this).isLoggedIn()){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            return;
        }


        setContentView(R.layout.activity_ulimi);

        //set app drawer and navigation
        appDrawer = findViewById(R.id.app_drawer);
        navView = findViewById(R.id.nav_view);

        //set user details in nav header
        User user = SharedPreferencesMgr.getInstance(this).getUser();
        View navHeader = navView.getHeaderView(0);
        ((TextView)navHeader.findViewById(R.id.user_fullname)).setText(user.getFirstname()+" "+user.getSurname());
        ((TextView)navHeader.findViewById(R.id.user_username)).setText("@UlimiNet/"+user.getUsername());
        //toggler = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add drawer toggler
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //set listener
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true); //set menu option as selected to persist highlight
                int id = menuItem.getItemId();
                appDrawer.closeDrawers();

                displaySelectedScreen(id);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                appDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedScreen(int screenId)
    {
        Fragment fragment = null;

        switch(screenId)
        {
            case R.id.home:
                Toast.makeText(UlimiActivity.this,"Home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.seeds:
                Toast.makeText(UlimiActivity.this,"Seeds",Toast.LENGTH_SHORT).show();
                break;
            case R.id.feeds:
                fragment = new FeedsFragment();
                break;
            case R.id.market:
                Toast.makeText(UlimiActivity.this,"Market",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                fragment = new SettingsFragment();
                break;
            case R.id.personal_details_opt:
                Toast.makeText(UlimiActivity.this,"Personal Details update",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout_opt:
                logout();
                break;
        }

        if(fragment != null)
        {
            FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.content_frame,fragment);
            fTrans.commit();
        }
    }

    public void logout()
    {
        SharedPreferencesMgr.getInstance(this).logout();
        startActivity(new Intent(UlimiActivity.this,LoginActivity.class));
    }


}
