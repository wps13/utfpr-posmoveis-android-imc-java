package br.edu.utfpr.calculaimc_java;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private EditText etAltura;
    private EditText etPeso;

    private TextView tvImc;

    private Button btCalcular;
    private Button btLimpar;

    private TextView tvFaixaImc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etAltura = findViewById(R.id.etAltura);
        etPeso = findViewById(R.id.etPeso);

        tvImc = findViewById(R.id.etImc);

        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCalcularOnClick();
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btLimparOnClick();
            }
        });

        btCalcular.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Este é o botão Calcular",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });

        tvFaixaImc = findViewById(R.id.tvFaixaImc);
    }

    private void btLimparOnClick() {
        etAltura.setText("");
        etPeso.setText("");
        tvImc.setText("0.0");
        etPeso.requestFocus();
    }

    private String getFaixaImc(double resultado) {
        if (resultado < 18.50) {
            return "Você está abaixo do peso ideal";
        }
        if (resultado >= 18.50 && resultado < 25.0) {
            return "Seu peso está normal!";
        }
        if (resultado >= 25.0 && resultado < 30.0) {
            return "Você está com sobrepeso";
        }
        if (resultado >= 30.0 && resultado < 35.0) {
            return "Obesidade grau 1";
        }
        if (resultado >= 35.0 && resultado < 40.0) {
            return "Obesidade grau 2";
        }
        if (resultado >= 40.0) {
            return "Obesidade grau 3";
        }

        return "Faixa não encontrada";
    }

    private void btCalcularOnClick() {
        if (etPeso.getText().toString().equals("")) {
            etPeso.setError("Campo peso deve ser preenchido");
            return;
        }

        if (etAltura.getText().toString().equals("")) {
            etAltura.setError("Campo altura deve ser preenchido");
            return;
        }

        // entrada
        double altura = Double.parseDouble(etAltura.getText().toString());
        double peso = Double.parseDouble(etPeso.getText().toString());

        //processamento
        double res = peso / Math.pow(altura, 2);
        String faixaImcMsg = getFaixaImc(res);

        //saida
        DecimalFormat df = new DecimalFormat("0.00");
        tvImc.setText(df.format(res));
        tvFaixaImc.setText(faixaImcMsg);
    }
}