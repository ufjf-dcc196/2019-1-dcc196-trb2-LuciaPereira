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

import dcc196.ufjf.br.trb2lucia.R;

    public class NovaTarefa2 extends AppCompatActivity {
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
                                Intent i = new Intent();
                                i.putExtra(MainActivity.TITULO_TAREFA, edtTitulo.getText().toString());
                                i.putExtra(MainActivity.DESCRICAO_TAREFA, edtDescricao.getText().toString());
                                i.putExtra(MainActivity.GRAU_TAREFA,edtGrau.getText().toString());
                                i.putExtra(MainActivity.ESTADO_TAREFA,edtEstado.getText().toString());
                                i.putExtra(MainActivity.HORA_LIMITE_TAREFA,edtData.getText().toString());
                                if ("".equals(edtTitulo.getText().toString()) || edtTitulo.getText() == null
                                || edtDescricao.getText().toString().equals("") || edtDescricao.getText() == null
                                        || edtGrau.getText().toString().equals("") || edtGrau.getText() == null
                                        || edtEstado.getText().toString().equals("") || edtEstado.getText() == null
                                        || edtData.getText().toString().equals("") || edtData.getText() == null){
                                    Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                                    t.setGravity(Gravity.CENTER,0,0);
                                    t.show();
                                }


                            }else {
                                setResult(Activity.RESULT_OK);
                                finish();
                            }

                            Toast.makeText(NovaTarefa2.this,"Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            Toast.makeText(NovaTarefa2.this,"Por favor preencha todos os campos", Toast.LENGTH_LONG).show();



                    }catch (Exception err){
                        Toast.makeText(NovaTarefa2.this,"Ocorreu um erro ao salvar", Toast.LENGTH_LONG).show();
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
