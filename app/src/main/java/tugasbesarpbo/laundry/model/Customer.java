
package tugasbesarpbo.laundry.model;

public class Customer {
    private String id;
    private String nama;
    private String nomorTelepon;

    public Customer(String id, String nama, String nomorTelepon) {
        this.id = id;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }
}
