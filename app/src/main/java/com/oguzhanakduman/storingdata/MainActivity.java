package com.oguzhanakduman.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ageEditText;
    TextView ageTextView;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageEditText = findViewById(R.id.ageEditText);
        ageTextView = findViewById(R.id.ageView);

        // Shared Preferences nesnesinin bir referansını private(yalnızca bu uygulamada) kullanılır olarak oluşturduk.
        sharedPreferences = this.getSharedPreferences("com.oguzhanakduman.storingdata", Context.MODE_PRIVATE);

        // Shared Preferences ile gelen yaş verisini ekranda başlatma
        int storedAge = sharedPreferences.getInt("storedAge",0);
        if(storedAge == 0){
            ageTextView.setText("Kayıtlı Yaş Değeri Yok");
        } else {
            ageTextView.setText("Yaşınız: " + storedAge);
        }

    }

    // Butona Basılınca Kayıt Etmeyi Sağlayan Veriler
    public void saveButton(View view){
        if(!ageEditText.getText().toString().matches("")){
            int getAgeFromInput = Integer.parseInt(ageEditText.getText().toString());
            ageTextView.setText("Yaşın: " + getAgeFromInput);

            // Gelen veriyi sharedpreferences ile kayıt etme
            sharedPreferences.edit().putInt("storedAge",getAgeFromInput).apply();

        }else {
            ageTextView.setText("Geçerli Değer Girin!");
        }
    }


    // Butona Basılınca Silmeyi Sağlayan Veriler
    public void deleteButton(View view){
        int storedData = sharedPreferences.getInt("storedAge",0);

        if (storedData != 0){
            sharedPreferences.edit().remove("storedAge").apply();
            ageTextView.setText("Kayıtlı Yaş Değeri Yok");
        }
    }


}