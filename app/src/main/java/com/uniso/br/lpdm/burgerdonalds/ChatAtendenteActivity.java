/* ESTA ACTIVITY REPRESENTA A TELA DE CHAT COM O ATENDENTE

FUNÇÕES DO MENU DESTA TELA:
- CAMPO DE TEXTO
- BOTÃO DE ENVIO PARA OUTRO APLICATIVO
- BOTÃO DE RETORNO AO MENU
 */

package com.uniso.br.lpdm.burgerdonalds;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChatAtendenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_atendente);
    }

    // Botão para Envio de Mensagem
    public void enviarMensagem(View view) {
        EditText editText = findViewById(R.id.editMensagem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
        intent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(intent, null);
        startActivity(shareIntent);
    }

    // Botão de retorno ao menu
    public void goToMenu(View view){
        Intent intencao = new Intent(this, BurgerDonaldsActivity.class);
        startActivity(intencao);
    }
}