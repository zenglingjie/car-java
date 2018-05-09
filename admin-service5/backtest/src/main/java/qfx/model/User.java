package qfx.model;

/**
 * Created by asus b250 on 2017/9/11.
 */
public class User {
    private int id;
    private String email;
    private String cnName;
    private String password;
    private  int privalige;
    private  int[] privaliges;
    private String[] priName;
    private String  pri;
    private  int pasNumber;
    private String md5password;

    public String getMd5password() {
        return md5password;
    }

    public void setMd5password(String md5password) {
        this.md5password = md5password;
    }

    public int getPasNumber() {
        return pasNumber;
    }

    public void setPasNumber(int pasNumber) {
        this.pasNumber = pasNumber;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String[] getPriName() {
        return priName;
    }

    public void setPriName(String[] priName) {
        this.priName = priName;
    }

    public int[] getPrivaliges() {
        return privaliges;
    }

    public void setPrivaliges(int[] privaliges) {
        this.privaliges = privaliges;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", cnName='" + cnName + '\'' +
                ", password='" + password + '\'' +
                ", privalige=" + privalige +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivalige() {
        return privalige;
    }

    public void setPrivalige(int privalige) {
        this.privalige = privalige;
    }

    public User(int id, String email, String cnName, String password, int privalige, int[] privaliges, String[] priName, String pri, int pasNumber, String md5password) {
        this.id = id;
        this.email = email;
        this.cnName = cnName;
        this.password = password;
        this.privalige = privalige;
        this.privaliges = privaliges;
        this.priName = priName;
        this.pri = pri;
        this.pasNumber = pasNumber;
        this.md5password = md5password;
    }

    public User(){

    }
}
