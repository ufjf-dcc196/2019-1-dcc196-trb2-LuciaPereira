package dcc196.ufjf.br.trb2lucia;

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



}
