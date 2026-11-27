package lab_assingments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeManagementSystem 
{
	static String driver = "oracle.jdbc.OracleDriver";
	static String DBurl = "jdbc:oracle:thin:@localhost:1521:free";
	static String uNm = "your_username";
	static String pwd = "your_password";
	Scanner sc = new Scanner(System.in);

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(DBurl, uNm, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	void operations() {
		try {
			Connection con = getCon();
			PreparedStatement stm1 = con.prepareStatement("insert into employee_db values(?,?,?,?,?,?)");
			PreparedStatement stm2 = con.prepareStatement("select * from employee_db");
			PreparedStatement stm3 = con.prepareStatement("select * from employee_db where empId=?");
			PreparedStatement stm4 = con
					.prepareStatement("update employee_db set name=?,age=?,department=?,salary=?,email=? where empId=?");
			PreparedStatement stm5 = con.prepareStatement("delete from employee_db where empId=?");
			PreparedStatement stm6 = con
					.prepareStatement("select * from employee_db where SALARY=(select max(SALARY) from employee_db)");
			PreparedStatement stm7 = con.prepareStatement("select * from employee_db where DEPARTMENT=? ");
			System.out.println("============================================");
			System.out.println("Welcom to the Employee Management System.");
			System.out.println("============================================");
			while (true) {
				System.out.println("1.Add Employee");
				System.out.println("2.View All Employee");
				System.out.println("3.Search Employee");
				System.out.println("4.Update Employee");
				System.out.println("5.Delete Employee");
				System.out.println("6.Find Highest Salary");
				System.out.println("7.Find Employees by Department");
				System.out.println("8.Exit\n");
				System.out.println("Enter your choice: ");
				int choice = sc.nextInt();
				switch (choice) {
					case 1 -> {
						System.out.println("You are choosing the Add Employee");
						System.out.println("Enter the new employee id that you want to add: ");
						int newId = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the new employee name that you want to add: ");
						String newName = sc.nextLine();
						System.out.println("Enter the new employee age that you want to add: ");
						int newAge = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the new employee department that you want to add: ");
						String department = sc.nextLine();
						System.out.println("Enter the new employee salary that you want to add: ");
						double salary = sc.nextDouble();
						sc.nextLine();
						System.out.println("Enter the new employee emailID that you want to add: ");
						String email = sc.nextLine();

						stm1.setInt(1, newId);
						stm1.setString(2, newName);
						stm1.setInt(3, newAge);
						stm1.setString(4, department);
						stm1.setDouble(5, salary);
						stm1.setString(6, email);

						int rs1 = stm1.executeUpdate();
						if (rs1 > 0) {
							System.out.println(newId + " record added successfully in the Employee Management DB.");
						} else {
							System.out.println("Employee not found!!");
						}
					}
					case 2 -> {
						System.out.println("You are choosing the View Employee");
						System.out.println("");
						ResultSet rs2 = stm2.executeQuery();
						while (rs2.next()) {
							System.out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getInt(3) + " " + rs2.getString(4)
									+ " " + rs2.getDouble(5) + " " + rs2.getString(6));
						}
					}
					case 3 -> {
						System.out.println("You are choosing the Search Employee based on id.");
						System.out.println("Enter the id that you want to search the record of Employee Management: ");
						int id = sc.nextInt();

						stm3.setInt(1, id);
						ResultSet rs3 = stm3.executeQuery();
						while (rs3.next()) {
							System.out.println(rs3.getInt(1) + " " + rs3.getString(2) + " " + rs3.getInt(3) + " " + rs3.getString(4)
									+ " " + rs3.getDouble(5) + " " + rs3.getString(6));
						}
					}
					case 4 -> {
						System.out.println("You are choosing the Update Employee based on id.");
						System.out.println("Enter the id that you want to update the record of Employee Management: ");
						int id = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the name that you want to update: ");
						String name = sc.nextLine();

						System.out.println("Enter the age that you want to update: ");
						int age = sc.nextInt();
						sc.nextLine();

						System.out.println("Enter the department that you want to update: ");
						String department = sc.nextLine();

						System.out.println("Enter the salary that you want to update: ");
						double salary = sc.nextDouble();
						sc.nextLine();

						System.out.println("Enter the emailId that you want to update: ");
						String mail = sc.nextLine();

						stm4.setString(1, name);
						stm4.setInt(2, age);
						stm4.setString(3, department);
						stm4.setDouble(4, salary);
						stm4.setString(5, mail);
						stm4.setInt(6, id);

						int rs4 = stm4.executeUpdate();
						if (rs4 > 0) {
							System.out.println(id + " record updated successsfully.");
						} else {
							System.out.println("Employee not found.");
						}
					}
					case 5 -> {
						System.out.println("You are choosing the delete the employee based on id.");
						System.out.println("Enter the id: ");
						int id = sc.nextInt();

						stm5.setInt(1, id);

						int rs5 = stm5.executeUpdate();
						if (rs5 > 0) {
							System.out.println(id + " record successfully delete from the employee management DB.");
						} else {
							System.out.println("Employee not found.");
						}
					}
					case 6 -> {
						System.out.println("You are choosing to find highest salary.");
						ResultSet rs6 = stm6.executeQuery();
						while (rs6.next()) {
							System.out.println(rs6.getInt(1) + " " + rs6.getString(2) + " " + rs6.getInt(3) + " " + rs6.getString(4)
									+ " " + rs6.getDouble(5) + " " + rs6.getString(6));
						}
					}
					case 7 -> {
						System.out.println("You are choosing to Find Employees by Department");
						sc.nextLine();
						System.out.println("Enter the department: ");
						String department = sc.nextLine();

						stm7.setString(1, department);
						ResultSet rs7 = stm7.executeQuery();
						while (rs7.next()) {
							System.out.println(rs7.getInt(1) + " " + rs7.getString(2) + " " + rs7.getInt(3) + " " + rs7.getString(4)
									+ " " + rs7.getDouble(5) + " " + rs7.getString(6));
						}
					}
					case 8 -> {
						System.out.println("Thank you!!");
						return;
					}
					default -> {
						System.out.println("Invalid choice.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EmployeeManagementSystem obj = new EmployeeManagementSystem();
		obj.operations();
	}
}
