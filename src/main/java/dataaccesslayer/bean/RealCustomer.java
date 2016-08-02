package dataaccesslayer.bean;

public class RealCustomer extends Customer {
    private Long id;
    private String name;
    private String family;
    private String fatherName;
    private String birthDate;
    private String nationalCode;
    private String realCustomerNumber;

//    public RealCustomer(int id, String name, String family, String fatherName, String birthDate, String nationalCode, String realCustomerNumber) {
//
//        this.id = id;
//        this.name = name;
//        this.family = family;
//        this.fatherName = fatherName;
//        this.birthDate = birthDate;
//        this.nationalCode = nationalCode;
//        this.realCustomerNumber = realCustomerNumber;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RealCustomer{" +
                "name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", realCustomerNumber='" + realCustomerNumber + '\'' +
                '}';
    }

    public String getRealCustomerNumber() {
        return realCustomerNumber;
    }

    public void setRealCustomerNumber(String realCustomerNumber) {
        this.realCustomerNumber = realCustomerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
