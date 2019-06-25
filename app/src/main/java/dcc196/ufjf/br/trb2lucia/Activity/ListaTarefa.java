package dcc196.ufjf.br.trb2lucia.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import dcc196.ufjf.br.trb2lucia.R;

public class ListaTarefa extends AppCompatActivity {
    private RecyclerView rvListTarefasEtiquetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tarefa);
    }
}
