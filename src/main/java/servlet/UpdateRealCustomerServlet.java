package servlet;

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


public class UpdateRealCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long realCustomerID = Long.valueOf(request.getParameter("id"));
        String outputHTML = "";

        try {
            RealCustomer realCustomer = RealCustomerCRUD.retrieveRealCustomerById(realCustomerID);
            outputHTML = HTMLGenerator.generateUpdateRealCustomer(realCustomer);
        } catch (SQLException e) {
            outputHTML = HTMLGenerator.generateError(e.getMessage());
            e.printStackTrace();
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(outputHTML);
    }
}
