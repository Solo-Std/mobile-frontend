package mobile.umn.mobileapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import mobile.umn.mobileapp.model.RequestHeader;

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
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       /* //Populate the ArrayList with your own values
        requests.add(new RequestHeader("PR-2018040003","STOCK","erwin","2018-04-03","Rp 3.500.000,00"));
        requests.add(new RequestHeader("PR-2018040007","STOCK","erwin","2018-04-07","Rp 2.000.000,00"));
        requests.add(new RequestHeader("PR-2018040012","NON-STOCK","erwin","2018-04-12","Rp 5.800.000,00"));
        */

        HomeListAdapter adapter = new HomeListAdapter(requests);
        RecyclerView myView = (RecyclerView) findViewById(R.id.recycler_view);
        myView.setHasFixedSize(true);
        myView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myView.setLayoutManager(llm);
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


        if (id == R.id.nav_pr) {
            Intent i = new Intent(HomeActivity.this, PurchaseRequestActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            startActivity(i);
        } else if (id == R.id.nav_depthead) {

        } else if (id == R.id.nav_finance) {
            new HttpRequestAsk().execute();
        } else if (id == R.id.nav_purchasing) {

        } else if (id == R.id.nav_gm) {

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(i);
            getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .remove("username")
                    .remove("password")
                    .commit();
        }
            else if (id == R.id.nav_requests)
        {
            startActivity(new Intent(HomeActivity.this,OngoingActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class HttpRequestAsk extends AsyncTask<Void, Void, List<List<Object>>> {

        HttpRequestAsk() {

        }

        @Override
        protected List<List<Object>> doInBackground(Void... voids) {
            MasterItemRestClient m = new MasterItemRestClient();
            return m.findAll();
        }

        @Override
        protected void onPostExecute(List<List<Object>> o) {
            super.onPostExecute(o);
            for (int i = 0; i < o.size(); i++) {
//                System.out.println("OBJECT " + i + " : " + o.get(i));
                for (int j = 0; j < o.get(i).size(); j++) {
                    System.out.println("              DETAIL " + j + " : " + o.get(i).get(j));
                }
                System.out.println("\n");
            }
        }
    }
}
