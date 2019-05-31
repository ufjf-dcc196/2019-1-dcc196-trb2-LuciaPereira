package dcc196.ufjf.br.trb2lucia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TarefaDbHelper extends SQLiteOpenHelper {
    private static  final  String NOME_BANCO = "tarefa.db";
    private static final int VERSAO_SCHEMA = 1;

    public TarefaDbHelper(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tarefa (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " titulo TEXT, descricao TEXT, grau TEXT, estado TEXT, datainicio TEXT, datafim TEXT);");
    }

        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(TarefaContract.Tarefa.DROP_TABLE);
            onCreate(db);



    }
}
