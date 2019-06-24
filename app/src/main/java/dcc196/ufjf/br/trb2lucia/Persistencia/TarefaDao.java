package dcc196.ufjf.br.trb2lucia.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;
import dcc196.ufjf.br.trb2lucia.Banco.TarefaDbHelper;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;

public class TarefaDao {
    private static TarefaDao instance = new TarefaDao();
    private TarefaDbHelper dbHelper;
    private Cursor cursor;

    public TarefaDao() {

    }
    public static TarefaDao getInstance(){
        return instance;
    }
    /*public ArrayList<Tarefa> getTarefa(){
        return tarefas;
    }*/
    public  void  addTarefas(Tarefa t){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TarefaContract.Tarefa.COLLUMN_TITULO,t.getTitulo());
        valores.put(TarefaContract.Tarefa.COLLUMN_DESCRICAO,t.getDescricao());
        valores.put(TarefaContract.Tarefa.COLLUMN_GRAU,t.getGrau());
       // valores.put(TarefaContract.Tarefa.COLLUMN_ESTADO,t.getEstado());
        valores.put(TarefaContract.Tarefa.COLLUMN_DATAINCIO,t.getDatalimite());
        db.insert(TarefaContract.Tarefa.TABLE_NAME,null,valores);

    }
}
