package DAL.bean;

public class LegalCustomer extends Customer {
    private Long id;
    private String nameOfCompany;
    private String dateOfRegistration;
    private String economicCode;
    private String legalCustomerNumber;

//    public LegalCustomer(int id, String nameOfCompany, String dateOfRegistration, String economicCode, String legalCustomerNumber) {
//
//        this.id = id;
//        this.nameOfCompany = nameOfCompany;
//        this.dateOfRegistration = dateOfRegistration;
//        this.economicCode = economicCode;
//        this.legalCustomerNumber = legalCustomerNumber;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LegalCustomer{" +
                "nameOfCompany='" + nameOfCompany + '\'' +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                ", economicCode='" + economicCode + '\'' +
                ", legalCustomerNumber='" + legalCustomerNumber + '\'' +
                '}';
    }

    public String getLegalCustomerNumber() {
        return legalCustomerNumber;
    }

    public void setLegalCustomerNumber(String legalCustomerNumber) {
        this.legalCustomerNumber = legalCustomerNumber;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getEconomicCode() {
        return economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }
}
