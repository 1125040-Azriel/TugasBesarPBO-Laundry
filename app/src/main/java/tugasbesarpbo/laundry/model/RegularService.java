
package tugasbesarpbo.laundry.model;

public class RegularService extends Service {

    public RegularService() {
        super("Laundry Reguler", 7000);
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
