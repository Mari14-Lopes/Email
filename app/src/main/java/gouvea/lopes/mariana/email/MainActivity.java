package gouvea.lopes.mariana.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();
                /*Ao especificar um ACTION
                do tipo SENDTO (ENVIAR PARA), o Android procura quais apps instaladas no sistema são
                capazes de resolver essa ação.*/
                Intent i = new Intent(Intent.ACTION_SENDTO);
                /*Esse URI é utilizado para indicar apps que trabalham com envio e
                recebimento de e-mail.
                 */
                i.setData(Uri.parse("mailto:"));
                //lista de Strings onde cada String é um e-mail de destinatário.
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}