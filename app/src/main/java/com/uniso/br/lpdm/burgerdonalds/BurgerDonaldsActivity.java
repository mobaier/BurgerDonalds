// André Mobaier de Oliveira RA: 00101025
// Ciência da Computação - 4º Semestre

/* ESTA ACTIVITY REPRESENTA O MENU PRINCIPAL DO APLICATIVO

FUNÇÕES DO MENU:
- REGISTRAR UM PEDIDO
- CONSULTAR HORAS TRABALHADAS
- CONVERSAR COM UM ATENDENTE VIA CHAT
 */

package com.uniso.br.lpdm.burgerdonalds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class BurgerDonaldsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burgerdonalds);
    }

    // Primeiro botão - Registrar pedido
    public void onCLickFazerPedido(View view){
        TextView textView = (TextView) findViewById(R.id.display);
        textView.setText("Registrando seu pedido...");
    }

    // Segundo Botão - Ver total de horas trabalhadas
    public void onClickVerHoras(View view){
        TextView textView = (TextView) findViewById(R.id.display);
        // Valor arbitrário para aparecer mesmo que
        // nenhuma hora de trabalho tenha sido registrada
        int horasTrabalhadas = 14;
        textView.setText("Total de horas trabalhadas: " + horasTrabalhadas + " horas");
    }

    // Terceiro botão - Chat com atendente
    public void onClickCozinha(View view){
        Intent intencao = new Intent(this, ChatAtendenteActivity.class);
        startActivity(intencao);
    }

    // Quarto botão = Gerenciar horas de trabalho
    public void onClickGH(View view){
        Intent intencao = new Intent(this, GerenciaDeHorasActivity.class);
        startActivity(intencao);
    }

}