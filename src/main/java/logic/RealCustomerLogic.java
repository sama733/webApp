package logic;

import DAL.CustomerCRUD;
import DAL.RealCustomerCRUD;
import DAL.bean.RealCustomer;
import logic.exceptions.DateFormatException;
import logic.exceptions.FieldIsRequiredException;

import java.sql.SQLException;

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

    public static void validateNationalCode(String nationalCode) throws FieldIsRequiredException {
        if (nationalCode.length() < 10) {
            throw new FieldIsRequiredException("لطفا کد ملی را به طور صحیح وارد نمایید");
        }
    }


}