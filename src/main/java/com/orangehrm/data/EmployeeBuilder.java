package com.orangehrm.data;

import java.util.UUID;

public class EmployeeBuilder {
    private String firstName = "AutoFirst";
    private String middleName = "AutoMid";
    private String lastName = "AutoLast" + UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();

    public EmployeeBuilder withFirstName(String firstName) { this.firstName = firstName; return this; }
    public EmployeeBuilder withLastName(String lastName) { this.lastName = lastName; return this; }
    public Employee build() { return new Employee(firstName, middleName, lastName); }
    public static EmployeeBuilder create() { return new EmployeeBuilder(); }
}
