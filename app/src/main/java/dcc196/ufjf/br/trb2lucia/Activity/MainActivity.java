package dcc196.ufjf.br.trb2lucia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaDbHelper;
import dcc196.ufjf.br.trb2lucia.Modelo.Etiqueta;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;
import dcc196.ufjf.br.trb2lucia.Persistencia.EtiquetaDao;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaDao;
import dcc196.ufjf.br.trb2lucia.R;
import dcc196.ufjf.br.trb2lucia.Adapter.TarefaAdapter;

public class MainActivity extends AppCompatActivity {
    private Button btnInserir;
    private Button btnLista;
    private Button btnTags;
    private RecyclerView listaTarefas;
    private TarefaAdapter adapter;
    private TarefaDbHelper dphelper;
    Cursor cursor;
    private static final int REQUEST_CADASTRAR_TAREFA = 1;
    private static final int REQUEST_DETALHES_TAGS = 2;
    private static final int REQUEST_DETALHES_TAREFA = 3;


    public static  final String  TITULO_TAREFA ="TITULO";
    public static  final String  DESCRICAO_TAREFA ="DESCRICAO";
    public static final String GRAU_TAREFA = "GRAU";
    public static final String ESTADO_TAREFA = "ESTADO";
    public static final String HORA_LIMITE_TAREFA = "HORA";
    public  static final String ID_TAREFA = "IDENTIFICADOR";

    public static final String DESCRICAO_TAGS = "TAGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dphelper = new TarefaDbHelper(getApplicationContext());

        TarefaDao.getInstance().inicializarDBHelper(getApplicationContext());
        listaTarefas = findViewById(R.id.rclTarefas);
        listaTarefas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TarefaAdapter(TarefaDao.getInstance().getTarefa());
        listaTarefas.setAdapter(adapter);

       adapter.setOnTarefaClickListener(new TarefaAdapter.OnTarefaClickListener() {
            @Override
            public void onTarefaClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, AtualizaTarefa.class);
                long idTarefa = TarefaDao.getInstance().getTarefa().get(position).getIdTarefa();
                intent.putExtra(MainActivity.ID_TAREFA,idTarefa);
                startActivityForResult(intent,REQUEST_DETALHES_TAREFA);

            }

            @Override
            public void onLongTarefaClick(View v, int position) {
                TarefaDao.getInstance().RemoveTarefa(TarefaDao.getInstance().getTarefa().get(position));
                adapter.setTarefas(TarefaDao.getInstance().getTarefa());
                adapter.notifyItemRemoved(position);
            }
        });

        btnInserir = findViewById(R.id.btnNovo);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaTarefa2.class);
                startActivityForResult(intent,REQUEST_CADASTRAR_TAREFA);


            }
        });
        btnLista = findViewById(R.id.btnExcluir);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListaTags.class);
                startActivity(intent);

            }
        });
        btnTags =  findViewById(R.id.bntTags);
        btnTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EtiquetaActivity.class);
                startActivityForResult(intent,REQUEST_DETALHES_TAGS);


            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if (requestCode == MainActivity.REQUEST_CADASTRAR_TAREFA && resultCode == Activity.RESULT_OK && data != null) {
                    Bundle bundleResultado = data.getExtras();
                    Tarefa t = new Tarefa();
                    t.setTitulo(bundleResultado.getString(MainActivity.TITULO_TAREFA));
                    t.setDescricao(bundleResultado.getString(MainActivity.DESCRICAO_TAREFA));
                    t.setGrau(bundleResultado.getString(MainActivity.GRAU_TAREFA));
                   // t.setEstado(bundleResultado.getString(MainActivity.ESTADO_TAREFA));
                    t.setDataLimite(bundleResultado.getString(MainActivity.HORA_LIMITE_TAREFA));

                    TarefaDao.getInstance().addTarefas(t);
                } else if (requestCode == MainActivity.REQUEST_DETALHES_TAGS && resultCode == Activity.RESULT_OK && data != null) {

                    Bundle bundleResultado = data.getExtras();
                    Etiqueta e = new Etiqueta();
                    e.setTag(bundleResultado.getString(MainActivity.DESCRICAO_TAGS));
                    EtiquetaDao.getInstance().addEtiqueta(e);
                }
            }
    protected void onResume() {
        super.onResume();
        adapter.setTarefas(TarefaDao.getInstance().getTarefa());
    }


}
