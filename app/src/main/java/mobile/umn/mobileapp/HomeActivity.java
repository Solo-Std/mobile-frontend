package mobile.umn.mobileapp;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.umn.mobileapp.adapter.HomeListAdapter;
import mobile.umn.mobileapp.model.MasterItemRestClient;
import mobile.umn.mobileapp.entity.RequestHeader;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<RequestHeader> requests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((TextView) findViewById(R.id.text_request_count)).setText("You have " + requests.size() + " new requests");
                ((TextView) findViewById(R.id.text_fullname)).setText(getIntent().getStringExtra("fullname")
                + " - " + getIntent().getStringExtra("position"));
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeListAdapter adapter = new HomeListAdapter(requests);
        RecyclerView myView = (RecyclerView) findViewById(R.id.recycler_view);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);


        if (getIntent().getStringExtra("position").equals("Department Head")) {
            Intent i = new Intent(HomeActivity.this, DeptHeadActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (getIntent().getStringExtra("position").equals("Financial Controller")) {
            Intent i = new Intent(HomeActivity.this, FinanceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (getIntent().getStringExtra("position").equals("General Manager")) {
            Intent i = new Intent(HomeActivity.this, GeneralManagerActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (getIntent().getStringExtra("position").equals("Purchasing Manager")) {
            Intent i = new Intent(HomeActivity.this, PurchasingActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_depthead) {
            Intent i = new Intent(HomeActivity.this, DeptHeadActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_finance) {
            Intent i = new Intent(HomeActivity.this, FinanceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_purchasing) {
            Intent i = new Intent(HomeActivity.this, PurchasingActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);

        } else if (id == R.id.nav_gm) {
            Intent i = new Intent(HomeActivity.this, GeneralManagerActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(i);
            getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .remove("username")
                    .remove("password")
                    .commit();
        } else if (id == R.id.nav_requests) {
            startActivity(new Intent(HomeActivity.this, OngoingActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
