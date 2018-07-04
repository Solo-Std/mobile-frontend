package mobile.umn.mobileapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

import mobile.umn.mobileapp.adapter.AddPurchaseRequestAdapter;
import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.RequestDetail;

public class PurchaseRequestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<RequestDetail> requests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.pr_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((TextView) findViewById(R.id.text_request_count)).setText("You have " + getIntent().getExtras().getInt("requests") + " new requests");
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("STOCK","NON-STOCK");
        spinner.setOnItemSelectedListener((view, position, id, item) -> {

        });


        // add button listener
        findViewById(R.id.btn_add_item).setOnClickListener(arg0 -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setView(getLayoutInflater().inflate(R.layout.dialog_add_item,null));

            dialog.setTitle("Add an item");

//            TextView text = (TextView) dialog.findViewById(R.id.text);
//            text.setText("Android custom dialog example!");
//            ImageView image = (ImageView) dialog.findViewById(R.id.image);
//            image.setImageResource(R.drawable.ic_attach_money_black_24dp);
//
//            Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//            dialogButton.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        requests.add(new RequestDetail(1,new MasterItem(1,"BRW0001","Red Potion",5000),50000,10));
        requests.add(new RequestDetail(2,new MasterItem(2,"BRW0001","Blue Potion",5000),5000,1));
        requests.add(new RequestDetail(3,new MasterItem(3,"BRW0001","Green Herb",500),5000,10));


        AddPurchaseRequestAdapter adapter = new AddPurchaseRequestAdapter(requests);
        RecyclerView myView = (RecyclerView) findViewById(R.id.pr_recycler_view);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pr) {

        } else if (id == R.id.nav_depthead) {

        } else if (id == R.id.nav_finance) {

        } else if (id == R.id.nav_purchasing) {

        } else if (id == R.id.nav_gm) {

        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(PurchaseRequestActivity.this, LoginActivity.class);
            startActivity(i);
            getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
                    .edit()
                    .remove("username")
                    .remove("password")
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
