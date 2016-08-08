package logic;


import logic.exceptions.FieldIsRequiredException;

public class LegalCustomerLogic {

    public static void validateLegalCustomer(String name, String dateOfRegistration, String economicCode) throws FieldIsRequiredException {

        if (name.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد نام پدر را وارد کنید.");
        }
        if (dateOfRegistration.isEmpty()) {
            throw new FieldIsRequiredException("لطفا فیلد تاریخ تولد را  بطور صحیح وارد کنید.");
        }
        if (economicCode.isEmpty() || economicCode.length() < 10) {
            throw new FieldIsRequiredException("لطفا شماره اقتصادی را بطور صحیح وارد نمایید");
        }
    }

    public static void validateEconomicCode(String nationalCode) throws FieldIsRequiredException {
        if (nationalCode.length() < 10) {
            throw new FieldIsRequiredException("لطفا شماره اقتصادی را به طور صحیح وارد نمایید");
        }
    }
}
