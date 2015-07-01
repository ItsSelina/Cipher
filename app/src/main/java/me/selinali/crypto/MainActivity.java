package me.selinali.crypto;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.input)
    EditText input;

    @Bind(R.id.key_selector)
    Spinner keySelector;

    @Bind(R.id.output)
    TextView output;

    private boolean encrypting = false;

    private static String encrypt(String message, int key) {
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            char ciphered = (char) (current + key);
            if (current < 'a' || current > 'z') encrypted += current;
            else if (ciphered > 'z') encrypted += (char) (ciphered - 26);
            else encrypted += ciphered;
        }
        return encrypted;
    }

    private static String decrypt(String message, int key) {
        String decrypted = "";
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            char ciphered = (char) (current - key);
            if (current < 'a' || current > 'z') decrypted += current;
            else if (ciphered < 'a') decrypted += (char) (ciphered + 26);
            else decrypted += ciphered;
        }
        return decrypted;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String action = intent.getStringExtra("ACTION");

        if (action.equals("encrypt")) setupForEncryption();
        else if (action.equals("decrypt")) setupForDecryption();

        List<Integer> keys = new ArrayList<>();
        for (int i = 1; i <= 25; i++) keys.add(i);
        keySelector.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, keys));
    }

    private void setupForEncryption() {
        encrypting = true;
        input.setHint("Enter message to encrypt");
        button.setText("Encrypt");
        button.setBackgroundColor(Color.parseColor("#a8d3c9"));
    }

    private void setupForDecryption() {
        input.setHint("Enter message to decrypt");
        button.setText("Decrypt");
        button.setBackgroundColor(Color.parseColor("#ea9b4c"));
    }

    public void onButtonClicked(View view) {
        String message = input.getText().toString();
        int key = (int) keySelector.getSelectedItem();

        String outputText;
        if (encrypting) outputText = encrypt(message, key);
        else outputText = decrypt(message, key);

        output.setText(outputText);
    }
}