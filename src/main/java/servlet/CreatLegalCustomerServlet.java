package servlet;

import DAL.LegalCustomerCRUD;
import DAL.bean.LegalCustomer;
import logic.LegalCustomerLogic;
import logic.exceptions.AssignCustomerNumberException;
import logic.exceptions.DataBaseConnectionException;
import logic.exceptions.DuplicateInformationException;
import logic.exceptions.FieldIsRequiredException;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreatLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String dateOfRegistration = request.getParameter("dateOfRegistration");
        String economicCode = request.getParameter("economicCode");
        String outputHTML = "";
        PrintWriter out = null;

        try {
            System.out.println("1");
            LegalCustomerLogic.validateLegalCustomer(name.trim(), dateOfRegistration.trim(),economicCode.trim());
            System.out.println("2");
            LegalCustomer legalCustomer = LegalCustomerCRUD.setValuesOfNewLegalCustomer(name.trim(), dateOfRegistration.trim(),economicCode.trim());
            System.out.println("3");
            outputHTML = HTMLGenerator.generateLegalCustomer(legalCustomer);
            System.out.println("4");

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
