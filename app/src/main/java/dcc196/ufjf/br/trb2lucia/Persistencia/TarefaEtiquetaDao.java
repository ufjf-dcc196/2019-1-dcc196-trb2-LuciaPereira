package dcc196.ufjf.br.trb2lucia.Persistencia;

import android.database.Cursor;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaDbHelper;

public class TarefaEtiquetaDao {
    private static TarefaEtiquetaDao instance = new TarefaEtiquetaDao();
    private TarefaDbHelper dbHelper;
    private Cursor cursor;

    public TarefaEtiquetaDao getInstance() {return  instance; }

}
