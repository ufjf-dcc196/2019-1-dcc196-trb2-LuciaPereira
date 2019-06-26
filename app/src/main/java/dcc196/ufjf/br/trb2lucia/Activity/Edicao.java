package dcc196.ufjf.br.trb2lucia.Activity;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaDao;
import dcc196.ufjf.br.trb2lucia.R;

public class Edicao extends AppCompatActivity {
    private EditText edtTitulo;
    private EditText edtDescricao;
    private EditText edtGrau;
    private EditText edtEstado;
    private EditText edtData;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);
        final Intent intent = getIntent();
        edtTitulo = (EditText)findViewById(R.id.editTitulo);
        edtDescricao = (EditText)findViewById(R.id.editDescricao);
        edtGrau = (EditText)findViewById(R.id.editGrau);
        edtEstado = (EditText)findViewById(R.id.editGrau);
        edtData = (EditText)findViewById(R.id.editData);
        setDados();
        TarefaDao.getInstance().inicializarDBHelper(getApplicationContext());
        Button btnEditar = (Button)findViewById(R.id.btnCadastrar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa t = new Tarefa();
                t.setTitulo(edtTitulo.getText().toString());
                t.setDescricao(edtDescricao.getText().toString());
                t.setGrau(edtGrau.getText().toString());
               // t.setEstado(edtEstado.getText().toString());
                t.setDataLimite(edtData.getText().toString());
                TarefaDao.getInstance().atulizarTarefa(t);
                //ListaTarefa.attRecycle();
                setResult(Activity.RESULT_OK,intent);
                finish();
                Toast.makeText(Edicao.this,"Tarefa alterada com sucesso ", Toast.LENGTH_LONG).show();
                }


        });
    }

    private void setDados() {
        edtTitulo.setText(TarefaDao.getInstance().getTarefaPeloId(id).getTitulo());
        edtDescricao.setText(TarefaDao.getInstance().getTarefaPeloId(id).getDescricao());
        edtGrau.setText(TarefaDao.getInstance().getTarefaPeloId(id).getGrau());
        //edtEstado.setText(TarefaDao.getInstance().getTarefaPeloId(id).getEstado());
        edtData.setText(TarefaDao.getInstance().getTarefaPeloId(id).getDatalimite());

    }

}
