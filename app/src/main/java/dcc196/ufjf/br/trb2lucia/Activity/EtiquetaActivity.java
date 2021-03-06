package dcc196.ufjf.br.trb2lucia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;
import dcc196.ufjf.br.trb2lucia.Persistencia.EtiquetaDao;
import dcc196.ufjf.br.trb2lucia.R;

public class EtiquetaActivity extends AppCompatActivity {
    private EditText edtDescricao;
    private int id;
    Button btnCadastrarTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etiqueta);
        EtiquetaDao.getInstance().inicializarDBHelper(getApplication());
        edtDescricao = findViewById(R.id.editDescricao);
        btnCadastrarTags = (Button) findViewById(R.id.btCadEtiqueta);



        btnCadastrarTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 try {
                    if (validateEntry()) {
                        if(id != -1)
                        {
                            Intent i = new Intent();
                            i.putExtra(MainActivity.DESCRICAO_TAGS,edtDescricao.getText().toString());

                            if ( edtDescricao.getText().toString().equals("") || edtDescricao.getText() == null) {

                                Toast t = Toast.makeText(getApplicationContext(), "Favor preencher o campo", Toast.LENGTH_LONG);
                                t.setGravity(Gravity.CENTER, 0, 0);
                                t.show();
                            }

                        }else {
                                setResult(Activity.RESULT_OK);
                                finish();
                        }
                        Toast.makeText(EtiquetaActivity.this,"Etiqueta salva com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                        else
                    Toast.makeText(EtiquetaActivity.this,"Por favor preencha o campo", Toast.LENGTH_LONG).show();



            }catch (Exception err){
                Toast.makeText(EtiquetaActivity.this,"Ocorreu um erro ao salvar", Toast.LENGTH_LONG).show();
            }

        }
    });
}

        private boolean validateEntry()
        {
            return !(edtDescricao.getText().toString().isEmpty());
        }


}
