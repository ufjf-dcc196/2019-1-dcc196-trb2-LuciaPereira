package dcc196.ufjf.br.trb2lucia.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dcc196.ufjf.br.trb2lucia.Banco.TarefaContract;
import dcc196.ufjf.br.trb2lucia.Modelo.Etiqueta;
import dcc196.ufjf.br.trb2lucia.Modelo.Tarefa;
import dcc196.ufjf.br.trb2lucia.R;

public class TarefaEtiquetaAdapter extends RecyclerView.Adapter<TarefaEtiquetaAdapter.ViewHolder> {
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private OnTarefaEtiquetaClickListener listener;

    @NonNull
    @Override
    public TarefaEtiquetaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstTarefaView = inflater.inflate(R.layout.tarefa_etiqueta_layout, viewGroup,false);
        ViewHolder holder = new TarefaEtiquetaAdapter.ViewHolder(lstTarefaView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaEtiquetaAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txtTitulo.setText(tarefas.get(i).getTitulo());
        viewHolder.txtDescricao.setText(tarefas.get(i).getDescricao());
        viewHolder.txtGrau.setText(tarefas.get(i).getGrau());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public void setTarefas(ArrayList<Tarefa> t){
        tarefas = t;
        notifyDataSetChanged();
    }
    public interface  OnTarefaEtiquetaClickListener{
        void onTarefaEtiqueta(View view,int position);
        void onLongTarefaEtiquetaClick(View view, int position);
    }
    public void setOnTarefaEtiquetaClickListener(OnTarefaEtiquetaClickListener listener){
        this.listener = listener;
    }
    public void setEtiquetas(ArrayList<Etiqueta> e){
        etiquetas = e;
        notifyDataSetChanged();
    }


    /*public TarefaEtiquetaAdapter(ArrayList<Etiqueta> t) {
        this.tarefas = t;
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public TextView txtTitulo,txtDescricao,txtGrau;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.lst_titulo);
            txtDescricao = itemView.findViewById(R.id.lst_descricao);
            txtGrau = itemView.findViewById(R.id.lst_grau);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongTarefaEtiquetaClick(view,position);
                        }
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onTarefaEtiqueta(v, position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onTarefaEtiqueta(v, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongTarefaEtiquetaClick(v, position);
                }
            }
            return true;
        }
    }
}
