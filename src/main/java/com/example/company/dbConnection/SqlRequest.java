package com.example.company.dbConnection;

public class SqlRequest {
    public static final String FIND_ALL_EMPLOYEE = "SELECT e.*, d.name as department FROM employee e LEFT JOIN department d ON e.department_id = d.id;";
    public static String ADD_EMPLOYEE = "INSERT into employee (firstname, lastname, birthdate, department_id, start_time, end_time, preference) values (?, ?, ?, ?, ?, ?, ?)";
    public static String CHANGE_SCHEDULE_EMPLOYEE = "UPDATE employee set start_time = ?, end_time = ?, preference = ? where id = ?";


    public static final String FIND_ALL_DEPARTMENTS = "SELECT id, name, start_time, end_time, floor from department group by id";
    public static String ADD_DEPARTMENT = "INSERT into department (name, start_time, end_time, floor) values (?, ?, ?, ?)";
    public static String CHANGE_SCHEDULE_DEPARTMENT = "UPDATE department set start_time = ?, end_time = ? where id = ?";


    public static final String GET_PREFERENCE_EMPLOYEES = "SELECT preference, e.start_time empstart, e.end_time empend, d.start_time depstart, d.end_time depend\n" +
            "from employee e\n" +
            "join department d on d.id = e.department_id\n" +
            "where e.start_time != d.start_time or e.end_time != d.end_time\n" +
            "group by preference, e.firstname, e.start_time, e.end_time, d.start_time, d.end_time";

}
