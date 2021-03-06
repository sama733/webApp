package DAL;

import DAL.bean.RealCustomer;
import logic.RealCustomerLogic;
import logic.exceptions.*;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RealCustomerCRUD {
    //for generate new realcustomer
    public static RealCustomer setValuesOfNewRealCustomer(String firstName, String lastName, String fatherName, String dateOfBirth, String nationalCode)
            throws FieldIsRequiredException, AssignCustomerNumberException, DuplicateInformationException, DataBaseConnectionException {
        RealCustomer realCustomer = new RealCustomer();

        realCustomer.setName(firstName);
        realCustomer.setFamily(lastName);
        realCustomer.setFatherName(fatherName);
        realCustomer.setBirthDate(dateOfBirth);
        realCustomer.setNationalCode(nationalCode);
        String customerNumber = CustomerCRUD.createRealCustomer(realCustomer);
        realCustomer.setRealCustomerNumber(customerNumber);
        return realCustomer;
    }

    //for create realcustomer
    public static void createRealCustomer(RealCustomer realCustomer) throws DataBaseConnectionException
    {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                    .prepareStatement(
                            "INSERT INTO realcustomer( name , family ,fathername,dateofbirth,nationalcode,realcustomernumber, id)" +
                                    " VALUES ( ?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, realCustomer.getName());
            preparedStatement.setString(2, realCustomer.getFamily());
            preparedStatement.setString(3, realCustomer.getFatherName());
            preparedStatement.setString(4, realCustomer.getBirthDate());
            preparedStatement.setString(5, realCustomer.getNationalCode());
            preparedStatement.setString(6, realCustomer.getRealCustomerNumber());
            preparedStatement.setString(7, String.valueOf(CustomerCRUD.getCustomerByCustomerNumber(Long.parseLong(realCustomer.getRealCustomerNumber()))));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DataBaseConnectionException(e.getMessage() + "خطا در ورود اطلاعات");
        }
    }

    //for search method
    public static ArrayList<RealCustomer> retrieveRealCustomer(String realCustomerNumber, String firstName, String lastName, String nationalCode) {
        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        try {
            PreparedStatement preparedStatement = generatePreparedStatement(realCustomerNumber, firstName, lastName, nationalCode);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                RealCustomer realCustomer = new RealCustomer();
                realCustomer.setId(results.getLong("id"));
                realCustomer.setName(results.getString("name"));
                realCustomer.setFamily(results.getString("family"));
                realCustomer.setFatherName(results.getString("fatherName"));
                realCustomer.setBirthDate(results.getString("dateOfBirth"));
                realCustomer.setNationalCode(results.getString("nationalCode"));
                realCustomer.setRealCustomerNumber(results.getString("realcustomernumber"));
                realCustomers.add(realCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FieldIsRequiredException e) {
            e.printStackTrace();
        }
        return realCustomers;
    }

    //for create method
    private static PreparedStatement generatePreparedStatement(String realCustomerNumber, String firstName, String lastName, String nationalCode)
            throws FieldIsRequiredException {
        PreparedStatement preparedStatement = null;
        StringBuilder sqlCommand = new StringBuilder(" SELECT * From realcustomer WHERE ");
        int counter = 1;
        List<String> parameters = new ArrayList<String>();
        if (!realCustomerNumber.trim().equals("") && realCustomerNumber != null) {
            sqlCommand.append(" realcustomernumber = ? AND ");
            parameters.add(realCustomerNumber);
        }
        if (!firstName.trim().equals("") && firstName != null) {
            sqlCommand.append(" name = ? AND ");
            parameters.add(firstName);
        }
        if (!nationalCode.trim().equals("") && nationalCode != null) {
            sqlCommand.append(" nationalcode = ? AND");
            RealCustomerLogic.validateNationalCode(nationalCode);
            parameters.add(nationalCode);
        }

        if (!lastName.trim().equals("") && lastName != null) {
            sqlCommand.append(" family = ? AND ");
            parameters.add(lastName);
        }
        sqlCommand.append(" true ");

        try {

            preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement(sqlCommand.toString());
            for (String parameter : parameters) {
                preparedStatement.setString(counter++, parameter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    //for delete method & update method
    public static RealCustomer retrieveRealCustomerById(Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement("SELECT * From realcustomer WHERE id=?;");
        preparedStatement.setLong(1, id);
        ResultSet results = preparedStatement.executeQuery();
        RealCustomer realCustomer = new RealCustomer();
        if (results.next()) {
            realCustomer.setId(results.getLong("id"));
            realCustomer.setRealCustomerNumber(results.getString("realcustomernumber"));
            realCustomer.setName(results.getString("name"));
            realCustomer.setFamily(results.getString("family"));
            realCustomer.setFatherName(results.getString("fathername"));
            realCustomer.setNationalCode(results.getString("nationalcode"));
            realCustomer.setBirthDate(results.getString("dateofbirth"));
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return realCustomer;

    }

    public static void updateRealCustomer(String name, String family, String fatherName, String birthDay, String nationalCode, Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement(("UPDATE realcustomer SET name = ? , family =  ? ,  fathername = ?  ,  nationalcode = ?  ,  dateofbirth = ?  WHERE id=?"));
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,family);
        preparedStatement.setString(3,fatherName);
        preparedStatement.setString(4,birthDay);
        preparedStatement.setString(5,nationalCode);
        preparedStatement.setLong(6,id);
        preparedStatement.executeUpdate();
    }
}
