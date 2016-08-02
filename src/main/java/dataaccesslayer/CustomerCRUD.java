package dataaccesslayer;

import dataaccesslayer.bean.RealCustomer;
import logic.exceptions.*;
import util.StaticConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerCRUD {

    public static String create(RealCustomer realCustomer) throws AssignCustomerNumberException, DuplicateInformationException {
        realCustomer.setCustomerNumber(generateCustomerNumber());
        try {
            RealCustomerCRUD.create(realCustomer);
        } catch (DataBaseonnectionException e) {
            e.printStackTrace();
        }
        return realCustomer.getCustomerNumber();
    }

//    private static void deleteByCustomerNumber(String customerNumber) {
//    }



    private static String generateCustomerNumber() throws AssignCustomerNumberException {
        String customerNumber = null;
        try {
            PreparedStatement preparedStatement =
                    StaticConnection.getStaticConnection().prepareStatement("insert into customer () values()", Statement.RETURN_GENERATED_KEYS  );
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                customerNumber = String.valueOf(resultSet.getInt(1));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customerNumber == "") {
            throw new AssignCustomerNumberException("سیستم با مشکل مواجه شده است، مجددا تلاش نمایید");
        }
        return customerNumber;
    }
}
