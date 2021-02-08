package com.ghostCoder.invoicePro.models;

public class UserModel {
    //fields
    String username;
    String password;
    String salt;
    String securityQue;
    String securityAns;
    String transportName;

    String address1;
    String address2;
    String city;

    String mobileNo;
    String telephoneNo;
    String emailId;

    String aboutTransport;
    String gstNo;
    String pinCode;

    public UserModel(String username, String password, String salt, String securityQue, String securityAns,
                     String transportName, String address1, String address2, String city, String mobileNo,
                     String telephoneNo, String emailId, String aboutTransport, String gstNo, String pinCode) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.securityQue = securityQue;
        this.securityAns = securityAns;
        this.transportName = transportName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.mobileNo = mobileNo;
        this.telephoneNo = telephoneNo;
        this.emailId = emailId;
        this.aboutTransport = aboutTransport;
        this.gstNo = gstNo;
        this.pinCode = pinCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecurityQue() {
        return securityQue;
    }

    public void setSecurityQue(String securityQue) {
        this.securityQue = securityQue;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAboutTransport() {
        return aboutTransport;
    }

    public void setAboutTransport(String aboutTransport) {
        this.aboutTransport = aboutTransport;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", securityQue='" + securityQue + '\'' +
                ", securityAns='" + securityAns + '\'' +
                ", transportName='" + transportName + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", telephoneNo='" + telephoneNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", aboutTransport='" + aboutTransport + '\'' +
                '}';
    }
}
