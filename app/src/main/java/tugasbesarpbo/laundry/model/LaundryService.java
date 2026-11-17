
package tugasbesarpbo.laundry.model;

public class LaundryService extends Service {

    public LaundryService(String nama, double hargaPerKg) {
        super(nama, hargaPerKg);
    }

    @Override
    public double hitungHarga(double berat) {
        if (berat <= 0) {
            throw new IllegalArgumentException(
                    "Berat harus lebih dari 0 kg.");
        }

        return berat * hargaPerKg;
    }
}
