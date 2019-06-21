package dcc196.ufjf.br.trb2lucia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class TarefaContract {



    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefa";
        public static final String COLLUMN_TITULO = "titulo";
        public static final String COLLUMN_DESCRICAO = "descricao";
        public static final String COLLUMN_GRAU= "grau";
        public static final String COLLUMN_ESTADO= "estado";
        public static final String COLLUMN_DATAINCIO = "datalimite";
        public static final String COLLUMN_DATAFIM = "dataatual";
        public  static final  String CREATE_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT , %s TEXT ,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)", TABLE_NAME, _ID, COLLUMN_TITULO,COLLUMN_DESCRICAO,COLLUMN_GRAU,COLLUMN_ESTADO,COLLUMN_DATAINCIO,COLLUMN_DATAFIM);
        public  static  final String DROP_TABLE  = String.format("DROP TABLE  %s" ,TABLE_NAME);
    }
    public static void  updateTarefa(SQLiteDatabase db, int id, String titulo, String descricao, String grau, String estado, String data){
        int update = db.update(Tarefa.TABLE_NAME, createContentValue(titulo, descricao, grau, estado, data),Tarefa._ID + "= ?", new String[]{Integer.toString(id)});
    }

    private static ContentValues createContentValue(String titulo, String descricao, String grau, String estado, String data) {
        ContentValues c = new ContentValues();
        c.put(Tarefa.COLLUMN_TITULO,titulo);
        c.put(Tarefa.COLLUMN_DESCRICAO,descricao);
        c.put(Tarefa.COLLUMN_GRAU,grau);
        c.put(Tarefa.COLLUMN_ESTADO,estado);
        c.put(Tarefa.COLLUMN_DATAINCIO,data);
        return c;
    }
    public static void Salvetarefa(SQLiteDatabase db, String titulo, String descricao, String grau, String estado, String data) {
        db.insert(Tarefa.TABLE_NAME,null, createContentValue(titulo,descricao, grau, estado, data));
    }







}
