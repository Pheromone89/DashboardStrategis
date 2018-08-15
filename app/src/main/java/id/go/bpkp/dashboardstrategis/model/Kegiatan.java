package id.go.bpkp.dashboardstrategis.model;

public class Kegiatan {

    int id;
    String kegiatan;
    int budget;

    public Kegiatan(int id, String kegiatan, int budget) {
        this.id = id;
        this.kegiatan = kegiatan;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public int getBudget() {
        return budget;
    }
}
