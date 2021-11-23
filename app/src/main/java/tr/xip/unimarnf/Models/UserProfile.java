package tr.xip.unimarnf.Models;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
    public String userP;
    public String userPhone;
    public String userprimerN;
    public String userprimerA;

    public UserProfile() {}

    public UserProfile(String userAge, String userEmail, String userName, String userP, String userPhone, String userprimerN, String userprimerA) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userP = userP;
        this.userPhone = userPhone;
        this.userprimerN = userprimerN;
        this.userprimerA = userprimerA;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserP() {
        return userP;
    }

    public void setUserP(String userP) {
        this.userP = userP;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserprimerN() {
        return userprimerN;
    }

    public void setUserprimerN(String userprimerN) {
        this.userprimerN = userprimerN;
    }

    public String getUserprimerA() {
        return userprimerA;
    }

    public void setUserprimerA(String userprimerA) {
        this.userprimerA = userprimerA;
    }
}
