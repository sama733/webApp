package servlet;


import DAL.CustomerCRUD;
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

public class DeleteLegalCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long legalCustomerID = Long.valueOf(request.getParameter("id"));
        String outputHTML = "";

        try {
            deleteLegalCustomerById(legalCustomerID);
        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateError("خطا در حذف مشتری");
            e.printStackTrace();
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError("خطا در حذف مشتری");
            e.printStackTrace();
        }
        outputHTML = HTMLGenerator.generateSuccess("مشتری مورد نظر حذف شد");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);
    }

    public static void deleteLegalCustomerById(Long id) throws FieldIsRequiredException, SQLException {
        LegalCustomer legalCustomer = LegalCustomerCRUD.retrieveLegalCustomerById(id);
        CustomerCRUD.deleteLegalCustomerById(legalCustomer.getId());
        CustomerCRUD.deleteCustomerById(legalCustomer.getId());
    }
}
