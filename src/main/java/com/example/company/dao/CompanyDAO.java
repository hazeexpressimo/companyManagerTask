package com.example.company.dao;

import com.example.company.data.Company;
import com.example.company.data.DateUtil;
import com.example.company.dbConnection.ConnectionManager;
import com.example.company.dbConnection.SqlRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.*;

public class CompanyDAO {

    public Map<Boolean, List<Integer>> getCompanyPreference() {
        Map<Boolean, List<Integer>> result = new HashMap<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SqlRequest.GET_PREFERENCE_EMPLOYEES);
            while (rs.next()) {
                Boolean preference = rs.getBoolean("preference");
                LocalTime empStart = LocalTime.parse(rs.getString("empstart"));
                LocalTime empEnd = LocalTime.parse(rs.getString("empend"));
                LocalTime depStart = LocalTime.parse(rs.getString("depstart"));
                LocalTime depEnd = LocalTime.parse(rs.getString("depend"));
                int deltaHours = DateUtil.getDeltaHours(empStart, empEnd, depStart, depEnd);
                result.computeIfAbsent(preference, k -> new ArrayList<>()).add(deltaHours);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public double getEarningsCompany(Map<Boolean, List<Integer>> preferences) {
        double result;
        int sumPositiveHours = 0;
        int sumNegativeHours = 0;
        if (preferences.get(Boolean.TRUE) != null) {
            sumPositiveHours = preferences.get(Boolean.TRUE).stream().reduce(0, Integer::sum);
        }
        if (preferences.get(Boolean.FALSE) != null) {
            sumNegativeHours = preferences.get(Boolean.FALSE).stream().reduce(0, Integer::sum);
        }
        double resultPositive = sumPositiveHours * 0.2;
        double resultNegative = sumNegativeHours * -0.2;
        result = resultPositive + resultNegative;
        return result;
    }

    public List<Company> getCompanyStatistics() {
        List<Company> result = new ArrayList<>();
        int positiveEmp;
        int negativeEmp;
        Map<Boolean, List<Integer>> preferences = getCompanyPreference();
        if (preferences.get(Boolean.TRUE) == null) {
            positiveEmp = 0;
        } else {
            positiveEmp = preferences.get(Boolean.TRUE).size();
        }
        if (preferences.get(Boolean.FALSE) == null) {
            negativeEmp = 0;
        } else {
            negativeEmp = preferences.get(Boolean.FALSE).size();
        }
        double companyEarning = getEarningsCompany(preferences);
        Company company = new Company(companyEarning, positiveEmp, negativeEmp);
        result.add(company);
        return result;
    }
}