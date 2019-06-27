package dcc196.ufjf.br.trb2lucia.Modelo;

public class Etiqueta {
    private String tag;
    private int idEtiqueta;

    public Etiqueta(String tag, int idEtiqueta) {
        this.tag = tag;
        this.idEtiqueta = idEtiqueta;
    }

    public Etiqueta() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(int idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }
}
