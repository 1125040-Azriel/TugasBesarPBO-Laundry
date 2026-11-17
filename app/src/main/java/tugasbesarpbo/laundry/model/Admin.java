
package tugasbesarpbo.laundry.model;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);
    }

    @Override
    public String getNamaRole() {
        return "Admin";
    }
}
