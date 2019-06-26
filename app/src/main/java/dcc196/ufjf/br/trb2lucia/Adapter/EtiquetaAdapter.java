package dcc196.ufjf.br.trb2lucia.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dcc196.ufjf.br.trb2lucia.Modelo.Etiqueta;
import dcc196.ufjf.br.trb2lucia.R;

public class EtiquetaAdapter extends RecyclerView.Adapter<EtiquetaAdapter.ViewHolder> {
    private ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    private OnEtiquetaClickListener listener;

    public  void setEtiquetas(ArrayList<Etiqueta> e){
        etiquetas = e;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EtiquetaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstEtiquetaView = inflater.inflate(R.layout.etiquetas_layout, viewGroup, false);
        ViewHolder holderView = new ViewHolder(lstEtiquetaView);
        return holderView;
    }

    @Override
    public void onBindViewHolder(@NonNull EtiquetaAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txtId.setText(" "+String.valueOf(i+1));
        viewHolder.txtDescricaoEtiqueta.setText(etiquetas.get(i).getTag());
            }

    @Override
    public int getItemCount() {
        return etiquetas.size();
    }
    public interface OnEtiquetaClickListener{
        void onEtiquetaClick(View view,int position);
        void onLongEtiquetaClick(View view,int position);
    }
    public void setOnEtiquetaClickListener(OnEtiquetaClickListener listener){
        this.listener = listener;
    }
    public EtiquetaAdapter(ArrayList<Etiqueta> etiquetas)
    {this.etiquetas = etiquetas;}

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtDescricaoEtiqueta;
        public TextView txtId;


        public ViewHolder(View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.tag_num);
            txtDescricaoEtiqueta  = itemView.findViewById(R.id.tag_descricao);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onLongEtiquetaClick(view, position);
                        }
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEtiquetaClick(v, position);
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
                    listener.onEtiquetaClick(v, position);
                }
            }

        }

        @Override
        public boolean onLongClick(View v) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongEtiquetaClick(v, position);
                }
            }
            return false;
        }

    }
}
