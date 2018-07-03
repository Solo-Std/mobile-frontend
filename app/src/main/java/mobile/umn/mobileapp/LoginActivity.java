package mobile.umn.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_signin).setOnClickListener((v -> {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }));
    }
}
