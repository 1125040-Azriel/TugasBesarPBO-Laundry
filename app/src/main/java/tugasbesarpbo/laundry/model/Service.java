
package tugasbesarpbo.laundry.model;

public abstract class Service {
    protected String nama;
    protected double hargaPerKg;

    public Service(String nama, double hargaPerKg) {
        this.nama = nama;
        this.hargaPerKg = hargaPerKg;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public abstract double hitungHarga(double berat);
}
