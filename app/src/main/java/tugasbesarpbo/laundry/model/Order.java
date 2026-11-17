
package tugasbesarpbo.laundry.model;

public class Order implements Payable {
    private String id;
    private Customer customer;
    private Service service;
    private double berat;

    public Order(String id, Customer customer, Service service, double berat) {
        if (berat <= 0) {
            throw new IllegalArgumentException(
                    "Berat harus lebih dari 0 kg.");
        }

        this.id = id;
        this.customer = customer;
        this.service = service;
        this.berat = berat;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Service getService() {
        return service;
    }

    public double getBerat() {
        return berat;
    }

    @Override
    public double hitungPembayaran() {
        return service.hitungHarga(berat);
    }
}
