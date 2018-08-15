package id.go.bpkp.dashboardstrategis.model;

public class JumlahPP {

    private String label, fokus, jumlah, persentase;

    public JumlahPP(String label, String fokus, String jumlah, String persentase) {
        this.label = label;
        this.fokus = fokus;
        this.jumlah = jumlah;
        this.persentase = persentase;
    }

    public String getLabel() {
        return label;
    }

    public String getFokus() {
        return fokus;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getPersentase() {
        return persentase;
    }
}
