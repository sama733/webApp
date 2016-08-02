package servlet;

import dataaccesslayer.bean.RealCustomer;
import logic.CustomerLogic;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DateFormatException;
import logic.exceptions.DuplicateInformationException;
import logic.exceptions.FieldIsRequiredException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateRealCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String nationalCode = request.getParameter("nationalCode");

        try {
            RealCustomer realCustomer = CustomerLogic.createCustomer(firstName, lastName, fatherName, dateOfBirth, nationalCode);

        } catch (FieldIsRequiredException e) {
            System.out.println("مقادیر اشتباه وارد شده است");
            e.printStackTrace();
        } catch (DateFormatException e) {
            System.out.println("تاریخ تولد را بصورت صحیح وارد نمایید");
            e.printStackTrace();
        } catch (AssignCustomerNumberException e) {
            System.out.println("عدم موفقیت در ساخت شماره مشتری");
            e.printStackTrace();
        } catch (DuplicateInformationException e) {
            System.out.println("مقدار وارد شده موجود می باشد");
            e.printStackTrace();
        }

    }
}
