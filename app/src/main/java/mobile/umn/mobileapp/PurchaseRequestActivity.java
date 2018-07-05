package mobile.umn.mobileapp;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import mobile.umn.mobileapp.adapter.AddItemAdapter;
import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.entity.RequestDetail;
import mobile.umn.mobileapp.model.MasterItemRestClient;
import mobile.umn.mobileapp.model.RequestHeader;
import mobile.umn.mobileapp.model.RequestHeaderRestClient;

public class PurchaseRequestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context = this;
    Activity activity = this;
    Dialog dialog;
    RequestHeader header;
    NumberProgressBar loader;
    ArrayList<RequestDetail> requests = new ArrayList<>();
    ArrayList<MasterItem> items = new ArrayList<>();
    MaterialSpinner spinner;

    AddItemAdapter adapter;

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

        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("STOCK", "NON-STOCK");
        spinner.setOnItemSelectedListener((view, position, id, item) -> {

        });

        findViewById(R.id.btn_save_pr).setOnClickListener(param0 -> {
            System.out.println("GRANDTOTAL DI ACTIVITY : " + adapter.gt);
            new HttpRequestSend().execute();
        });

        // add button listener
        findViewById(R.id.btn_add_item).setOnClickListener(v -> {
            dialog = new Dialog(context);
            dialog.setContentView(getLayoutInflater().inflate(R.layout.dialog_add_item, null));

            loader = dialog.findViewById(R.id.pr_progress_bar);
            dialog.setTitle("Add an item");
            EditText search = (EditText) dialog.findViewById(R.id.text_search_item);
            search.setOnEditorActionListener((view, actionId, event) -> {
                loader.setProgress(0);
                loader.setVisibility(View.VISIBLE);
                if (actionId == 0) {
                    new HttpRequestAsk(search.getText().toString()).execute();
                    dialog.findViewById(R.id.text_qty_item).requestFocus();
                    return true;
                }
                return false;
            });
            dialog.show();
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent i = new Intent(PurchaseRequestActivity.this, PurchaseRequestActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            startActivity(i);
        } else if (id == R.id.nav_depthead) {
            Intent i = new Intent(PurchaseRequestActivity.this, DeptHeadActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
        } else if (id == R.id.nav_finance) {
            Intent i = new Intent(PurchaseRequestActivity.this, FinanceActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("requests", requests.size());
            i.putExtra("fullname",getIntent().getStringExtra("fullname"));
            i.putExtra("position",getIntent().getStringExtra("position"));
            startActivity(i);
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
        else if (id == R.id.nav_requests)
        {
            startActivity(new Intent(PurchaseRequestActivity.this,OngoingActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.pr_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private class HttpRequestAsk extends AsyncTask<Void, Integer, List<MasterItem>> {
        String item_name;

        HttpRequestAsk(String item_name) {
            this.item_name = item_name;
        }

        @Override
        protected List<MasterItem> doInBackground(Void... voids) {
            MasterItemRestClient m = new MasterItemRestClient();
            List<MasterItem> l = m.findAllByName(item_name);
            int i = 0;
            while (i <= 100) {
                try {
                    Thread.sleep(1);
                    publishProgress(i);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return l;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            loader.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<MasterItem> masterItems) {
            super.onPostExecute(masterItems);
            items = new ArrayList<>(masterItems);
            if (masterItems.size() > 0) {
                adapter = new AddItemAdapter(
                        items, requests, dialog, context, activity);
                RecyclerView myView = (RecyclerView) dialog.findViewById(R.id.dialog_pr_item_list);
                myView.setHasFixedSize(true);
                myView.setAdapter(adapter);
                LinearLayoutManager llm = new LinearLayoutManager(context);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                myView.setLayoutManager(llm);
            }

            loader.setVisibility(View.INVISIBLE);
        }
    }

    private class HttpRequestSend extends AsyncTask<Void, Integer, RequestHeader> {

        HttpRequestSend() {


        }

        @Override
        protected RequestHeader doInBackground(Void... voids) {
            RequestHeaderRestClient r = new RequestHeaderRestClient();
            header = new RequestHeader(
                    0,
                    getIntent().getStringExtra("fullname"),
                    "",
                    "",
                    0,
                    spinner.getSelectedIndex() == 0 ? "STOCK" : "NON-STOCK",
                    "", "",
                    "", "",
                    "", "",
                    "", "",
                    adapter.gt,
                    requests
            );
            return r.submit(header);
        }

        @Override
        protected void onPostExecute(RequestHeader requestHeader) {
            super.onPostExecute(requestHeader);
            System.out.println("DONE : " + requestHeader);
        }
    }
}
