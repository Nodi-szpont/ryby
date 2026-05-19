package com.example.ryba;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNazwa, editTextWaga;
    private RadioGroup radioGroupOdzywianie;
    private RadioButton radioButtonRoslinozerne, radioButtonWszystkozerne, radioButtonDrapiezne;
    private TextView textViewDlugosc;
    private SeekBar seekBarDlugosc;
    private CheckBox checkBoxOkres;
    private Button buttonDodaj;
    private ListView listView;
    private ArrayList<String> rybyLista;
    private android.widget.ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicjalizujWidoki();
        skonfigurujSeekBar();
        skonfigurujRadioGroup();
        skonfigurujListView();
        skonfigurujButton();
    }

    private void inicjalizujWidoki() {
        editTextNazwa = findViewById(R.id.editTextText2);
        editTextWaga = findViewById(R.id.editTextText3);
        radioGroupOdzywianie = findViewById(R.id.grupa);
        radioButtonRoslinozerne = findViewById(R.id.radioButton);
        radioButtonWszystkozerne = findViewById(R.id.radioButton2);
        radioButtonDrapiezne = findViewById(R.id.radioButton3);
        textViewDlugosc = findViewById(R.id.textView2);
        seekBarDlugosc = findViewById(R.id.seekBar);
        checkBoxOkres = findViewById(R.id.checkBox);
        buttonDodaj = findViewById(R.id.button);
        listView = findViewById(R.id.listView);
    }

    private void skonfigurujSeekBar() {
        seekBarDlugosc.setMin(1);
        seekBarDlugosc.setMax(200);
        seekBarDlugosc.setProgress(2);
        textViewDlugosc.setText("Długość: 1");

        seekBarDlugosc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewDlugosc.setText("Długość: " + progress + "cm");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void skonfigurujRadioGroup() {
        radioButtonDrapiezne.setChecked(true);
    }

    private void skonfigurujListView() {
        rybyLista = new ArrayList<>();
        adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rybyLista);
        listView.setAdapter(adapter);
    }

    private void skonfigurujButton() {
        buttonDodaj.setOnClickListener(v -> dodajRybe());
    }

    private void dodajRybe() {
        String nazwa = editTextNazwa.getText().toString().trim();
        String waga = editTextWaga.getText().toString().trim();
        int dlugosc = seekBarDlugosc.getProgress();
        String odzywianie = getWybraneOdzywianie();
        boolean okresOchronny = checkBoxOkres.isChecked();

        if (nazwa.isEmpty() || waga.isEmpty()) {
            Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
            return;
        }

        String rybaTekst = nazwa + " | " + dlugosc + "cm | " + waga + "kg | " + odzywianie +
                (okresOchronny ? " | CHRONIONA" : "");

        rybyLista.add(rybaTekst);
        adapter.notifyDataSetChanged();

        editTextNazwa.setText("");
        editTextWaga.setText("");
        seekBarDlugosc.setProgress(2);
        checkBoxOkres.setChecked(false);
        radioButtonDrapiezne.setChecked(true);

        Toast.makeText(this, "Ryba dodana!", Toast.LENGTH_SHORT).show();
    }

    private String getWybraneOdzywianie() {
        int selectedId = radioGroupOdzywianie.getCheckedRadioButtonId();
        if (selectedId == radioButtonRoslinozerne.getId()) {
            return "roślinożerne";
        } else if (selectedId == radioButtonWszystkozerne.getId()) {
            return "wszystkożerne";
        } else {
            return "drapieżne";
        }
    }
}