package inventory.module;

import java.util.regex.Pattern;

public class Customer {

    private String name;
    private int id;
    private double receivables;
    private String email;
    private String mobile;
    private String companyName;
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getReceivables() {
        return receivables;
    }

    public void setReceivables(double receivables) {
        this.receivables = receivables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public boolean setEmail(String email) {

        boolean res = Pattern.matches("^[A-Za-z][A-Za-z0-9+_.-]+@(.+)$", email);

        if (res) {

            this.email = email;

            return true;

        } else return false;
    }
    public String getMobile() {
        return mobile;
    }
    public boolean setMobile(String mobile) {
        boolean res = Pattern.matches("^[7-9][0-9]{9}$", mobile);
        if (res) {
            this.mobile = mobile;
            return true;
        }
        return false;
    }

    @Override
    public String toString(){

        System.out.println("\n|" + "NAME    : " + this.name);
        System.out.println("|" + "MOBILE  : " + this.mobile);
        System.out.println("|" + "MAIL    : " + this.email);
        System.out.println("|" + "COMPANY : " + this.companyName);
        System.out.println("|" + "ID      : " + this.id);
        return "";
    }

}
