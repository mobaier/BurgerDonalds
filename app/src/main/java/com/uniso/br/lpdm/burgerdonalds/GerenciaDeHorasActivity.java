package com.uniso.br.lpdm.burgerdonalds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;



public class GerenciaDeHorasActivity extends AppCompatActivity {
    private int segundos = 0;
    private boolean executando;
    private boolean estavaExecutadando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerencia_de_horas);

        // Guardar a instância dos valores do timer
        if(savedInstanceState != null){
            segundos = savedInstanceState.getInt("segundos");
            executando = savedInstanceState.getBoolean("executando");
            estavaExecutadando = savedInstanceState.getBoolean("estavaExecutadando");
        }

        // Iniciar o timer
        executarTemporizador();
    }

    // Método para salvar os valores do timer, ele é executado antes da destruição da activity
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("segundos", segundos);
        savedInstanceState.putBoolean("executando", executando);
        savedInstanceState.putBoolean("estavaExecutadando", estavaExecutadando);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Esses métodos são executados sempre que perde foco, e como são executados sempre depois
    // de onstart e onstop, podemos deixar apenas eles;
    @Override
    protected void onPause() {
        super.onPause();
        estavaExecutadando = executando;
        executando = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        executando = estavaExecutadando;
    }

    // Primeiro botão - Iniciar o cronômetro
    public void onClickIniciar(View view){
        executando = true;
    }

    // Segundo botão - Pausar o cronômetro
    public void onClickPausar(View view){
        executando = false;
    }

    // Terceiro botão - Envia os dados do timer para outra activity e zera o cronômetro
    public void onClickEnviar(View view){
        executando = false;
        segundos = 0;
    }

    private void executarTemporizador(){
        // A variavel precisa ser final para podermos acess-la dentro do handler
        final TextView textView = (TextView) findViewById(R.id.expTime);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             // Cálculo de tempo baseado em quantos segundos passaram
                             int horas = segundos/3600;
                             int minutos = (segundos%3600)/60;
                             int segundos_interno = segundos%60;

                             // Formatação em HH:MM:SS
                             String time = String.format(Locale.getDefault(), "%d:%02d:%02d",
                                     horas, minutos, segundos_interno);

                             // Colocamos o texto no text view
                             textView.setText(time);
                             // Contar os segundos (incrementar) enquanto o app está em execução
                             if(executando){
                                 segundos++;
                             }

                             // Delay para simular a contagem real de tempo (Não é 100% coerente)
                             handler.postDelayed(this, 1000);
                         }
                     }
        );
    }

    // Voltar para o menu principal
    public void goToMenu(View view){
        Intent intencao = new Intent(this, BurgerDonaldsActivity.class);
        startActivity(intencao);
    }
}