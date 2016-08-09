package DAL;


import DAL.bean.LegalCustomer;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DataBaseConnectionException;
import logic.exceptions.DuplicateInformationException;
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

    public static void creatLegalCustomer(LegalCustomer legalCustomer) throws DuplicateInformationException {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnectionUtil()
                    .prepareStatement(
                            "INSERT INTO legalcustomer( name , dateOfRegistration ,economicCode, legalcustomernumber)" +
                                    " VALUES ( ?, ?, ?,?);");

            preparedStatement.setString(1, legalCustomer.getName());
            preparedStatement.setString(2, legalCustomer.getDateOfRegistration());
            preparedStatement.setString(3, legalCustomer.getEconomicCode());
            preparedStatement.setString(4, legalCustomer.getLegalCustomerNumber());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new DuplicateInformationException(e.getMessage() + "مقدار وارد شده موجود می باشد");
        }
    }

    public static ArrayList<LegalCustomer> retrieveCustomer(String name, String dateOfRegistration, String economicCode, String legalCustomerNumber) throws SQLException {
        PreparedStatement preparedStatement = generatePreparedStatement(name, dateOfRegistration, economicCode, legalCustomerNumber);
        ResultSet results = preparedStatement.executeQuery();
        ArrayList<LegalCustomer> legalCustomers = new ArrayList<LegalCustomer>();

        while (results.next()) {
            LegalCustomer legalCustomer = new LegalCustomer();
            legalCustomer.setId(results.getLong("id"));
            legalCustomer.setName(results.getString("name"));
            legalCustomer.setEconomicCode(results.getString("economiccode"));
            legalCustomer.setDateOfRegistration(results.getNString("dateofregistration"));
            legalCustomer.setLegalCustomerNumber(results.getString("legalcustomernumber"));
            legalCustomers.add(legalCustomer);
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        return legalCustomers;
    }

    private static PreparedStatement generatePreparedStatement(String name, String dateOfRegistration, String economicCode, String legalCustomerNumber) throws SQLException {
        PreparedStatement preparedStatement = null;
        StringBuilder sqlCommand = new StringBuilder(" SELECT * From legalcustomer WHERE ");
        int counter = 1;
        List<String> parameters = new ArrayList<String>();
        if (!legalCustomerNumber.trim().equals("") && legalCustomerNumber != null) {
            sqlCommand.append(" legalcustomernumber = ? and");
            parameters.add(legalCustomerNumber);
        }
        if (!economicCode.trim().equals("") && economicCode != null) {
            sqlCommand.append(" economiccode = ? and");
            parameters.add(economicCode);
        }
        if (!dateOfRegistration.trim().equals("") && dateOfRegistration != null) {
            sqlCommand.append(" dateofregistration = ? and");
            parameters.add(dateOfRegistration);
        }
        if (!name.trim().equals("") && name != null) {
            sqlCommand.append(" name = ? and");
            parameters.add(name);
        }
        sqlCommand.append("true");
        preparedStatement = ConnectionUtil.getConnectionUtil().prepareStatement(sqlCommand.toString());
        for (String parameter : parameters) {
            preparedStatement.setString(counter++, parameter);
        }
        return preparedStatement;
    }
}


