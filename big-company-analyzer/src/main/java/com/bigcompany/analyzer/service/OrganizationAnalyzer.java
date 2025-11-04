package com.bigcompany.analyzer.service;

import com.bigcompany.analyzer.model.Employee;
import com.bigcompany.analyzer.model.SalaryViolation;
import java.util.*;

/**
 * Analyzes organization data such as manager salaries and reporting depth.
 *
 * Assumptions:
 *  - Managers should earn 20%–50% more than the average of their direct subordinates.
 *  - If a manager earns below or above this range, it's marked as UNDERPAID or OVERPAID.
 *  - Reporting chain longer than a given depth (e.g., 4) is considered too long.
 */
public class OrganizationAnalyzer {

    private final Map<Integer, Employee> employeesById = new HashMap<>();
    private final List<Employee> allEmployees;
    private Employee ceo;

    public OrganizationAnalyzer(List<Employee> employees) {
        this.allEmployees = employees;
        for (Employee e : employees) {
            employeesById.put(e.getId(), e);
            if (e.getManagerId() == null) {
                ceo = e; // Assuming only one CEO (no manager)
            }
        }
    }

    /**
     * Finds all salary violations where managers earn too little or too much
     * compared to their team's average salary.
     */
    public List<SalaryViolation> analyzeSalaryViolations() {
        List<SalaryViolation> result = new ArrayList<>();

        // Correct, simple, and readable
        double minFactor = 1.2; // Managers should earn at least 20% more than their subordinates
        double maxFactor = 1.5; // Managers should not earn more than 50% above their subordinates

        for (Employee manager : allEmployees) {
            List<Employee> subs = getSubordinates(manager.getId());
            if (subs.isEmpty()) continue; // Skip non-managers

            double avgSubSalary = subs.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);

            double minAllowed = avgSubSalary * minFactor;
            double maxAllowed = avgSubSalary * maxFactor;

            // Sensible assumption:
            // If avgSubSalary = 0 (e.g., bad data), skip analysis to avoid divide-by-zero or nonsense results
            if (avgSubSalary <= 0) continue;

            if (manager.getSalary() < minAllowed) {
                result.add(new SalaryViolation("UNDERPAID", manager, minAllowed, minAllowed - manager.getSalary()));
            } else if (manager.getSalary() > maxAllowed) {
                result.add(new SalaryViolation("OVERPAID", manager, maxAllowed, manager.getSalary() - maxAllowed));
            }
        }

        return result;
    }

    /**
     * Finds employees with too many reporting levels above them.
     *
     * @param allowedDepth maximum allowed levels between employee and CEO
     * @return list of warning messages for employees exceeding allowed depth
     * 
     *  Assumption:
     *  A reporting chain longer than 'allowedDepth' (e.g., 4) is considered too long.
     * CEO has no manager, so their depth is 0.
     */
    public List<String> analyzeLongReportingLines(int allowedDepth) {
        List<String> result = new ArrayList<>();

        for (Employee e : allEmployees) {
            int depth = depthToCeo(e);
            
         // If the employee has more managers than allowed, report it clearly
            if (depth > allowedDepth) {
                result.add(String.format(
                    "%s has %d managers in their reporting chain, which is %d more than the allowed %d.",
                    e.getName(), depth, depth - allowedDepth, allowedDepth
                ));
            }
        }

        return result;
    }

    /**
     * Helper method to get direct subordinates of a given manager.
     */
    private List<Employee> getSubordinates(int managerId) {
        List<Employee> subs = new ArrayList<>();
        for (Employee e : allEmployees) {
            if (e.getManagerId() != null && e.getManagerId() == managerId) {
                subs.add(e);
            }
        }
        return subs;
    }

    /**
     * Calculates how many levels above a given employee the CEO is.
     */
    private int depthToCeo(Employee e) {
        int depth = 0;
        Integer managerId = e.getManagerId();

        // Assumption: no circular reporting chain in data
        while (managerId != null) {
            Employee manager = employeesById.get(managerId);
            if (manager == null) break;
            depth++;
            managerId = manager.getManagerId();
        }

        return depth;
    }
}
