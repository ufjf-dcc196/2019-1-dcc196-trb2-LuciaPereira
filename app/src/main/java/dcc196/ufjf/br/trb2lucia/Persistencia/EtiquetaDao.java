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

public class EtiquetaDao {
    private static EtiquetaDao instance = new EtiquetaDao();
    private Cursor cursor;
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private TarefaDbHelper dbHelper;

    public EtiquetaDao() {
    }
    public static EtiquetaDao getInstance(){
        return instance;
    }
    public ArrayList<Etiqueta> getEtiqueta(){
        return etiquetas;
    }

    public void inicializarDBHelper(Context c){
        dbHelper = new TarefaDbHelper(c);
    }

    private void InsertBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TarefaContract.Tarefa.COLLUMN_DESCRICAO, "Etiqueta inserida");

    }
    public void addEtiqueta(Etiqueta e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(TarefaContract.Etiqueta.COLUMN_NAME_TAG,e.getTag());
        db.insert(TarefaContract.Etiqueta.TABLE_NAME, null, valores);
    }
    public void removeEtiqueta(Etiqueta e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete(TarefaContract.Etiqueta.TABLE_NAME,
                "_ID=?",new String[]{String.valueOf(e.getIdEtiqueta())});
    }
    public void atualizarEtiqueta(Etiqueta e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Descricao", e.getTag());
        db.update("Etiqueta",cv,
                "_ID=?",new String[]{String.valueOf(e.getIdEtiqueta())});
    }
    public ArrayList<Etiqueta> getEtiquetas() {
        cursor = getAllEtiqueta();
        ArrayList<Etiqueta> e = new ArrayList<>();
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

    private Cursor getAllEtiqueta() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                TarefaContract.Etiqueta.COLUMN_NAME_TAG,
                TarefaContract.Etiqueta._ID
        };
        Cursor c = db.query(TarefaContract.Etiqueta.TABLE_NAME,visao,null,null,null,null,null,null);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }

}
