package com.example.company.departmentServlet;
import com.example.company.beans.DepartmentBean;
import com.example.company.beans.EmployeeBean;
import com.example.company.dao.DepartmentDAO;
import com.example.company.dao.EmployeeDAO;
import com.example.company.data.CompanyException;
import com.example.company.data.Department;
import com.example.company.data.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "departmentServlet", value = {"/addDepartment", "/showDepartment", "/changeScheduleDepartment"})
public class DepartmentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getRequestURI().contains("showDepartment")) {
            List<Department> departments = null;
            try {
                departments = new DepartmentDAO().findAll();
            } catch (CompanyException e) {
                e.printStackTrace();
            }
            request.setAttribute("depbean", new DepartmentBean(departments));
            request.getRequestDispatcher("/department.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getRequestURI().contains("addDepartment")) {
                addDepartment(request, response);
        }
        if (request.getRequestURI().contains("changeScheduleDepartment")) {
            changeScheduleDepartment(request, response);
        }
    }

    private void addDepartment(HttpServletRequest request, HttpServletResponse response) {
        int id = 0;
        String name = request.getParameter("nameDepReq");
        LocalTime startTimeReq = LocalTime.parse(request.getParameter("startTimeReq"));
        LocalTime endTimeReq = LocalTime.parse(request.getParameter("endTimeReq"));
        int floor = Integer.parseInt(request.getParameter("floorDepReq"));
        try {
            Department department = new Department(id, name, startTimeReq, endTimeReq, floor);
            DepartmentDAO departmentDAO = new DepartmentDAO();
            departmentDAO.addDepartment(department);
            response.sendRedirect(request.getContextPath() + "/mainPage");
        } catch (SQLException | IOException | ParseException | CompanyException e) {
            e.printStackTrace();
        }
    }

    private void changeScheduleDepartment(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("chooseDepNameInChangeMenu"));
        LocalTime newStartTime = LocalTime.parse(request.getParameter("changeStartTimeDepReq"));
        LocalTime newEndTime = LocalTime.parse(request.getParameter("changeEndTimeDepReq"));
        try {
            Department department = new Department(id, newStartTime, newEndTime);
            DepartmentDAO departmentDAO = new DepartmentDAO();
            departmentDAO.changeScheduleDepartment(department);
            response.sendRedirect(request.getContextPath() + "/mainPage");
        } catch (SQLException | IOException | CompanyException exception) {
            exception.printStackTrace();
        }
    }

    public void destroy() {
    }
}