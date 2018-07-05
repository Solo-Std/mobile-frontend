package mobile.umn.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.ResponseEntity;

import java.util.List;

import mobile.umn.mobileapp.entity.MasterUser;
import mobile.umn.mobileapp.model.MasterUserRestClient;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        SharedPreferences getter = sharedpreferences;
        String _username = getter.getString("username", null);
        String _password = getter.getString("password", null);
        if(_username!=null && _username.equals("admin")){
            if(_password!=null && _password.equals("admin")){
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }
        if (_username == null || _username.equals("") || _password == null || _password.equals("")) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            final EditText username = (EditText) findViewById(R.id.input_username);
            final EditText password = (EditText) findViewById(R.id.input_password);

            findViewById(R.id.btn_signin).setOnClickListener((v -> {
                if (username.getText().toString().equals("")) {
                    username.setError("Username cannot be empty");
                } else if (password.getText().toString().equals("")) {
                    password.setError("Password cannot be empty");
                } else {
                    if(username.getText().toString().equals("admin")){
                        if(password.getText().toString().equals("admin")){
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    }
                    System.out.println("else");
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.commit();
                    new HttpRequestAsk(username.getText().toString(), password.getText().toString()).execute();
                }
            }));
        } else {
            new HttpRequestAsk(_username, _password).execute();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }
    }

    private class HttpRequestAsk extends AsyncTask<Void, Void, List<MasterUser>> {
        String username;
        String password;

        HttpRequestAsk(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected List<MasterUser> doInBackground(Void... voids) {
            MasterUserRestClient masterUserRestClient = new MasterUserRestClient();
            return masterUserRestClient.find(username, password);
        }

        @Override
        protected void onPostExecute(List<MasterUser> masterUsers) {
            super.onPostExecute(masterUsers);
            if (masterUsers.size()>0) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                i.putExtra("fullname",masterUsers.get(0).getFullname());
                i.putExtra("position",masterUsers.get(0).getJobposition());
                startActivity(i);
            } else {
                Toast.makeText(LoginActivity.this,"Invalid Credential",Toast.LENGTH_LONG).show();
            }
        }
    }
}
