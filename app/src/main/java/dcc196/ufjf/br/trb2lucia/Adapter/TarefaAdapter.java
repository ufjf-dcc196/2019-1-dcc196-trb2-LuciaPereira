package dcc196.ufjf.br.trb2lucia.Adapter;


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
import dcc196.ufjf.br.trb2lucia.R;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder>{

    private Cursor cursor;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private TarefaAdapter.OnTarefaClickListener listener;

    public TarefaAdapter(ArrayList<Tarefa> tarefa){
        this.tarefas= tarefa;
    }

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
        viewHolder.txtTitulo.setText(tarefas.get(i).getTitulo());
        viewHolder.txtDescricao.setText(tarefas.get(i).getDescricao());
        viewHolder.txtGrau.setText(tarefas.get(i).getGrau());
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    public void setOnTarefaClickListener(OnTarefaClickListener listener) {
        this.listener = listener;
    }

    public interface OnTarefaClickListener {
        void onTarefaClick(View v, int position);
        void onLongTarefaClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onTarefaClick(view,position);
                        }
                    }
                    return false;
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

        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongTarefaClick(view, position);
                }
            }
            return true;
        }
    }


}


