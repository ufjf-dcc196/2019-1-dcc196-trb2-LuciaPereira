package dcc196.ufjf.br.trb2lucia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dcc196.ufjf.br.trb2lucia.Adapter.TarefaEtiquetaAdapter;
import dcc196.ufjf.br.trb2lucia.Modelo.Etiqueta;
import dcc196.ufjf.br.trb2lucia.Persistencia.EtiquetaDao;
import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaEtiquetaDao;
import dcc196.ufjf.br.trb2lucia.R;

public class ListTagsTarefas extends AppCompatActivity {
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private RecyclerView listaTarefaTags;
    private int idTarefa;
    private TarefaEtiquetaAdapter adapter;
    public static final String ORIGEM_TAREFA = "OrigemActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tags_tarefas);

        EtiquetaDao.getInstance().inicializarDBHelper(getApplicationContext());
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        idTarefa = bundleResult.getInt(AtualizaTarefa.ID_TAREFA);

        listaTarefaTags = findViewById(R.id.lista_tag_tarefa);
        listaTarefaTags.setLayoutManager(new LinearLayoutManager(this));
       // adapter = new ListTagsTarefas(TarefaEtiquetaDao.getInstance().getEtiquetaNaosalva(idTarefa));
        adapter.setEtiquetas(TarefaEtiquetaDao.getInstance().getEtiquetaNaosalva(idTarefa));
        listaTarefaTags.setAdapter(adapter);


    adapter.setOnTarefaEtiquetaClickListener(new TarefaEtiquetaAdapter.OnTarefaEtiquetaClickListener() {
        @Override
        public void onTarefaEtiqueta(View view, int position) {

        }


        @Override
        public void onLongTarefaEtiquetaClick(View view, int position) {

        }

    });


    }

}
