package DAL;


import DAL.bean.LegalCustomer;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DataBaseConnectionException;
import logic.exceptions.DuplicateInformationException;
import util.ConnectionUtil;

import java.sql.PreparedStatement;

public class LegalCustomerCRUD {


    public static LegalCustomer setValuesOfNewLegalCustomer(String name, String dateOfRegistration, String economicCode) throws AssignCustomerNumberException, DuplicateInformationException , DataBaseConnectionException{
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setName(name);
        legalCustomer.setDateOfRegistration(dateOfRegistration);
        legalCustomer.setEconomicCode(economicCode);
        String customerNumber = CustomerCRUD.createLegalCustomer(legalCustomer);
        legalCustomer.setLegalCustomerNumber(customerNumber);
        return legalCustomer;
    }

    public static void creatLegalCustomer(LegalCustomer legalCustomer) throws DuplicateInformationException {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                    .prepareStatement(
                            "INSERT INTO legalcustomer( name , dateOfRegistration ,economicCode)" +
                                    " VALUES ( ?, ?, ?);");

            preparedStatement.setString(1, legalCustomer.getName());
            preparedStatement.setString(2, legalCustomer.getDateOfRegistration());
            preparedStatement.setString(3, legalCustomer.getEconomicCode());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DuplicateInformationException(e.getMessage() + "مقدار وارد شده موجود می باشد");
        }
    }
}

