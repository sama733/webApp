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


            ArrayList<RealCustomer> realCustomers = RealCustomerCRUD.retrieveRealCustomer(realCustomerNumber, firstName, lastName, nationalCode);
            if (realCustomers.size() == 0) {
                outputHTML = HTMLGenerator.resultOfRealCustomer("مشتری با اطلاعات وارد شده وجود ندارد.");
            } else {
                outputHTML = HTMLGenerator.generateRealCustomerResults(realCustomers);
            }
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(outputHTML);

    }
}

