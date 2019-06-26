package dcc196.ufjf.br.trb2lucia.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import dcc196.ufjf.br.trb2lucia.Adapter.EtiquetaAdapter;
import dcc196.ufjf.br.trb2lucia.R;

public class ListaTags extends AppCompatActivity {
    private RecyclerView listaTags;
    private static EtiquetaAdapter lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tags);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();

    }
}
