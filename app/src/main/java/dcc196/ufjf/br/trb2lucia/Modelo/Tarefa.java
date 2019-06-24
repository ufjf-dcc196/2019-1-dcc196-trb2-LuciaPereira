package dcc196.ufjf.br.trb2lucia.Modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarefa {
    private String titulo;
    private long idTarefa;
    private String descricao;
    private String grau;
    private Estado estado;
    private String dataLimite;
    private String dataAtual;
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");



    public Tarefa(String titulo, long idTarefa, String descricao, String grau, Estado estado, String dataLimite, String dataAtual) {
        this.titulo = titulo;
        this.idTarefa = idTarefa;
        this.descricao = descricao;
        this.grau = grau;
        this.estado = estado;
        this.dataLimite = dataLimite;
        this.dataAtual = dataAtual;
    }

    public Tarefa() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDatalimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = sdf.format(new Date());
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = sdf.format(new Date());
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", idTarefa=" + idTarefa +
                ", descricao='" + descricao + '\'' +
                ", grau='" + grau + '\'' +
                ", estado=" + estado +
                ", dataLimite=" + dataLimite +
                ", dataAtual=" + dataAtual +
                '}';
    }
}
