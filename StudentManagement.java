import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "Anjali@2006";

        System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter student email: ");
            String email = sc.nextLine();

            System.out.print("Enter student course: ");
            String course = sc.nextLine();

            String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";

            try (
                Connection con = DriverManager.getConnection(
                    url, username, password
                );
                PreparedStatement ps = con.prepareStatement(sql)
            ) {

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, course);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Student Added Successfully!");
                }

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else if (choice == 2) {

    String sql = "SELECT * FROM students";

    try (
        Connection con = DriverManager.getConnection(
            url, username, password
        );
        java.sql.Statement stmt = con.createStatement();
        java.sql.ResultSet rs = stmt.executeQuery(sql)
    ) {

        System.out.println("\n===== STUDENT RECORDS =====");

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Course: " + rs.getString("course"));
            System.out.println("---------------------------");
        }

         } catch (SQLException e) {
          System.out.println("Error: " + e.getMessage());
         }

        } else if (choice == 3) {

    System.out.print("Enter Student ID to update: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter new name: ");
    String name = sc.nextLine();

    System.out.print("Enter new email: ");
    String email = sc.nextLine();

    System.out.print("Enter new course: ");
    String course = sc.nextLine();

    String sql = "UPDATE students SET name=?, email=?, course=? WHERE id=?";

    try (
        Connection con = DriverManager.getConnection(
            url, username, password
        );
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, course);
        ps.setInt(4, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Updated Successfully!");
        } else {
            System.out.println("Student ID Not Found!");
        }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        } else if (choice == 4) {

    System.out.print("Enter Student ID to delete: ");
    int id = sc.nextInt();

    String sql = "DELETE FROM students WHERE id=?";

    try (
        Connection con = DriverManager.getConnection(
            url, username, password
        );
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Student Deleted Successfully!");
        } else {
            System.out.println("Student ID Not Found!");
        }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        } else if (choice == 5) {
            System.out.println("Thank you!");

        } else {
            System.out.println("Invalid Choice!");
        }

        sc.close();
    }
}