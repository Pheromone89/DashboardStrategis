package id.go.bpkp.dashboardstrategis.model;

public class Deputi {
    int index;
    String nama, pimpinan;

    public Deputi(int index, String nama, String pimpinan) {
        this.index = index;
        this.nama = nama;
        this.pimpinan = pimpinan;
    }

    public int getIndex() {
        return index;
    }

    public String getNama() {
        return nama;
    }

    public String getPimpinan() {
        return pimpinan;
    }
}
