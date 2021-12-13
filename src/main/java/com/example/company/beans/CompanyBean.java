package com.example.company.beans;

import com.example.company.data.Company;

import java.util.List;

public class CompanyBean {
    private List<Company> companyList;

    public CompanyBean(List<Company> companyList) {
        this.companyList = companyList;
    }

    public CompanyBean() {
    }

    public int getSize() {
        return companyList.size();
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }
}
