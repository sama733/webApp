package DAL;

import DAL.bean.RealCustomer;
import logic.exceptions.*;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RealCustomerCRUD {


    public static RealCustomer setValuesOfNewRealCustomer(String firstName, String lastName, String fatherName, String dateOfBirth, String nationalCode)
            throws FieldIsRequiredException, DateFormatException, AssignCustomerNumberException, DuplicateInformationException, DataBaseConnectionException {
        RealCustomer realCustomer = new RealCustomer();

        realCustomer.setName(firstName);
        realCustomer.setFamily(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setBirthDate(dateOfBirth);
        realCustomer.setNationalCode(nationalCode);
        String customerNumber = CustomerCRUD.createRealCustomer(realCustomer);
        realCustomer.setCustomerNumber(customerNumber);
        return realCustomer;
    }

    public static void createRealCustomer(RealCustomer realCustomer) throws DataBaseConnectionException {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                    .prepareStatement(
                            "INSERT INTO realcustomer( name , family ,fathername,dateofbirth,nationalcode,realcustomernumber)" +
                                    " VALUES ( ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, realCustomer.getName());
            preparedStatement.setString(2, realCustomer.getFamily());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getBirthDate());
            preparedStatement.setString(5, realCustomer.getNationalCode());
            preparedStatement.setString(6, realCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataBaseConnectionException(e.getMessage() + "اتصال به پایگاه داده ممکن نیست!");
        }
    }
}
