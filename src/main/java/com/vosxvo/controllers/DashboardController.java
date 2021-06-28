package com.vosxvo.controllers;

import com.vosxvo.services.file.Configuration;
import com.vosxvo.services.model.Department;
import com.vosxvo.services.model.Employee;
import com.vosxvo.services.model.User;
import com.vosxvo.services.sql.MySQLHandle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label users;

    @FXML
    private Label employees;

    @FXML
    private Label departments;

    @FXML
    private BarChart<Double, String> barChar;

    @FXML
    private Label top1;

    @FXML
    private Label top2;

    @FXML
    private Label top3;

    @FXML
    private Label top4;

    @FXML
    private Label top5;

    @FXML
    private Label low1;

    @FXML
    private Label low2;

    @FXML
    private Label low3;

    @FXML
    private Label low4;

    @FXML
    private Label low5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initFeatures();
        initChart();
        initRank();
    }

    private void initFeatures() {
        Connection connection = null;
        Statement statement = null;

        try {
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT COUNT(*) FROM " + User.TABLE);
            if (set != null && set.next()) {
                users.setText(String.valueOf(set.getInt(1)));
            }
            set = statement.executeQuery("SELECT COUNT(*) FROM " + Employee.TABLE);
            if (set != null && set.next()) {
                employees.setText(String.valueOf(set.getInt(1)));
            }
            set = statement.executeQuery("SELECT COUNT(*) FROM " + Department.TABLE);
            if (set != null && set.next()) {
                departments.setText(String.valueOf(set.getInt(1)));
            }

            if (statement != null) statement.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void initChart() {
        Connection connection = null;
        Statement statement = null;
        XYChart.Series<Double, String> series = new XYChart.Series<>();

        try {
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT dept.dept_name, SALA.AVG_SALARY FROM departments dept, " +
                    "(SELECT de.dept_no AS DENO, AVG(sa.salary) AS AVG_SALARY FROM salaries sa " +
                    "JOIN dept_emp de ON sa.emp_no = de.emp_no GROUP BY de.dept_no) AS SALA WHERE dept.dept_no = SALA.DENO;");
            while (set != null && set.next()) {
                series.getData().add(new XYChart.Data<>(set.getDouble(2), set.getString(1)));
            }
            if (statement != null) statement.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            barChar.getData().addAll(series);
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void initRank() {
        Connection connection = null;
        Statement statement = null;

        try {
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT em.first_name, em.last_name FROM employees em " +
                    "JOIN salaries s ON em.emp_no = s.emp_no ORDER BY s.salary DESC LIMIT 5;");
            if (set.next()) top1.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) top2.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) top3.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) top4.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) top5.setText(set.getString(1) + " " + set.getString(2));
            set = statement.executeQuery("SELECT em.first_name, em.last_name FROM employees em " +
                    "JOIN salaries s ON em.emp_no = s.emp_no ORDER BY s.salary LIMIT 5;");
            if (set.next()) low1.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) low2.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) low3.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) low4.setText(set.getString(1) + " " + set.getString(2));
            if (set.next()) low5.setText(set.getString(1) + " " + set.getString(2));
            if (statement != null) statement.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
