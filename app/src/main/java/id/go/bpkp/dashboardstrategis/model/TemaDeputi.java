package id.go.bpkp.dashboardstrategis.model;

public class TemaDeputi {

    int id;
    String judulTema;
    boolean isActive;

    public int getId() {
        return id;
    }

    public String getJudulTema() {
        return judulTema;
    }

    public boolean isActive() {
        return isActive;
    }

    public TemaDeputi(int id, String judulTema, boolean isActive) {

        this.id = id;
        this.judulTema = judulTema;
        this.isActive = isActive;
    }
}
