package go2code.mtp.org.registerandlogin;

import com.google.gson.annotations.Expose;

public class LoginModel {

    @Expose
    private String status;
    @Expose
    private String login;

    /**
     * @return The status
     */

    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The login
     */

    public String getLogin() {
        return login;
    }

    /**
     * @param login The login
     */

    public void setLogin(String login) {
        this.login = login;
    }
}