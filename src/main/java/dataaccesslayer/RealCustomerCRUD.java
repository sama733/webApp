package dataaccesslayer;

import dataaccesslayer.bean.RealCustomer;
import logic.exceptions.DataBaseonnectionException;
import util.StaticConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RealCustomerCRUD {

    public static void create(RealCustomer realCustomer) throws DataBaseonnectionException {
        try {
            PreparedStatement preparedStatement = StaticConnection.getStaticConnection()
                    .prepareStatement(
                            "INSERT INTO realcustomer( name , family ,fathername,birthdaye,nationalcode,realcustomernumber)" +
                                    " VALUES ( ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, realCustomer.getName());
            preparedStatement.setString(2, realCustomer.getFamily());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getBirthDate());
            preparedStatement.setString(5, realCustomer.getNationalCode());
            preparedStatement.setString(6, realCustomer.getCustomerNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseonnectionException(e.getMessage() + "خطا در ایجاد اتصال به پایگاه داده!");
        }
    }
}
