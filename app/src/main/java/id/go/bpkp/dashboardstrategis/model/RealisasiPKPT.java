package id.go.bpkp.dashboardstrategis.model;

public class RealisasiPKPT {

    String label, realisasi, total, persentase;

    public RealisasiPKPT(String label, String realisasi, String total, String persentase) {
        this.label = label;
        this.realisasi = realisasi;
        this.total = total;
        this.persentase = persentase;
    }

    public String getLabel() {
        return label;
    }

    public String getRealisasi() {
        return realisasi;
    }

    public String getTotal() {
        return total;
    }

    public int getRealisasiInt() {
        return Integer.parseInt(realisasi);
    }

    public int getTotalInt() {
        return Integer.parseInt(total);
    }

    public String getPersentase() {
        return persentase;
    }
}
