package id.go.bpkp.dashboardstrategis.model;

public class IndikatorUmum {

    private int id;
    private String tag, judul, tahun, label, nilai;

    public IndikatorUmum(String tag, int id, String judul, String tahun, String label, String nilai) {
        this.tag = tag;
        this.id = id;
        this.judul = judul;
        this.tahun = tahun;
        this.label = label;
        this.nilai = nilai;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getJudul() {
        return judul;
    }

    public String getTahun() {
        return tahun;
    }

    public String getLabel() {
        return label;
    }

    public String getNilai() {
        return nilai;
    }
}
