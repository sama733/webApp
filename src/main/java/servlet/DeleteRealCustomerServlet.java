package servlet;

import DAL.CustomerCRUD;
import DAL.RealCustomerCRUD;
import DAL.bean.RealCustomer;
import util.HTMLGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class DeleteRealCustomerServlet extends HttpServlet {

    public static void deleteById(Long id) throws SQLException {
        RealCustomer realCustomer = RealCustomerCRUD.retrieveRealCustomerById(id);
        CustomerCRUD.deleteRealCustomerById(realCustomer.getId());
        CustomerCRUD.deleteCustomerById(realCustomer.getId());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long realCustomerID = Long.valueOf(request.getParameter("id"));
        String outputHTML = "";
        try {
            deleteById(realCustomerID);
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError("خطا در حذف مشتری.");
            e.printStackTrace();
        }
        outputHTML = HTMLGenerator.generateRealCustomerDelete("مشتری با موفقیت حذف شد.");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);
    }
}
