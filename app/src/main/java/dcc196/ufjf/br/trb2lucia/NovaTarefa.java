package dcc196.ufjf.br.trb2lucia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovaTarefa extends AppCompatActivity {
    private EditText edtTitulo;
    private EditText edtDescricao;
    private EditText edtGrau;
    private EditText edtEstado;
    private EditText edtData;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        edtTitulo = (EditText)findViewById(R.id.editTitulo);
        edtDescricao = (EditText)findViewById(R.id.editDescricao);
        edtGrau = (EditText)findViewById(R.id.editGrau);
        edtEstado = (EditText)findViewById(R.id.editGrau);
        edtData = (EditText)findViewById(R.id.editData);

        Button btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

       btnCadastrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try {
                   if (validateEntry()) {
                       if(id != -1)
                       {
                           TarefaContract.updateTarefa(new TarefaDbHelper(NovaTarefa.this).getWritableDatabase(),id,edtTitulo.getText().toString(),edtDescricao.getText().toString(),edtGrau.getText().toString(),edtEstado.getText().toString(),edtData.getText().toString());
                           Intent i = new Intent();
                           i.putExtra("Titulo", edtTitulo.getText().toString());
                           i.putExtra("Descricao",edtDescricao.getText().toString());
                           i.putExtra("Grau", edtGrau.getText().toString());
                           i.putExtra("estado",edtEstado.getText().toString());
                           i.putExtra("data", edtData.getText().toString());
                           setResult(Activity.RESULT_OK,i);

                       }else {
                           TarefaContract.Salvetarefa(new TarefaDbHelper(NovaTarefa.this).getWritableDatabase(),edtTitulo.getText().toString(),edtDescricao.getText().toString(),edtGrau.getText().toString(),edtEstado.getText().toString(),edtData.getText().toString());
                           setResult(Activity.RESULT_OK);
                       }

                       Toast.makeText(NovaTarefa.this,"Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                       finish();
                   }
                   else
                       Toast.makeText(NovaTarefa.this,"Por favor preencha todos os campos", Toast.LENGTH_LONG).show();



               }catch (Exception err){
                   Toast.makeText(NovaTarefa.this,"Ocorreu um erro ao salvar", Toast.LENGTH_LONG).show();
               }

           }
       });
    }
    private boolean validateEntry()
    {
        return !(edtTitulo.getText().toString().isEmpty() || edtDescricao.getText().toString().isEmpty() ||
                edtGrau.getText().toString().isEmpty() || edtEstado.getText().toString().isEmpty() || edtData.getText().toString().isEmpty());
    }
}
