# Swiss-Re-Code-exercise-106

### Project Name: Big Company Analyzer

This project is a coding challenge submission for **Swiss Re – Application Engineer ** role.

It analyzes employee data from a CSV file and reports:
- Which **managers earn less** than they should, and by how much  
- Which **managers earn more** than they should, and by how much  
- Which **employees have a reporting line that is too long**, and by how much  

## How to Run
### Prerequisites
- Java SE 8 or higher
- Any IDE (Eclipse, IntelliJ, or Spring Tool Suite)
- Maven (if building from command line)
 ### Steps
1. Clone or download this repository.
2. Open the project in your IDE.
3. Place the file `employees.csv` inside:src/main/resources/
4. Run the main class: com.bigcompany.analyzer.BigCompanyAnalyzerMainClass
5. View results in the console.



## Approach & Design

The code is written with a focus on **simplicity, readability, and correctness**.

### Main Components
| Class                         | Responsibility |
|-------------------------------|----------------|
| `Employee`                    | Represents each employee (id, name, salary, manager). |
| `CsvReader`                   | Reads data from `employees.csv` file. |
| `OrganizationAnalyzer`        | Performs salary and hierarchy analysis. |
| `SalaryViolation`             | Holds information about manager salary violations. |
| `BigCompanyAnalyzerMainClass` | Main class that drives execution and prints results. |

##  Key Assumptions

1. **Salary Fairness Rules**
- Managers must earn **at least 20% more** than the **average salary of their direct subordinates**.
- Managers must **not earn more than 50% above** that average.
- These thresholds are stored as:
  ```java
  double minFactor = 1.2; // minimum 20% more than subordinates
  double maxFactor = 1.5; // maximum 50% above subordinates' average
  
2. **Reporting Line Depth**
- A reporting chain longer than **4 managers between an employee and the CEO** is considered **too long**.

3. **Data Format**
- CSV file  format:
   ```
  id,firstName,lastName,salary,managerId
  123,Joe,Doe,60000,
  124,Martin,Chekov,45000,123
  ...
  ```
   The CEO has no "managerId".

##  Sample Output
   === Salary Analysis ===

###Managers earning LESS than they should:-(UNDERPAID)

 - Martin Chekov earns 45000.0, should earn at least 59280.0 (less by 14280.0)
 - Bob Ronstad earns 47000.0, should earn at least 54120.0 (less by 7120.0)
 - Oliver Grant earns 55000.0, should earn at least 56640.0 (less by 1640.0)

### Managers earning MORE than they should:-(OVERPAID)

 - Alice Hasacat earns 70000.0, should earn at most 51000.0 (more by 19000.0)
 - Brett Hardleaf earns 34000.0, should earn at least 36000.0 (less by 2000.0)
 - Chris Stone earns 30000.0, should earn at least 33600.0 (less by 3600.0)
 - Linda Park earns 28000.0, should earn at least 31200.0 (less by 3200.0)
 - Kevin Ray earns 26000.0, should earn at least 28800.0 (less by 2800.0)
 - Adam Clark earns 50000.0, should earn at least 51600.0 (less by 1600.0)
 - Chloe Ward earns 43000.0, should earn at least 50400.0 (less by 7400.0)
 - Sophia Gray earns 42000.0, should earn at least 49200.0 (less by 7200.0)
 - Jacob Bell earns 41000.0, should earn at least 48000.0 (less by 7000.0)
 - Amelia Adams earns 40000.0, should earn at least 46800.0 (less by 6800.0)
 - Daniel Long earns 39000.0, should earn at least 45600.0 (less by 6600.0)
 - Charlotte Wood earns 38000.0, should earn at least 44400.0 (less by 6400.0)
 - Henry Morgan earns 37000.0, should earn at least 43200.0 (less by 6200.0)
 - Victoria Brown earns 36000.0, should earn at least 42000.0 (less by 6000.0)
 - Oscar Perry earns 35000.0, should earn at least 40800.0 (less by 5800.0)
 - George Russell earns 34000.0, should earn at least 39600.0 (less by 5600.0)
 - Lucy Brooks earns 33000.0, should earn at least 38400.0 (less by 5400.0)
 - Leo Mills earns 32000.0, should earn at least 37200.0 (less by 5200.0)
 - Ellie Price earns 31000.0, should earn at least 36000.0 (less by 5000.0)
 - Layla Bennett earns 30000.0, should earn at least 34800.0 (less by 4800.0)
 - Freya Morris earns 29000.0, should earn at least 33600.0 (less by 4600.0)
 - Aria Rogers earns 28000.0, should earn at least 32400.0 (less by 4400.0)
 - Abel Turner earns 27000.0, should earn at least 31200.0 (less by 4200.0)
 - Mason Gray earns 26000.0, should earn at least 30000.0 (less by 4000.0)
 - Luna Bailey earns 25000.0, should earn at least 28800.0 (less by 3800.0)
 - Stella Evans earns 24000.0, should earn at least 27600.0 (less by 3600.0)

=== Reporting Line Analysis ===

### Employees have a reporting line which is too long, and by how much:

 - Linda Park has 5 managers in their reporting chain, which is 1 more than the allowed 4.
 - Kevin Ray has 6 managers in their reporting chain, which is 2 more than the allowed 4.
 - Sara Brown has 7 managers in their reporting chain, which is 3 more than the allowed 4.
 - Jacob Bell has 5 managers in their reporting chain, which is 1 more than the allowed 4.
 - Amelia Adams has 6 managers in their reporting chain, which is 2 more than the allowed 4.
 - Daniel Long has 7 managers in their reporting chain, which is 3 more than the allowed 4.
 - Charlotte Wood has 8 managers in their reporting chain, which is 4 more than the allowed 4.
 - Henry Morgan has 9 managers in their reporting chain, which is 5 more than the allowed 4.
 - Victoria Brown has 10 managers in their reporting chain, which is 6 more than the allowed 4.
 - Oscar Perry has 11 managers in their reporting chain, which is 7 more than the allowed 4.
 - George Russell has 12 managers in their reporting chain, which is 8 more than the allowed 4.
 - Lucy Brooks has 13 managers in their reporting chain, which is 9 more than the allowed 4.
 - Leo Mills has 14 managers in their reporting chain, which is 10 more than the allowed 4.
 - Ellie Price has 15 managers in their reporting chain, which is 11 more than the allowed 4.
 - Layla Bennett has 16 managers in their reporting chain, which is 12 more than the allowed 4.
 - Freya Morris has 17 managers in their reporting chain, which is 13 more than the allowed 4.
 - Aria Rogers has 18 managers in their reporting chain, which is 14 more than the allowed 4.
 - Abel Turner has 19 managers in their reporting chain, which is 15 more than the allowed 4.
 - Mason Gray has 20 managers in their reporting chain, which is 16 more than the allowed 4.
 - Luna Bailey has 21 managers in their reporting chain, which is 17 more than the allowed 4.
 - Stella Evans has 22 managers in their reporting chain, which is 18 more than the allowed 4.
 - Zoe Carter has 23 managers in their reporting chain, which is 19 more than the allowed 4.

