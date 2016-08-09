package servlet;

import DAL.LegalCustomerCRUD;
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

public class SaveLegalCustomerChangesServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("Name");
        String dateOfRegistration = request.getParameter("dateOfRegistration");
        String economicCode = request.getParameter("economicCode");
        String outputHTML = "";
        PrintWriter out = null;

        try {
            LegalCustomerLogic.validateLegalCustomer(name.trim(),dateOfRegistration.trim(),economicCode.trim());
            LegalCustomerCRUD.updateLegalCustomer(name.trim(),dateOfRegistration.trim(),economicCode.trim() ,id);
            outputHTML = HTMLGenerator.generateSuccess("اطلاعات مشتری با موفقیت ویرایش شد.");
        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateError("لطفا مقادیر را با دقت پر نمایید");
            e.printStackTrace();
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError("خطا در پایگاه داده");
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=UTF-8");
        out = response.getWriter();
        out.println(outputHTML);
    }
}
