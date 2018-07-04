package mobile.umn.mobileapp;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.HttpRequest;

import java.util.ArrayList;
import java.util.List;

import entity.MasterCard;
import mobile.umn.mobileapp.adapter.MasterItemListAdapter;
import mobile.umn.mobileapp.adapter.OngoingRequestListAdapter;
import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.model.MasterCardRestClient;
import mobile.umn.mobileapp.model.MasterItemRestClient;
import mobile.umn.mobileapp.model.RequestHeader;

public class OngoingRequestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<RequestHeader> requests = new ArrayList<RequestHeader>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HttpRequestAsk().execute();
        setContentView(R.layout.activity_ongoing_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((TextView)findViewById(R.id.text_request_count)).setText("You have " + requests.size() + " new requests");
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
        //OngoingRequestListAdapter adapter = new OngoingRequestListAdapter(requests);//taro list tampungan http request
        //RecyclerView myView =  (RecyclerView)findViewById(R.id.recycler_view);
        //myView.setHasFixedSize(true);
        //myView.setAdapter(adapter);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        //myView.setLayoutManager(llm);
    }


    private class HttpRequestAsk extends AsyncTask<Void, Void, List<MasterCard>> {

        @Override
        protected List<MasterCard> doInBackground(Void... voids) {
            MasterCardRestClient masterCardRestClient = new MasterCardRestClient();
            return masterCardRestClient.findAll();
        }

        @Override
        protected void onPostExecute(List<MasterCard> masterCards) {
            RecyclerView listViewMasterItem = (RecyclerView) findViewById(R.id.recyclerViewOngoingRequest);
            listViewMasterItem.setAdapter(new OngoingRequestListAdapter(masterCards));
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

