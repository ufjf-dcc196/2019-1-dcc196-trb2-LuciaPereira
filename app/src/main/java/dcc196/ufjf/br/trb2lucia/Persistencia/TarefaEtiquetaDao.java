package dcc196.ufjf.br.trb2lucia.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;
import dcc196.ufjf.br.trb2lucia.Banco.TarefaDbHelper;
import dcc196.ufjf.br.trb2lucia.Modelo.Etiqueta;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;

public class TarefaEtiquetaDao {
    private static TarefaEtiquetaDao instance = new TarefaEtiquetaDao();
    private TarefaDbHelper dbHelper;
    private Cursor cursor;

    public static TarefaEtiquetaDao getInstance() {
        return  instance;
    }
    private TarefaEtiquetaDao(){}

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
    public void inicializarDBHelper(Context c){
        dbHelper = new TarefaDbHelper(c);

    }
    public void removerTarefaEtiqueta(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("tarefas","ID_TAREFA=? "
                ,new String[]{String.valueOf(id)});
    }
    public void add(int idTags, int idTarefa){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TarefaContract.Etiqueta.COLUMN_NAME_TAG, idTags);
        valores.put(TarefaContract.Etiqueta._ID,idTarefa);
        db.insert(TarefaContract.TarefaEtiqueta.TABLE_NAME,null, valores);
    }
    public void removeTarefaEtiqueta(int idEtiqueta, int idTarefa){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("tarefa_etiqueta","ID_TAG=? and ID_TAREFA = ?"
                ,new String[]{String.valueOf(idEtiqueta), String.valueOf(idTarefa)});

    }
    public ArrayList<Tarefa> getTarefaEtiqueta(int id) {
        cursor = getAllTarefaEtiqueta(id,"TAREFA");
        ArrayList<Tarefa> tarefas = new ArrayList<>();
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
                //temp.setEstado(Estado(cursor.getString(idxEstado)));
                temp.setDataLimite(cursor.getString(idxData));
                temp.setIdTarefa(Integer.parseInt(cursor.getString(idxId)));
                tarefas.add(temp);
            }while (cursor.moveToNext());
        }
        return tarefas;
    }
    private Cursor getAllTarefaEtiqueta(int id, String argumento) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c;
        if("Tags".equals(argumento)){
            String MY_QUERY =
                    "select * from Etiqueta, TarefaEtiqueta where ID_TAREFA = ? " +
                            "AND ID_TAG = Etiqueta._ID";

            c= db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
        }else{
            String MY_QUERY =
                    "select * from Tarefa, TarefaEtiqueta where ID_TAG = ? " +
                            "AND ID_TAREFA = Tarefa._ID";

            c= db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
        }
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }
    public ArrayList<Etiqueta> getEtiquetaTarefa(int id) {
        cursor = getAllTarefaEtiqueta(id,"tags");
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        int indexDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Etiqueta.COLUMN_NAME_TAG);
        int indexIdEtiqueta = cursor.getColumnIndexOrThrow(TarefaContract.Etiqueta._ID);
        if(cursor.moveToFirst()){
            do{
                Etiqueta temp = new Etiqueta();
                temp.setTag(cursor.getString(indexDescricao));
                temp.setIdEtiqueta(Integer.parseInt(cursor.getString(indexIdEtiqueta)));
                etiquetas.add(temp);
            }while (cursor.moveToNext());
        }
        return etiquetas;
    }

}
