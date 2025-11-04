package com.bigcompany.analyzer.util;

import com.bigcompany.analyzer.model.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {
    public static List<Employee> readEmployeesFromResource(String resourcePath) throws IOException {
        InputStream in = CsvReader.class.getResourceAsStream(resourcePath);
        if (in == null) throw new IOException("Resource not found: " + resourcePath);
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 5) continue;
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                double salary = Double.parseDouble(parts[3]);
                Integer managerId = parts[4].trim().isEmpty() ? null : Integer.parseInt(parts[4]);
                employees.add(new Employee(id, firstName, lastName, salary, managerId));
            }
        }
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.getId(), e);
        for (Employee e : employees) {
            if (e.getManagerId() != null && map.containsKey(e.getManagerId())) {
                map.get(e.getManagerId()).addSubordinate(e);
            }
        }
        return employees;
    }
}