package com.ghostCoder.invoicePro.database;

public class InitTables {
    AppDB appDB;

    public InitTables() {
        appDB = new AppDB();
    }

    //create tables
    public void createTables(){
        createConsignorTable();
        createMaterialTypeTable();
        createVehicleTypeTable();
        createAdditionalChargeListTable();
        createRateTypeTable();
        createUserTable();
        createLrRecordsTable();
        createInvoiceRecordsTable();
        createAdditionalChargesRecordsTable();
    }

    //consignor table
    private void createConsignorTable(){
        String query = "CREATE TABLE consignors (\n" +
                "    cname     VARCHAR (60) NOT NULL\n" +
                "                           PRIMARY KEY,\n" +
                "    addressL1 VARCHAR (40) NOT NULL,\n" +
                "    addressL2 VARCHAR (35) NOT NULL,\n" +
                "    city      VARCHAR (20) NOT NULL,\n" +
                "    gstNo     VARCHAR (15),\n" +
                "    emailId   VARCHAR (60) NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //material type table
    private void createMaterialTypeTable(){
        String query = "CREATE TABLE materialType (\n" +
                "    type VARCHAR (20) NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //vehicle type/weight table
    private void createVehicleTypeTable(){
        String query= "CREATE TABLE vehicleWeight (\n" +
                "    weight VARCHAR (20) NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //additional charge list table
    private void createAdditionalChargeListTable(){
        String query = "CREATE TABLE additionalChargeList (\n" +
                "    charges VARCHAR (60) NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //rate type table
    private void createRateTypeTable(){
        String query = "CREATE TABLE rateTypeList (\n" +
                "    type VARCHAR (20) NOT NULL\n" +
                "        UNIQUE\n" +
                ");\n";

        appDB.createTable(query);
    }

    //user data table
    private void createUserTable(){
        String query = "CREATE TABLE user (\n" +
                "    username       VARCHAR (25) NOT NULL,\n" +
                "    password       VARCHAR (40) NOT NULL,\n" +
                "    salt           VARCHAR (40) NOT NULL,\n" +
                "    securityQue    VARCHAR (60) NOT NULL,\n" +
                "    securityAns    VARCHAR (40) NOT NULL,\n" +
                "    transportName  VARCHAR (50) NOT NULL,\n" +
                "    addressL1      VARCHAR (30) NOT NULL,\n" +
                "    addressL2      VARCHAR (30) NOT NULL,\n" +
                "    city           VARCHAR (15) NOT NULL,\n" +
                "    mobileNo       VARCHAR (10) NOT NULL,\n" +
                "    telephoneNo    VARCHAR (10) NOT NULL,\n" +
                "    emailId        VARCHAR (50) NOT NULL,\n" +
                "    aboutTransport VARCHAR (50) NOT NULL,\n" +
                "    gstNo VARCHAR (15) NOT NULL,\n" +
                "    pinCode VARCHAR (7) NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //lr records table
    private void createLrRecordsTable(){
        String query = "CREATE TABLE lrRecords (\n" +
                "    consignor       VARCHAR (60) NOT NULL,\n" +
                "    date            DATE         NOT NULL,\n" +
                "    lrNo            NUMERIC      PRIMARY KEY\n" +
                "                                 NOT NULL\n" +
                "                                 UNIQUE,\n" +
                "    destinationFrom VARCHAR (20) NOT NULL,\n" +
                "    destinationTo   VARCHAR (20) NOT NULL,\n" +
                "    vehicleNo       VARCHAR (11) NOT NULL,\n" +
                "    weight          VARCHAR (10),\n" +
                "    packageQuantity NUMERIC      NOT NULL,\n" +
                "    packageType     VARCHAR (20) NOT NULL,\n" +
                "    invoiceNo       VARCHAR      NOT NULL\n" +
                ");";

        appDB.createTable(query);
    }

    //invoice records table
    private void createInvoiceRecordsTable(){
        String query = "CREATE TABLE invoiceRecords (\n" +
                "    billNo       VARCHAR NOT NULL,\n" +
                "    date            DATE         NOT NULL,\n" +
                "    lrNo                 REFERENCES LrRecords (lrNo) \n" +
                "                         NOT NULL,\n" +
                "    amount       NUMERIC,\n" +
                "    rate         VARCHAR,\n" +
                "    paid         BOOLEAN,\n" +
                "    paymentDate  DATE,\n" +
                "    chequeNo     VARCHAR,\n" +
                "    chequeAmount NUMERIC,\n" +
                "    multiLr      BOOLEAN\n" +
                ");";

        appDB.createTable(query);
    }

    //additional charges records table
    private void createAdditionalChargesRecordsTable(){
        String query = "CREATE TABLE additionalChargesRecords (\n" +
                "    billNo         VARCHAR NOT NULL,\n" +
                "    chargeName     VARCHAR NOT NULL,\n" +
                "    additionalInfo VARCHAR NOT NULL,\n" +
                "    amount         NUMERIC NOT NULL,\n" +
                "    rate           VARCHAR NOT NULL\n" +
                ");";
        appDB.createTable(query);
    }

}
