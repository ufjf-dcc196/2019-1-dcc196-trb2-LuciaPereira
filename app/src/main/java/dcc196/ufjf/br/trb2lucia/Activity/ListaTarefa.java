package dcc196.ufjf.br.trb2lucia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dcc196.ufjf.br.trb2lucia.Persistencia.TarefaEtiquetaDao;
import dcc196.ufjf.br.trb2lucia.R;
import dcc196.ufjf.br.trb2lucia.Adapter.TarefaAdapter;

public class ListaTarefa extends AppCompatActivity {
    private RecyclerView rvListTarefasEtiquetas;
    private TarefaAdapter adapter;
    private int idTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        TarefaEtiquetaDao.getInstance().inicializarDBHelper(getApplicationContext());

        rvListTarefasEtiquetas = findViewById(R.id.rvListaTarefas);
        idTags = bundleResult.getInt(AtualizaTarefa.ID_TAG);
        adapter =  new TarefaAdapter(TarefaEtiquetaDao.getInstance().getTarefaEtiqueta(idTags));
        rvListTarefasEtiquetas.setLayoutManager(new LinearLayoutManager(this));
        rvListTarefasEtiquetas.setAdapter(adapter);



    }
}

