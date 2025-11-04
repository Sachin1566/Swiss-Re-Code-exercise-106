package com.bigcompany.analyzer.model;

import com.bigcompany.analyzer.model.Employee;

/**
 * Represents a violation of the expected manager salary range.
 *
 * Assumptions:
 *  - Type can be "UNDERPAID" or "OVERPAID".
 *  - Manager salary is compared with the average of direct subordinates.
 */
public class SalaryViolation {
    private final String type; // "UNDERPAID" or "OVERPAID"
    private final Employee manager;
    private final double expected;
    private final double difference;

    public SalaryViolation(String type, Employee manager, double expected, double difference) {
        this.type = type;
        this.manager = manager;
        this.expected = expected;
        this.difference = difference;
    }

    public String getType() {
        return type;
    }

    public Employee getManager() {
        return manager;
    }

    public double getExpected() {
        return expected;
    }

    public double getDifference() {
        return difference;
    }

    @Override
    public String toString() {
        if ("UNDERPAID".equals(type)) {
            return String.format(
                "%s (ID %d) earns %.2f, should earn at least %.2f (less by %.2f)",
                manager.getName(), manager.getId(), manager.getSalary(), expected, difference
            );
        } else {
            return String.format(
                "%s (ID %d) earns %.2f, should earn at most %.2f (more by %.2f)",
                manager.getName(), manager.getId(), manager.getSalary(), expected, difference
            );
        }
    }

}
