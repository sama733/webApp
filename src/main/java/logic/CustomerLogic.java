package logic;

import dataaccesslayer.CustomerCRUD;
import dataaccesslayer.bean.RealCustomer;
import logic.exceptions.*;

public class CustomerLogic {

    public static RealCustomer createCustomer(String firstName, String lastName, String fatherName, String dateOfBirth, String nationalCode)
            throws FieldIsRequiredException, DateFormatException, AssignCustomerNumberException, DuplicateInformationException {
        RealCustomerLogic.validate(firstName.trim(), lastName.trim(), fatherName.trim(), dateOfBirth.trim(), nationalCode.trim());
        RealCustomer realCustomer = new RealCustomer();
        realCustomer.setName(firstName);
        realCustomer.setFamily(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setBirthDate(dateOfBirth);
        realCustomer.setNationalCode(nationalCode);
        realCustomer.setCustomerNumber(CustomerCRUD.create(realCustomer));
        return realCustomer;
    }
}
