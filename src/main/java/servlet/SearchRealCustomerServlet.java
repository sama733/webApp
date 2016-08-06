package servlet;

import DAL.bean.RealCustomer;
import logic.RealCustomerLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SearchRealCustomerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String realCustomerNumber = request.getParameter("customerNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nationalCode = request.getParameter("nationalCode");
        String outputHTML = "";

        ArrayList<RealCustomer> realCustomers = RealCustomerLogic.retrieveRealCustomer(realCustomerNumber, firstName, lastName, nationalCode);
    }
}
