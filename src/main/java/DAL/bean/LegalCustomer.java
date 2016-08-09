package DAL.bean;

public class LegalCustomer extends Customer {
    private Long id;
    private String name;
    private String dateOfRegistration;
    private String economicCode;
    private String legalCustomerNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegalCustomerNumber() {
        return legalCustomerNumber;
    }

    public void setLegalCustomerNumber(String legalCustomerNumber) {
        super.setCustomerNumber(legalCustomerNumber);
        this.legalCustomerNumber = legalCustomerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
