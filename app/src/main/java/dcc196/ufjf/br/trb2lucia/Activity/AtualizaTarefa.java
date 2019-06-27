package dcc196.ufjf.br.trb2lucia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dcc196.ufjf.br.trb2lucia.Adapter.EtiquetaAdapter;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;
import dcc196.ufjf.br.trb2lucia.Persistencia.EtiquetaDao;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaDao;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaEtiquetaDao;
import dcc196.ufjf.br.trb2lucia.R;

public class AtualizaTarefa extends AppCompatActivity {
    private static final int REQUEST_CADASTRAR_TAREFA_ETIQUETA = 1;
    public static final String ID_TAREFA = "Tarefa";
    public static final String ID_TAG = "Tags";

    private RecyclerView listTags;
    private EditText descricao, titulo, grau;
    private Button btnSalvar,btnCancelar, btnNovaTag;
    private EtiquetaAdapter adapter;
    private int idTarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_tarefa);
        final Intent intent = getIntent();
        Bundle bundleResult =  intent.getExtras();
        idTarefa =  bundleResult.getInt(MainActivity.ID_TAREFA);
        TarefaDao.getInstance().inicializarDBHelper(getApplicationContext());
        EtiquetaDao.getInstance().inicializarDBHelper(getApplicationContext());
        TarefaEtiquetaDao.getInstance().inicializarDBHelper(getApplicationContext());
        listTags = findViewById(R.id.lista_tags);
        listTags.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EtiquetaAdapter(TarefaEtiquetaDao.getInstance().getEtiquetaTarefa(idTarefa));
        listTags.setAdapter(adapter);

        titulo = findViewById(R.id.titulo_tarefa);
        descricao = findViewById(R.id.descricao_tarefa);
        grau = findViewById(R.id.grau_tarefa);
        btnSalvar = findViewById(R.id.btn_salvar_tarefa);
        btnNovaTag = findViewById(R.id.btn_nova_tag);
        btnCancelar = findViewById(R.id.btn_cancelar_tarefa);

        titulo.setText(TarefaDao.getInstance().getTarefaPeloId(idTarefa).getTitulo());
        descricao.setText(TarefaDao.getInstance().getTarefaPeloId(idTarefa).getDescricao());
        grau.setText(TarefaDao.getInstance().getTarefaPeloId(idTarefa).getGrau());

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attPart = new Intent();
                Tarefa aux = new Tarefa();
                aux.setTitulo(titulo.getText().toString());
                        aux.setDescricao(descricao.getText().toString());
                        aux.setGrau(grau.getText().toString());
                        aux.setIdTarefa(idTarefa);
                TarefaDao.getInstance().atulizarTarefa(aux);
                setResult(Activity.RESULT_OK, attPart);
                finish();
            }
        });

        btnNovaTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attPart = new Intent(AtualizaTarefa.this, ListTagsTarefas.class);
                attPart.putExtra(AtualizaTarefa.ID_TAREFA,idTarefa);
                startActivityForResult(attPart,REQUEST_CADASTRAR_TAREFA_ETIQUETA);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnEtiquetaClickListener(new EtiquetaAdapter.OnEtiquetaClickListener() {
            @Override
            public void onEtiquetaClick(View view, int position) {
                Intent intent = new Intent(AtualizaTarefa.this,ListaTarefa.class);
                int idEtiqueta = (int) EtiquetaDao.getInstance().getEtiqueta().get(position).getIdEtiqueta();
                intent.putExtra(AtualizaTarefa.ID_TAG,idEtiqueta);

                startActivity(intent);
            }

            @Override
            public void onLongEtiquetaClick(View view, int position) {
                int idEtiqueta = (int) EtiquetaDao.getInstance().getEtiqueta().get(position).getIdEtiqueta();
                TarefaEtiquetaDao.getInstance().removeTarefaEtiqueta(idEtiqueta, idTarefa);

                adapter.setEtiquetas(TarefaEtiquetaDao.getInstance().getEtiquetaTarefa(idEtiqueta));
                adapter.notifyItemRemoved(position);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AtualizaTarefa.REQUEST_CADASTRAR_TAREFA_ETIQUETA && resultCode== Activity.RESULT_OK && data != null){
            adapter.setEtiquetas(TarefaEtiquetaDao.getInstance().getEtiquetaTarefa(idTarefa));
            adapter.notifyDataSetChanged();
        }
    }

}
