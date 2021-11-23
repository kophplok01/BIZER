package tr.xip.unimarnf.Models;

import com.google.firebase.database.ServerValue;

public class Reg {

    String uid;
    Object time;
    String displayname;
    String emailname;
    String phone;


    public Reg() {
    }

    public Reg(String uid, String displayname, String emailname, String phone) {
        this.uid = uid;
        this.time = ServerValue.TIMESTAMP;
        this.displayname = displayname;
        this.emailname = emailname;
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Object getTime() {
        return time;
    }

    public void setTime(Object time) {
        this.time = time;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmailname() {
        return emailname;
    }

    public void setEmailname(String emailname) {
        this.emailname = emailname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
