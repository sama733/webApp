package DAL;

import DAL.bean.RealCustomer;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DuplicateInformationException;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerCRUD {

    public static String createRealCustomer(RealCustomer realCustomer) throws AssignCustomerNumberException, DuplicateInformationException {
        String generatedValues = generateCustomerNumber();
        realCustomer.setRealCustomerNumber(generatedValues);
        RealCustomerCRUD.createRealCustomer(realCustomer);
        return realCustomer.getCustomerNumber();
    }

    private static String generateCustomerNumber() throws AssignCustomerNumberException {
        String customerNumber = null;
        try {
            customerNumber = String.valueOf(System.currentTimeMillis());
            PreparedStatement preparedStatement =
                    ConnectionUtil.getConnectionUtil().prepareStatement("insert into customer (customernumber) values(?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customerNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customerNumber.equals("")) {
            throw new AssignCustomerNumberException("سیستم با مشکل مواجه شده است، مجددا تلاش نمایید");
        }
        return customerNumber;
    }

}
