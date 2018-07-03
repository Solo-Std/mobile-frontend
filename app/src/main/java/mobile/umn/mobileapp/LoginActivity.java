package mobile.umn.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.springframework.http.ResponseEntity;

import java.util.List;

import mobile.umn.mobileapp.adapter.MasterItemListAdapter;
import mobile.umn.mobileapp.entity.MasterItem;
import model.MasterUserRestClient;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        SharedPreferences getter = sharedpreferences;
        String _username = getter.getString("username", null);
        String _password = getter.getString("password", null);
        if(_username.equals("") || _password.equals("")){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            EditText username = (EditText)findViewById(R.id.input_username);
            EditText password = (EditText)findViewById(R.id.input_password);


            findViewById(R.id.btn_signin).setOnClickListener((v -> {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("username", username.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.commit();
                new HttpRequestAsk(username.getText().toString(),password.getText().toString()).execute();
            }));
        }
        else{
            new HttpRequestAsk(_username,_password).execute();
            super.onCreate(savedInstanceState);
        }
    }

    private class HttpRequestAsk extends AsyncTask<Void, Void, ResponseEntity<Boolean>> {
        String username;
        String password;
        HttpRequestAsk(String username, String password){
            this.username = username;
            this.password = password;
        }

        @Override
        protected ResponseEntity<Boolean> doInBackground(Void... voids) {
            MasterUserRestClient masterUserRestClient = new MasterUserRestClient();
            return masterUserRestClient.find(username, password);
        }

        @Override
        protected void onPostExecute(ResponseEntity<Boolean> booleanResponseEntity) {
            super.onPostExecute(booleanResponseEntity);
            if(booleanResponseEntity.getBody()){
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }
        }
    }
}
