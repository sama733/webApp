package logic;

import DAL.bean.RealCustomer;
import logic.exceptions.DateFormatException;
import logic.exceptions.FieldIsRequiredException;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RealCustomerLogic {


    public static void validateRealCustomer(String firstName, String lastName, String fatherName, String dateOfBirth, String nationalCode)
            throws FieldIsRequiredException, DateFormatException {
        if (firstName.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد نام را وارد کنید.");
        }
        if (lastName.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد نام خانوادگی را وارد کنید.");
        }
        if (fatherName.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد نام پدر را وارد کنید.");
        }
        if (dateOfBirth.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد تاریخ تولد را  بطور صحیح وارد کنید.");
        }
        if (nationalCode.isEmpty() || nationalCode.length() < 10) {
            throw new FieldIsRequiredException("لطفا کد ملی را بطور صحیح وارد نمایید");
        }
    }


    public static ArrayList<RealCustomer> retrieveRealCustomer(String realCustomerNumber, String firstName, String lastName, String nationalCode) {
        ArrayList<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        try {
            PreparedStatement preparedStatement = generatePreparedStatement(realCustomerNumber,firstName,lastName,nationalCode);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                RealCustomer realCustomer = new RealCustomer();
                realCustomer.setCustomerNumber(String.valueOf(results.getInt("customerNumber")));
                realCustomer.setName(results.getString("firstName"));
                realCustomer.setFamily(results.getString("lastName"));
                realCustomer.setNationalCode(results.getString("nationalCode"));
                realCustomers.add(realCustomer);
            }
        } catch (SQLException e) {
        }
        return realCustomers;
    }

    private static PreparedStatement generatePreparedStatement(String realCustomerNumber, String firstName, String lastName, String nationalCode) {
        PreparedStatement preparedStatement = null;
        StringBuilder sqlCommand = new StringBuilder("SELECT * From realcustomer WHERE ");
        int counter = 1;
        List<String> parameters = new ArrayList<String>();
        if(realCustomerNumber != "" && realCustomerNumber != null) {
            sqlCommand.append(" realcustomernumber=? AND");
            parameters.add(realCustomerNumber);
        }
        if(nationalCode != "") {
            sqlCommand.append(" nationalcode=? AND");
            parameters.add(nationalCode);
        }
        if(firstName != "") {
            sqlCommand.append(" name=? AND");
            parameters.add(firstName);
        }
        if(lastName != "") {
            sqlCommand.append(" family=? AND");
            parameters.add(lastName);
        }
        sqlCommand.append("true");

        try {
            preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement(sqlCommand.toString());
            for (String parameter : parameters){

                preparedStatement.setString(counter++,parameter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;

    }
}