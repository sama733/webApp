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
import java.sql.SQLException;

public class CreatLegalCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String dateOfRegistration = request.getParameter("dateOfRegistration");
        String economicCode = request.getParameter("economicCode");
        String outputHTML = "";
        PrintWriter out = null;

        try {
            LegalCustomerLogic.validateLegalCustomer(name.trim(), dateOfRegistration.trim(), economicCode.trim());
            LegalCustomer legalCustomer = LegalCustomerCRUD.setValuesOfNewLegalCustomer(name.trim(), dateOfRegistration.trim(), economicCode.trim());
            outputHTML = HTMLGenerator.generateLegalCustomer(legalCustomer);

        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateError("لطفا مقادیر را با دقت پر نمایید");
        } catch (AssignCustomerNumberException e) {
            outputHTML = HTMLGenerator.generateError("عدم موفقیت در ساخت شماره مشتری");
        } catch (DuplicateInformationException e) {
            outputHTML = HTMLGenerator.generateError("مقدار وارد شده موجود می باشد");
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            outputHTML = HTMLGenerator.generateError("خطا در اتصال به بانک");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
            out.println(outputHTML);
        }
    }
}
