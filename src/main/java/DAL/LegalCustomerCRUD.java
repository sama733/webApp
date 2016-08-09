package DAL;


import DAL.bean.LegalCustomer;
import logic.LegalCustomerLogic;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DataBaseConnectionException;
import logic.exceptions.DuplicateInformationException;
import logic.exceptions.FieldIsRequiredException;
import util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LegalCustomerCRUD {


    public static LegalCustomer setValuesOfNewLegalCustomer(String name, String dateOfRegistration, String economicCode) throws AssignCustomerNumberException, DuplicateInformationException, DataBaseConnectionException, SQLException {
        LegalCustomer legalCustomer = new LegalCustomer();
        legalCustomer.setName(name);
        legalCustomer.setDateOfRegistration(dateOfRegistration);
        legalCustomer.setEconomicCode(economicCode);
        String legalCustomerNumber = CustomerCRUD.createLegalCustomer(legalCustomer);
        legalCustomer.setLegalCustomerNumber(legalCustomerNumber);
        return legalCustomer;
    }

    public static void creatLegalCustomer(LegalCustomer legalCustomer) throws DataBaseConnectionException {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                    .prepareStatement(
                            "INSERT INTO legalcustomer( name , dateOfRegistration ,economicCode, legalcustomernumber, id)" +
                                    " VALUES ( ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, legalCustomer.getName());
            preparedStatement.setString(2, legalCustomer.getDateOfRegistration());
            preparedStatement.setString(3, legalCustomer.getEconomicCode());
            preparedStatement.setString(4, legalCustomer.getLegalCustomerNumber());
            preparedStatement.setString(5, String.valueOf(CustomerCRUD.getCustomerByCustomerNumber(Long.parseLong(legalCustomer.getLegalCustomerNumber()))));
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DataBaseConnectionException(e.getMessage() + "خطا در ورود اطلاعات");
        }
    }

    public static ArrayList<LegalCustomer> retrieveLegalCustomer(String name, String dateOfRegistration, String economicCode, String legalCustomerNumber) throws SQLException, FieldIsRequiredException {
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();
        try {
            PreparedStatement preparedStatement = generatePreparedStatement(name, dateOfRegistration, economicCode, legalCustomerNumber);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                LegalCustomer legalCustomer = new LegalCustomer();
                legalCustomer.setId(results.getLong("id"));
                legalCustomer.setName(results.getString("Name"));
                legalCustomer.setEconomicCode(results.getString("economiccode"));
                legalCustomer.setDateOfRegistration(results.getString("dateofregistration"));
                legalCustomer.setLegalCustomerNumber(results.getString("legalcustomernumber"));
                legalCustomers.add(legalCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return legalCustomers;
    }

    private static PreparedStatement generatePreparedStatement(String name, String dateOfRegistration, String economicCode, String legalCustomerNumber) throws FieldIsRequiredException {
        PreparedStatement preparedStatement = null;
        StringBuilder sqlCommand = new StringBuilder(" SELECT * From legalcustomer WHERE ");
        int counter = 1;
        List<String> parameters = new ArrayList<String>();
        if (!legalCustomerNumber.trim().equals("") && legalCustomerNumber != null) {
            sqlCommand.append(" legalcustomernumber = ? and ");
            parameters.add(legalCustomerNumber);
        }
        if (!economicCode.trim().equals("") && economicCode != null) {
            sqlCommand.append(" economiccode = ? and ");
            LegalCustomerLogic.validateEconomicCode(economicCode);
            parameters.add(economicCode);
        }
        if (!dateOfRegistration.trim().equals("") && dateOfRegistration != null) {
            sqlCommand.append(" dateofregistration = ? and ");
            parameters.add(dateOfRegistration);
        }
        if (!name.trim().equals("") && name != null) {
            sqlCommand.append(" Name = ? and ");
            parameters.add(name);
        }
        sqlCommand.append("true");

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

    public static LegalCustomer retrieveLegalCustomerById(Long id) throws SQLException, FieldIsRequiredException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement("SELECT * FROM legalcustomer WHERE id=?;");
        preparedStatement.setLong(1, id);
        ResultSet results = preparedStatement.executeQuery();
        LegalCustomer legalCustomer = new LegalCustomer();
        while (results.next()) {
            legalCustomer.setId(results.getLong("id"));
            legalCustomer.setName(results.getString("Name"));
            legalCustomer.setEconomicCode(results.getString("economiccode"));
            legalCustomer.setDateOfRegistration(results.getString("dateofregistration"));
            legalCustomer.setLegalCustomerNumber(results.getString("legalcustomernumber"));
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return legalCustomer;
    }


    public static void updateLegalCustomer(String name, String dateOfRegistration, String economicCode, Long id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                .prepareStatement("UPDATE legalcustomer SET name = ? , economiccode =  ? ,  dateofregistration = ?  WHERE id=?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, economicCode);
        preparedStatement.setString(3, dateOfRegistration);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
    }
}


