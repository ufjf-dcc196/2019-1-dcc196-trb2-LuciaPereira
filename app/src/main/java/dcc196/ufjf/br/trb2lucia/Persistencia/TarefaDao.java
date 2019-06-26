package dcc196.ufjf.br.trb2lucia.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public void inicializarDBHelper(Context c){
        dbHelper = new TarefaDbHelper(c);

    }
    private Cursor getAllTarefa() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TarefaContract.Tarefa.COLLUMN_TITULO,
                TarefaContract.Tarefa.COLLUMN_DESCRICAO,
                TarefaContract.Tarefa.COLLUMN_GRAU,
                TarefaContract.Tarefa.COLLUMN_ESTADO,
                TarefaContract.Tarefa.COLLUMN_DATAINCIO,
                TarefaContract.Tarefa._ID

        };
       // String sort = TarefaContract.Tarefa.COLLUMN_TITULO ;
        Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME,visao,null,null,null,null,null);
        Log.i("SQLTEST", "getCursor" +c.getCount());
        return c;
    }
    public ArrayList<Tarefa> getTarefa() {
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        cursor = getAllTarefa();
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DESCRICAO);
        int idxGrau = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_GRAU);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_ESTADO);
        int idxData = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DATAINCIO);
        int idxId = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa._ID);

        if(cursor.moveToFirst()){
            do{
                Tarefa temp = new Tarefa();
                temp.setTitulo(cursor.getString(idxTitulo));
                temp.setDescricao(cursor.getString(idxDescricao));
                temp.setGrau(cursor.getString(idxGrau));
                temp.setIdTarefa(Integer.parseInt(cursor.getString(idxId)));
                tarefas.add(temp);
            }while (cursor.moveToNext());
        }
        return tarefas;
    }
    public void InsertBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TarefaContract.Tarefa.COLLUMN_TITULO, "Natacao");
        valores.put(TarefaContract.Tarefa.COLLUMN_DESCRICAO,"levar as criancas");
        valores.put(TarefaContract.Tarefa.COLLUMN_GRAU, "1");
        valores.put(TarefaContract.Tarefa.COLLUMN_ESTADO, "Em execucao");
        valores.put(TarefaContract.Tarefa.COLLUMN_DATAINCIO, "terça");
        db.insert(TarefaContract.Tarefa.TABLE_NAME,null,valores);

    }
    public void RemoveTarefa(Tarefa t)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Tarefa","_ID=" , new String[]{String.valueOf(t.getIdTarefa())});
    }
    public void atulizarTarefa(Tarefa t)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Tarefa:", t.getTitulo());
        cv.put("Descricao", t.getDescricao());
        cv.put("Grau",t.getGrau());
        //cv.put("Estado",t.getEstado());
        cv.put("Data", t.getDatalimite());
        db.update("Tarefa",cv,
                "_ID=?",new String[]{String.valueOf(t.getIdTarefa())});

    }

    public Tarefa getTarefaPeloId(int id){
        Tarefa temp = null;
        cursor = getTarefaPeloBD(id);
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DESCRICAO);
        int idxGrau = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_GRAU);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_ESTADO);
        int idxData = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DATAINCIO);
        int idxId = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa._ID);
        if(cursor.moveToFirst()){

                temp = new Tarefa();
                temp.setTitulo(cursor.getString(idxTitulo));
                temp.setDescricao(cursor.getString(idxDescricao));
                temp.setGrau(cursor.getString(idxGrau));
                temp.setIdTarefa(Integer.parseInt(cursor.getString(idxId)));
        }
        return temp;
    }
    private Cursor getTarefaPeloBD(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TarefaContract.Tarefa.COLLUMN_TITULO,
                TarefaContract.Tarefa.COLLUMN_DESCRICAO,
                TarefaContract.Tarefa.COLLUMN_GRAU,
                TarefaContract.Tarefa.COLLUMN_ESTADO,
                TarefaContract.Tarefa.COLLUMN_DATAINCIO,
                TarefaContract.Tarefa._ID
        };
        String sort = TarefaContract.Tarefa.COLLUMN_TITULO+ " DESC";

        Cursor c = db.query(TarefaContract.Tarefa.TABLE_NAME, visao,
                "_ID = ?" ,new String[]{String.valueOf(id)} ,null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }



}
