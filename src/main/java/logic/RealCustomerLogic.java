package logic;
import logic.exceptions.*;
public class RealCustomerLogic {

    public static void validate(String firstName, String lastName, String fatherName, String dateOfBirth, String nationalCode)
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
        if (nationalCode.isEmpty() && nationalCode.length()<11) {
            throw new FieldIsRequiredException("لطفا فیلد کد ملی را بطور صحیح وارد کنید.");
        }
    }

}
