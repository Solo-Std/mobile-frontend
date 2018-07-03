package mobile.umn.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DebugMenuActivity extends AppCompatActivity {

    private Button buttonShowAll, buttonShowById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonShowAll = (Button) findViewById(R.id.buttonShowAll);
        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DebugMenuActivity.this, MasterItemListActivity.class);
                startActivity(i);
            }
        });

        buttonShowById = (Button) findViewById(R.id.buttonShowById);
        buttonShowById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DebugMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.login_button).setOnClickListener((v)->{
            Intent i = new Intent(DebugMenuActivity.this, LoginActivity.class);
            startActivity(i);
        });
        findViewById(R.id.home_button).setOnClickListener((v)->{
            Intent i = new Intent(DebugMenuActivity.this, HomeActivity.class);
            startActivity(i);
        });
    }
}
