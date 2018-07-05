package mobile.umn.mobileapp;

import android.app.Activity;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import entity.MasterCard;
import mobile.umn.mobileapp.adapter.DeptHeadListAdapter;
import mobile.umn.mobileapp.model.MasterCardRestClient;

public class DeptHeadActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public List<MasterCard> masterCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_head);


        mRecyclerView = (RecyclerView) findViewById(R.id.dh_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {
            new HttpRequestAsk().execute();
            //masterCards.add(new MasterCard(123,1123));
//            mAdapter = new OngoingRequestListAdapter(masterCards);
//            mRecyclerView.setAdapter(mAdapter);
            System.out.println("itemcount:" + masterCards.size());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("berhasil");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dh_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((TextView) findViewById(R.id.text_request_count)).setText("You have " + masterCards.size() + " new requests");
                ((TextView) findViewById(R.id.text_fullname)).setText(getIntent().getStringExtra("fullname")
                        + " - " + getIntent().getStringExtra("position"));
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dh_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pr) {
            Intent i = new Intent(DeptHeadActivity.this, PurchaseRequestActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", masterCards.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_depthead) {
            Intent i = new Intent(DeptHeadActivity.this, DeptHeadActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", masterCards.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_finance) {
            Intent i = new Intent(DeptHeadActivity.this, FinanceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", masterCards.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_purchasing) {
            Intent i = new Intent(DeptHeadActivity.this, PurchasingActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", masterCards.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);

        } else if (id == R.id.nav_gm) {
            Intent i = new Intent(DeptHeadActivity.this, GeneralManagerActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", masterCards.size());
            i.putExtra("fullname", getIntent().getStringExtra("fullname"));
            i.putExtra("position", getIntent().getStringExtra("position"));
            startActivity(i);

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(DeptHeadActivity.this, LoginActivity.class);
            startActivity(i);
            getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .remove("username")
                    .remove("password")
                    .commit();
        } else if (id == R.id.nav_requests) {
            startActivity(new Intent(DeptHeadActivity.this, OngoingActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.dh_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class HttpRequestAsk extends AsyncTask<Void, Void, List<MasterCard>> {

        @Override
        protected List<MasterCard> doInBackground(Void... voids) {
            MasterCardRestClient masterCardRestClient = new MasterCardRestClient();
            masterCards = masterCardRestClient.findAll();
            System.out.println("itemcount:" + masterCards.size());
            return masterCardRestClient.findAll();
        }

        @Override
        protected void onPostExecute(List<MasterCard> masterCards) {
            mRecyclerView = (RecyclerView) findViewById(R.id.dh_recycler_view);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new DeptHeadListAdapter(
                    masterCards, getIntent().getStringExtra("position"),args0->{
                        new HttpRequestAsk().execute();
            });
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}