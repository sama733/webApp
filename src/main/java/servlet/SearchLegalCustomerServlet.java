package servlet;

import DAL.LegalCustomerCRUD;
import DAL.bean.LegalCustomer;
import logic.exceptions.FieldIsRequiredException;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchLegalCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("Name");
        String dateOfRegistration = request.getParameter("dateOfRegistration");
        String economicCode = request.getParameter("economicCode");
        String legalCustomerNumber = request.getParameter("customerNumber");
        String outputHTML = "";

        try {
            ArrayList<LegalCustomer> legalCustomers = LegalCustomerCRUD.retrieveLegalCustomer(name, dateOfRegistration, economicCode, legalCustomerNumber);
            if (legalCustomers.size() == 0) {
                outputHTML = HTMLGenerator.generateSuccess("مشتری با اطلاعات وارد شده موجود نیست");
            } else {
                outputHTML = HTMLGenerator.generateLegalCustomerSearch(legalCustomers);
            }
        }catch (SQLException e) {
            outputHTML= HTMLGenerator.generateError("خطا در اتصال");
            e.printStackTrace();
        } catch (FieldIsRequiredException e) {
            outputHTML= HTMLGenerator.generateError("مقادیر را با دقت پر نمایید");
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);
    }
}
