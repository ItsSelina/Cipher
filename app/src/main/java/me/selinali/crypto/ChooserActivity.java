package me.selinali.crypto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
    }

    public void onEncryptClicked(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ACTION", "encrypt");
        startActivity(i);

    }

    public void onDecryptClicked(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ACTION", "decrypt");
        startActivity(i);
    }
}
