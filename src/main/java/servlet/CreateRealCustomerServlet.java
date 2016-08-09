package servlet;

import DAL.RealCustomerCRUD;
import DAL.bean.RealCustomer;
import logic.RealCustomerLogic;
import logic.exceptions.*;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateRealCustomerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String nationalCode = request.getParameter("nationalCode");
        String outputHTML = "";
        PrintWriter out = null;

        try {
            RealCustomerLogic.validateRealCustomer(firstName.trim(), lastName.trim(), fatherName.trim(), dateOfBirth.trim(), nationalCode.trim());
            RealCustomer realCustomer = RealCustomerCRUD.setValuesOfNewRealCustomer(firstName.trim(), lastName.trim(), fatherName.trim(), dateOfBirth.trim(), nationalCode.trim());
            outputHTML = HTMLGenerator.generateRealCustomer(realCustomer);

        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateRealCustomerError("لطفا مقادیر را با دقت پر نمایید");
        } catch (AssignCustomerNumberException e) {
            outputHTML = HTMLGenerator.generateRealCustomerError("عدم موفقیت در ساخت شماره مشتری");
        } catch (DuplicateInformationException e) {
            outputHTML = HTMLGenerator.generateRealCustomerError("مقدار وارد شده موجود می باشد");
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            outputHTML = HTMLGenerator.generateRealCustomerError("خطا در اتصال به بانک");
        } finally {
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
            out.println(outputHTML);
        }
    }
}
