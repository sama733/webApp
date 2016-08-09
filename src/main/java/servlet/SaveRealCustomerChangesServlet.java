package servlet;

import DAL.RealCustomerCRUD;
import logic.RealCustomerLogic;
import logic.exceptions.FieldIsRequiredException;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class SaveRealCustomerChangesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String nationalCode = request.getParameter("nationalCode");
        String outputHTML = "";
        PrintWriter out = null;

        try {
            RealCustomerLogic.validateRealCustomer(firstName.trim(), lastName.trim(), fatherName.trim(), dateOfBirth.trim(), nationalCode.trim());
            RealCustomerCRUD.updateRealCustomer(firstName.trim(), lastName.trim(), fatherName.trim(), dateOfBirth.trim(), nationalCode.trim(), id);
            outputHTML = HTMLGenerator.generateSuccess("اطلاعات مشتری با موفقیت ویرایش شد.");
        } catch (FieldIsRequiredException e) {
            outputHTML = HTMLGenerator.generateError("لطفا مقادیر را با دقت پر نمایید");
            e.printStackTrace();
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError("خطا در پایگاه داده");
            e.printStackTrace();
        } finally {
            response.setContentType("text/html; charset=UTF-8");
            out = response.getWriter();
            out.println(outputHTML);
        }

    }
}
