package DAL;

import DAL.bean.LegalCustomer;
import DAL.bean.RealCustomer;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DataBaseConnectionException;
import logic.exceptions.DuplicateInformationException;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerCRUD {
    //for create realcustomer
    public static String createRealCustomer(RealCustomer realCustomer) throws AssignCustomerNumberException, DuplicateInformationException, DataBaseConnectionException {
        String generatedValues = generateRealCustomerNumber();
        realCustomer.setRealCustomerNumber(generatedValues);
        RealCustomerCRUD.createRealCustomer(realCustomer);
        return realCustomer.getRealCustomerNumber();
    }

    //for generate realcustomernumber
    private static String generateRealCustomerNumber() throws AssignCustomerNumberException {
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

    //for generate legalcustomernumber
    private static String generateLegalCustomerNumber() throws AssignCustomerNumberException, SQLException {
        String customerNumber = null;
        customerNumber = String.valueOf(System.currentTimeMillis());
        PreparedStatement preparedStatement =
                ConnectionUtil.getConnectionUtil().prepareStatement("insert into customer (customernumber) values(?);", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customerNumber);
        preparedStatement.executeUpdate();

        if (customerNumber.trim().equals("")) {
            throw new AssignCustomerNumberException("سیستم با مشکل مواجه شده است، مجددا تلاش نمایید");
        }
        return customerNumber;
    }

    // for delete method of real customer
    public static void deleteRealCustomerById(Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement("DELETE From realcustomer WHERE id=?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();

    }

    //for delete method of real customer and legal customer
    public static void deleteCustomerById(Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement("DELETE  from customer where id=?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }


    // for creat legal methods
    public static String createLegalCustomer(LegalCustomer legalCustomer) throws AssignCustomerNumberException, DuplicateInformationException, SQLException, DataBaseConnectionException {
        String generatedValues = generateLegalCustomerNumber();
        legalCustomer.setLegalCustomerNumber(generatedValues);
        LegalCustomerCRUD.creatLegalCustomer(legalCustomer);
        return legalCustomer.getLegalCustomerNumber();
    }

    //for delete legalcustomer by id
    public static void deleteLegalCustomerById(Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement("DELETE From legalcustomer WHERE id=?;");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public static Long getCustomerByCustomerNumber(long customerNumber){
        Long customerId = null ;
        try {
            PreparedStatement preparedStatement =
                    ConnectionUtil.getConnectionUtil().prepareStatement("SELECT * FROM customer WHERE customerNumber = ?;");
            preparedStatement.setLong(1, customerNumber);
            ResultSet result =  preparedStatement.executeQuery();
            while(result.next()){
                customerId = result.getLong("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;

    }
}
