package mobile.umn.mobileapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import entity.MasterCard;
import mobile.umn.mobileapp.adapter.OngoingRequestListAdapter;
import mobile.umn.mobileapp.model.MasterCardRestClient;

public class OngoingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public List<MasterCard> masterCards = new ArrayList<>();

    LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing);
        mRecyclerView = (RecyclerView) findViewById(R.id.ongoing_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        try{
            new HttpRequestAsk().execute();
            //masterCards.add(new MasterCard(123,1123));
//            mAdapter = new OngoingRequestListAdapter(masterCards);
//            mRecyclerView.setAdapter(mAdapter);
            System.out.println("itemcount:"+masterCards.size());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("berhasil");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.ongoing_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(masterCards!= null)((TextView)findViewById(R.id.text_request_count)).setText("You have " + masterCards.size() + " new requests");
            }

        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    private class HttpRequestAsk extends AsyncTask<Void, Void, List<MasterCard>> {

        @Override
        protected List<MasterCard> doInBackground(Void... voids) {
            MasterCardRestClient masterCardRestClient = new MasterCardRestClient();
            masterCards = masterCardRestClient.findAll();
            System.out.println("itemcount:"+masterCards.size());
            return masterCardRestClient.findAll();
        }

        @Override
        protected void onPostExecute(List<MasterCard> masterCards) {
            mRecyclerView = (RecyclerView) findViewById(R.id.ongoing_recycler_view);

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new OngoingRequestListAdapter(masterCards);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.ongoing_drawer_layout);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_pr) {
            // Handle the camera action
        } else if (id == R.id.nav_depthead) {

        } else if (id == R.id.nav_finance) {

        } else if (id == R.id.nav_purchasing) {

        } else if (id == R.id.nav_gm) {

        } else if (id == R.id.nav_logout) {

        }
        else if(id == R.id.nav_requests)
        {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.ongoing_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
