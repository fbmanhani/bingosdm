package br.edu.ifsp.sdm.manhani.bingosdm;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private ArrayList<Integer> listaSorteados;
    private TextView sorteadosTextView;
    private TextView sorteadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        random = new Random(System.currentTimeMillis());
        listaSorteados = new ArrayList<>();
        sorteadosTextView = findViewById(R.id.sorteadosTextView);
        sorteadoTextView = findViewById(R.id.sorteadoTextView);
    }


    public void sortear(View botao) {
        if (botao.getId() == R.id.sortearButton) {
            if (listaSorteados.size() < 75) {
                Integer numero = random.nextInt(75) + 1;
                while (listaSorteados.contains(numero)) {
                    numero = random.nextInt(75) + 1;
                }
                listaSorteados.add(numero);
                sorteadosTextView.setText(sorteadosTextView.getText() + numero.toString() + " ");

                String letra = "";
                if (numero >= 1 && numero <= 15) {
                    letra = "B";
                } else if (numero >= 16 && numero <= 30) {
                    letra = "I";
                } else if (numero >= 31 && numero <= 45) {
                    letra = "N";
                } else if (numero >= 46 && numero <= 60) {
                    letra = "G";
                } else if (numero >= 61 && numero <= 75) {
                    letra = "O";
                }
                sorteadoTextView.setText(letra + numero);
            } else {
                Toast.makeText(this, "Não há mais números à serem sorteados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void reiniciar(View botao) {
        if (botao.getId() == R.id.reiniciarButton) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder
                    .setMessage("Deseja reniciar?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            listaSorteados.clear();
                            sorteadosTextView.setText(null);
                            sorteadoTextView.setText(null);
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("sorteadosTxt", sorteadosTextView.getText().toString());
        outState.putString("sorteadoTxt", sorteadoTextView.getText().toString());
        outState.putIntegerArrayList("listaSorteados", listaSorteados);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            sorteadosTextView.setText(savedInstanceState.getString("sorteadosTxt", null));
            sorteadoTextView.setText(savedInstanceState.getString("sorteadoTxt", null));
            listaSorteados = savedInstanceState.getIntegerArrayList("listaSorteados");
        }
    }
}
