package dcc196.ufjf.br.trb2lucia.Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
    public void addEtiqueta(Etiqueta e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

    }
}
