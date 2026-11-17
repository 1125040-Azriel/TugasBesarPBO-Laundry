
package tugasbesarpbo.laundry.model;

public abstract class User implements Authenticatable {
    protected String username;
    protected String password;
    protected Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username)
                && this.password.equals(password);
    }

    public abstract String getNamaRole();
}
