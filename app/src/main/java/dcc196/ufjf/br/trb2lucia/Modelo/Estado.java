package dcc196.ufjf.br.trb2lucia.Modelo;

public class Estado {
    private long idEstado;
    private String status;

    public Estado(long idEstado, String status) {
        this.idEstado = idEstado;
        this.status = status;
    }

    public Estado() { }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
