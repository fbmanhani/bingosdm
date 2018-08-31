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
    private TextView sorteadosBTextView;
    private TextView sorteadosITextView;
    private TextView sorteadosNTextView;
    private TextView sorteadosGTextView;
    private TextView sorteadosOTextView;
    private TextView sorteadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        random = new Random(System.currentTimeMillis());
        listaSorteados = new ArrayList<>();
        sorteadosBTextView = findViewById(R.id.sorteadosBTextView);
        sorteadosITextView = findViewById(R.id.sorteadosITextView);
        sorteadosNTextView = findViewById(R.id.sorteadosNTextView);
        sorteadosGTextView = findViewById(R.id.sorteadosGTextView);
        sorteadosOTextView = findViewById(R.id.sorteadosOTextView);
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
                String letra = "";
                if (numero >= 1 && numero <= 15) {
                    letra = "B";
                    sorteadosBTextView.setText(String.format("%s%s ", sorteadosBTextView.getText(), numero.toString()));
                } else if (numero >= 16 && numero <= 30) {
                    letra = "I";
                    sorteadosITextView.setText(String.format("%s%s ", sorteadosITextView.getText(), numero.toString()));
                } else if (numero >= 31 && numero <= 45) {
                    letra = "N";
                    sorteadosNTextView.setText(String.format("%s%s ", sorteadosNTextView.getText(), numero.toString()));
                } else if (numero >= 46 && numero <= 60) {
                    letra = "G";
                    sorteadosGTextView.setText(String.format("%s%s ", sorteadosGTextView.getText(), numero.toString()));
                } else if (numero >= 61 && numero <= 75) {
                    letra = "O";
                    sorteadosOTextView.setText(String.format("%s%s ", sorteadosOTextView.getText(), numero.toString()));
                }
                sorteadoTextView.setText(String.format("%s%d", letra, numero));
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
                            sorteadosBTextView.setText(null);
                            sorteadosITextView.setText(null);
                            sorteadosNTextView.setText(null);
                            sorteadosGTextView.setText(null);
                            sorteadosOTextView.setText(null);
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
        outState.putString("sorteadosBTxt", sorteadosBTextView.getText().toString());
        outState.putString("sorteadosITxt", sorteadosITextView.getText().toString());
        outState.putString("sorteadosNTxt", sorteadosNTextView.getText().toString());
        outState.putString("sorteadosGTxt", sorteadosGTextView.getText().toString());
        outState.putString("sorteadosOTxt", sorteadosOTextView.getText().toString());
        outState.putString("sorteadoTxt", sorteadoTextView.getText().toString());
        outState.putIntegerArrayList("listaSorteados", listaSorteados);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            sorteadosBTextView.setText(savedInstanceState.getString("sorteadosBTxt", null));
            sorteadosITextView.setText(savedInstanceState.getString("sorteadosITxt", null));
            sorteadosNTextView.setText(savedInstanceState.getString("sorteadosNTxt", null));
            sorteadosGTextView.setText(savedInstanceState.getString("sorteadosGTxt", null));
            sorteadosOTextView.setText(savedInstanceState.getString("sorteadosOTxt", null));
            sorteadoTextView.setText(savedInstanceState.getString("sorteadoTxt", null));
            listaSorteados = savedInstanceState.getIntegerArrayList("listaSorteados");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sorteadosBTextView = null;
        sorteadosITextView = null;
        sorteadosNTextView = null;
        sorteadosGTextView = null;
        sorteadosOTextView = null;
        sorteadoTextView = null;
        listaSorteados = null;
    }
}
