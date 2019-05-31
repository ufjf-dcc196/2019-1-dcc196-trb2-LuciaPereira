package dcc196.ufjf.br.trb2lucia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnInserir;
    private Button btnExcluir;
    private RecyclerView listaTarefas;
    private TarefaAdapter adapter;
    private TarefaDbHelper dphelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dphelper = new TarefaDbHelper(getApplicationContext());
        listaTarefas = (RecyclerView)findViewById(R.id.rclTarefas);
        listaTarefas.setLayoutManager(new LinearLayoutManager(this));
       // adapter = new TarefaAdapter();
        listaTarefas.setAdapter(adapter);


        btnInserir = (Button)findViewById(R.id.btnNovo);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,activity_nova_tarefa.class);

              //  startActivityForResult(intent,0);

            }
        });
        btnExcluir = (Button)findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void getCursorTarefas() {
    }
}
