package dcc196.ufjf.br.trb2lucia;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View tarefaView = inflater.inflate(R.layout.activity_nova_tarefa, viewGroup, false);
        ViewHolder holder = new ViewHolder(tarefaView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int idxTitulo = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_TITULO);
        int idxDescricao = cursor.getColumnIndexOrThrow(TarefaContract.Tarefa.COLLUMN_DESCRICAO);

        cursor.moveToPosition(i);
        viewHolder.txtTitulo.setText(cursor.getString(idxTitulo));
        viewHolder.txtDescricao.setText(cursor.getString(idxDescricao));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitulo;
        public TextView txtDescricao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);

        }
    }

}
