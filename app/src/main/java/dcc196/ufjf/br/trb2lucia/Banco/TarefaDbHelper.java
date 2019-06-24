package dcc196.ufjf.br.trb2lucia.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;

public class TarefaDbHelper extends SQLiteOpenHelper {
    private static  final  String NOME_BANCO = "organizador.db";
    private static final int VERSAO_SCHEMA = 2;

    public TarefaDbHelper(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefaContract.Tarefa.CREATE_TAREFA);
        db.execSQL(TarefaContract.Etiqueta.CREATE_ETIQUETA);
    }

        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(TarefaContract.Tarefa.DROP_TAREFA);
            onCreate(db);



    }
}
