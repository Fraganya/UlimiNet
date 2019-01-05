package ht.me.fraganya.uliminet;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class UlimiActivity extends AppCompatActivity {

    private DrawerLayout appDrawer;
    private ActionBarDrawerToggle toggler;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulimi);

        appDrawer = findViewById(R.id.app_drawer);
        navView = findViewById(R.id.nav_view);
        //toggler = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add drawer toggler
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true); //set menu option as selected to persist highlight
                int id = menuItem.getItemId();
                appDrawer.closeDrawers();

                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(UlimiActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.seeds:
                        Toast.makeText(UlimiActivity.this,"Seeds",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.feeds:
                        Toast.makeText(UlimiActivity.this,"Feeds",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.market:
                        Toast.makeText(UlimiActivity.this,"Market",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(UlimiActivity.this,"Settings",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;

                }

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
}
