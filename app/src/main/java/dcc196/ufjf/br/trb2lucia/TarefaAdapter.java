package dcc196.ufjf.br.trb2lucia;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder>{

    private Cursor cursor;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();

   private TarefaAdapter.OnTarefaClickListener listener;
    public TarefaAdapter(Cursor c){
        cursor = c;
    }

    public TarefaAdapter(ArrayList<Tarefa> tarefa){
        this.tarefas= tarefa;
    }

    //public void setCursor(Cursor c) {
  //      cursor = c;
   //     notifyDataSetChanged();
   // }
    public void setTarefas(ArrayList<Tarefa> t){
        tarefas = t;
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
//verificar
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
        return tarefas.size();
                //cursor.getCount();
    }

    public void setOnTarefaClickListener(OnTarefaClickListener listener) {
        this.listener = listener;
    }



    public interface OnTarefaClickListener {
        public void onTarefaClick(View v, int position);
        void onLongParticipanteClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int position = getAdapterPosition();
                  if(listener!=null){
                      listener.onTarefaClick(v, position);

              }

              }
          });


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position!=RecyclerView.NO_POSITION){
                listener.onTarefaClick(v,position);
            }

        }
    }


}


