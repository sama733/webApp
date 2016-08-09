package servlet;

import DAL.LegalCustomerCRUD;
import DAL.bean.LegalCustomer;
import logic.LegalCustomerLogic;
import logic.exceptions.FieldIsRequiredException;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UpdateLegalCustomerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String outputHTML="";
        try {
            LegalCustomer legalCustomer = LegalCustomerCRUD.retrieveLegalCustomerById(id);
            outputHTML = HTMLGenerator.generateUpdateLegalCustomer(legalCustomer);
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError("خطا در اتصال");
        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateError("مقادیر را پر نمایید");
            e.printStackTrace();
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);

    }
}
