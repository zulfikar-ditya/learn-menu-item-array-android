package com.example.menu_item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ArrayList arr = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        createSetRandomNumber();
        updateList();
    }

    public void updateList() {
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr
        );
        list.setAdapter(arrayAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    public void createSetRandomNumber() {
        Random random = new Random();
        Integer angkaPertama = random.nextInt(101);
        Integer angkaKedua = random.nextInt(101);

        TextView angka_pertama = findViewById(R.id.angka_pertama);
        TextView angka_kedua = findViewById(R.id.angka_kedua);

        angka_pertama.setText(String.valueOf(angkaPertama));
        angka_kedua.setText(String.valueOf(angkaKedua));
//        reset jawaban
        EditText input = findViewById(R.id.answer);
        input.setText(String.valueOf(0));
    }

    public boolean checkJawab()
    {
        EditText input = findViewById(R.id.answer);
        String inputAnswer = input.getText().toString();
        Integer answer = Integer.parseInt(inputAnswer);

        TextView angka_pertama = findViewById(R.id.angka_pertama);
        TextView angka_kedua = findViewById(R.id.angka_kedua);

        String pertama = angka_pertama.getText().toString();
        String kedua = angka_kedua.getText().toString();
        Integer angka1 = 0;
        Integer angka2 = 0;
        try {
            angka1 = Integer.parseInt(pertama);
            angka2 = Integer.parseInt(kedua);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Integer result = angka1 + angka2;
        return answer == result;
    }

    public void submit(View view) {
        if (checkJawab()) {
            Toast.makeText(this, "Benar", Toast.LENGTH_SHORT).show();
            arr.add("Benar");
        } else {
            Toast.makeText(this, "Salah", Toast.LENGTH_SHORT).show();
            arr.add("Salah");
        }
        updateList();
        createSetRandomNumber();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            this.startActivity(new Intent(MainActivity2.this, About.class));
        } else if (item.getItemId() == R.id.bantuan) {
            Toast.makeText(this, "Bantuan clicked", Toast.LENGTH_SHORT).show();
            this.startActivity(new Intent(MainActivity2.this, BantuanActivity.class));
        }
        return true;
    }

    public void reset_stat(View view) {
        arr.clear();
        updateList();
    }
}