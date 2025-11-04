package com.bigcompany.analyzer;

import com.bigcompany.analyzer.model.Employee;
import com.bigcompany.analyzer.model.SalaryViolation;
import com.bigcompany.analyzer.util.CsvReader;
import com.bigcompany.analyzer.service.OrganizationAnalyzer;

import java.io.IOException;
import java.util.List;

/**
 * Main class for the Big Company Analyzer challenge.
 * Reads employee data, analyzes salary fairness and reporting line depth,
 * and prints the results to the console.
 *
 * Assumptions:
 *  - Managers should earn between 20% and 50% more than the average salary
 *    of their direct subordinates.
 *  - A reporting chain longer than 4 levels (from employee to CEO) is considered too long.
 *  - The input CSV is stored in src/main/resources/employees.csv.
 */
public class BigCompanyAnalyzerMainClass {

    public static void main(String[] args) {
        try {
            // Read all employees from the CSV file inside resources folder
            List<Employee> employees = CsvReader.readEmployeesFromResource("/employees.csv");

            // Create the analyzer object which performs salary and hierarchy checks
            OrganizationAnalyzer analyzer = new OrganizationAnalyzer(employees);

            // === Salary Analysis ===
            List<SalaryViolation> violations = analyzer.analyzeSalaryViolations();
            boolean hasUnderpaid = false;
            boolean hasOverpaid = false;

            System.out.println("=== Salary Analysis ===");

            // Print managers who earn less or more than allowed
            for (SalaryViolation v : violations) {
                if (v.getType().equals("UNDERPAID")) {
                    if (!hasUnderpaid) {
                        System.out.println("\nManagers earning LESS than they should:-(UNDERPAID)\n");
                        hasUnderpaid = true;
                    }
                    System.out.println(" - " + v);
                } else if (v.getType().equals("OVERPAID")) {
                    if (!hasOverpaid) {
                        System.out.println("\nManagers earning MORE than they should:-(OVERPAID)\n");
                        hasOverpaid = true;
                    }
                    System.out.println(" - " + v);
                }
            }

            if (!hasUnderpaid && !hasOverpaid) {
                System.out.println("All managers earn within the correct range.");
            }

            // === Reporting Line Analysis ===
            // Find employees who have more than 4 managers between them and the CEO
            List<String> longLines = analyzer.analyzeLongReportingLines(4);

            System.out.println("\n=== Reporting Line Analysis ===\n");
            if (longLines.isEmpty()) {
                System.out.println("All employees are within the allowed reporting depth.");
            } else {
                System.out.println("Employees have a reporting line which is too long, and by how much:\n");
                for (String line : longLines) {
                    System.out.println(" - " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading employees file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
