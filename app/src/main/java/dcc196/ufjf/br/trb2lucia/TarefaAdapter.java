package dcc196.ufjf.br.trb2lucia;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder>{



    private Cursor cursor;
    public TarefaAdapter(Cursor c){
        cursor = c;
    }

    public void setCursor(Cursor c) {
        cursor = c;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tarefaView = inflater.inflate(R.layout.tarefa_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(tarefaView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DESCRICAO);
        int idxGrau = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_GRAU);
        int idxEstado = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_ESTADO);
        int idxData = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DATAINCIO);


        cursor.moveToPosition(i);

        viewHolder.txtTitulo.setText(cursor.getString(idxTitulo));
        viewHolder.txtDescricao.setText(cursor.getString(idxDescricao));
        viewHolder.txtGrau.setText(cursor.getString(idxGrau));
        viewHolder.txtEstado.setText(cursor.getString(idxEstado));
        viewHolder.txtData.setText(cursor.getString(idxData));

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public TextView txtDescricao;
        public TextView txtGrau;
        public TextView txtEstado;
        public TextView txtData;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          txtTitulo = itemView.findViewById(R.id.txt_tarefa_titulo);
          txtDescricao = itemView.findViewById(R.id.txt_tarefa_descricao);
          txtGrau = itemView.findViewById(R.id.txt_tarefa_grau);


        }
    }

}
