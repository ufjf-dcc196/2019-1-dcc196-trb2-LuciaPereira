package dcc196.ufjf.br.trb2lucia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnInserir;
    private Button btnExcluir;
    private Button btnTags;
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
        adapter = new TarefaAdapter(getCursorTarefas());

        listaTarefas.setAdapter(adapter);



        final Random rnd = new Random();
        btnInserir = (Button)findViewById(R.id.btnNovo);
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,NovaTarefa.class);
                //startActivityForResult(intent,0);
                SQLiteDatabase db = dphelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(TarefaContract.Tarefa.COLLUMN_TITULO,"Titulo" + rnd.nextInt(100));
                valores.put(TarefaContract.Tarefa.COLLUMN_DESCRICAO,"Descricao" + rnd.nextInt(100));
                valores.put(TarefaContract.Tarefa.COLLUMN_GRAU,"Titulo" + rnd.nextInt(100));
                valores.put(TarefaContract.Tarefa.COLLUMN_ESTADO,"Titulo" + rnd.nextInt(20));
                valores.put(TarefaContract.Tarefa.COLLUMN_DATAINCIO,"Titulo" + rnd.nextInt(50));
                long id = db.insert(TarefaContract.Tarefa.TABLE_NAME,null,valores);
                Log.i("DBINFO","O idenfificador da tarefa foi criado" + id);
                adapter.setCursor(getCursorTarefas());



            }
        });
        btnExcluir = (Button)findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTags = (Button) findViewById(R.id.bntTags);
        btnTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private Cursor getCursorTarefas() {
        SQLiteDatabase db =  dphelper.getReadableDatabase();
        String[] visao = {
                TarefaContract.Tarefa.COLLUMN_TITULO,
                TarefaContract.Tarefa.COLLUMN_DESCRICAO,
                TarefaContract.Tarefa.COLLUMN_GRAU,
                TarefaContract.Tarefa.COLLUMN_ESTADO,
                TarefaContract.Tarefa.COLLUMN_DATAINCIO
        };
        return  db.query(TarefaContract.Tarefa.TABLE_NAME,visao,null,null,null,null,null);




    }
}
