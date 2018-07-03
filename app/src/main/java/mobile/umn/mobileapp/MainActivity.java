package mobile.umn.mobileapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mobile.umn.mobileapp.entity.MasterItem;
import mobile.umn.mobileapp.model.MasterItemRestClient;

public class MainActivity extends AppCompatActivity {

    private Button buttonFind;
    private EditText editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (EditText) findViewById(R.id.editTextId);

        buttonFind = (Button) findViewById(R.id.buttonFind);
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item_id = Integer.parseInt(editTextId.getText().toString());
                new HttpRequestAsk(item_id).execute();
            }
        });
    }

    private class HttpRequestAsk extends AsyncTask<Void, Void, MasterItem>{
        private int item_id;

        public HttpRequestAsk(int item_id){
            this.item_id = item_id;
        }

        @Override
        protected MasterItem doInBackground(Void... voids) {
            MasterItemRestClient masterItemRestClient = new MasterItemRestClient();
//            MasterItem i = masterItemRestClient.find(item_id);
//            if(i==null) return new MasterItem();
//            else return i;
            return masterItemRestClient.find(item_id);
        }

        @Override
        protected void onPostExecute(MasterItem masterItem) {
            TextView textViewId = (TextView) findViewById(R.id.textViewId);
            textViewId.setText(String.valueOf(masterItem.getItem_id()));

            TextView textViewCode = (TextView) findViewById(R.id.textViewCode);
            textViewCode.setText(String.valueOf(masterItem.getItem_code()));

            TextView textViewName = (TextView) findViewById(R.id.textViewName);
            textViewName.setText(String.valueOf(masterItem.getItem_name()));
        }
    }
}
