package dcc196.ufjf.br.trb2lucia.Banco;

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
        public  static final  String CREATE_TAREFA = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT , %s TEXT ,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)", TABLE_NAME, _ID, COLLUMN_TITULO,COLLUMN_DESCRICAO,COLLUMN_GRAU,COLLUMN_ESTADO,COLLUMN_DATAINCIO,COLLUMN_DATAFIM);
        public static final String DROP_TAREFA = "DROP TABLE IF EXISTS" + Tarefa.TABLE_NAME;
    }

    public static final class Etiqueta implements BaseColumns{
        public static final String TABLE_NAME = "ETIQUETA";
        public static final String COLUMN_NAME_TAG = "TAG";

        public final static String CREATE_ETIQUETA = "CREATE TABLE "+Etiqueta.TABLE_NAME+" ("
                + Etiqueta._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Etiqueta.COLUMN_NAME_TAG+ " TEXT "
                +")";
        public final static String DROP_ETIQUETA = "DROP TABLE IF EXISTS" + Etiqueta.TABLE_NAME;

    }
    public final class TarefaEtiqueta implements BaseColumns {
        public final static String TABLE_NAME = "TarefaEtiqueta";
        public final static String  COLUMN_ID_TAG = "ID_TAG";
        public final static String COLUMN_ID_TAREFA = "ID_TAREFA";

        public final static String CREATE_TAREFA_ETIQUETA  = "CREATE TABLE "+ TarefaEtiqueta.TABLE_NAME+" ("
                + TarefaEtiqueta._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TarefaEtiqueta.COLUMN_ID_TAG + " INTEGER,"
                + TarefaEtiqueta.COLUMN_ID_TAREFA + " INTEGER "
                +")";

        public final static String DROP_TAREFA_ETIQUETA = "DROP TABLE IF EXISTS "+TarefaEtiqueta.TABLE_NAME;
    }
}
