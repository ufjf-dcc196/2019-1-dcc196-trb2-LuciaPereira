package dcc196.ufjf.br.trb2lucia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaDbHelper;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaDao;
import dcc196.ufjf.br.trb2lucia.R;
import dcc196.ufjf.br.trb2lucia.TarefaAdapter;

public class MainActivity extends AppCompatActivity {
    private Button btnInserir;
    private Button btnExcluir;
    private Button btnTags;
    private RecyclerView listaTarefas;
    private TarefaAdapter adapter;
    private TarefaDbHelper dphelper;
    private static final int REQUEST_CADASTRAR_TAREFA = 1;
    private static final int REQUEST_DETALHES_TAGS = 2;


    public static  final String     TITULO_TAREFA ="TITULO";
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
        //dphelper = new TarefaDbHelper(getApplicationContext());

        TarefaDao.getInstance().inicializarDBHelper(getApplicationContext());
        listaTarefas = findViewById(R.id.rclTarefas);
        listaTarefas.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TarefaAdapter(TarefaDao.getInstance().getTarefa());
        listaTarefas.setAdapter(adapter);

        btnInserir = findViewById(R.id.btnNovo);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovaTarefa2.class);
                startActivityForResult(intent,REQUEST_CADASTRAR_TAREFA);


            }
        });
        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NovaTarefa2.class);
                startActivity(intent);

            }
        });
        btnTags =  findViewById(R.id.bntTags);
        btnTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EtiquetaActivity.class);
                startActivity(intent);
                startActivityForResult(intent,REQUEST_DETALHES_TAGS);


            }
        });

    }
}
