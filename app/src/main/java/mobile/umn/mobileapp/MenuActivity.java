package mobile.umn.mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.buttonShowAll).setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, MasterItemListActivity.class);
            startActivity(i);
        });

        findViewById(R.id.buttonShowById).setOnClickListener(v -> {
            Intent i = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}
