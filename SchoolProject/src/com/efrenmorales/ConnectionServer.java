package com.efrenmorales;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Efren on 28/07/2016,12:14 PM.
 * SchoolProject
 */
public class ConnectionServer {


    private static Connection connexion = null;
    private static String _user = "root";
    private static String _pwd = "root";

    //Establecer una conexion a Mysql para ingresar a la base de datos afectada
    private static Connection connectionServer() {

        String url = "jdbc:mysql://localhost/schoolcatalogue";


        try {
            Class.forName("com.mysql.jdbc.Connection");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Imposible conectar al Servidor", JOptionPane.ERROR_MESSAGE);
        }
        try {
            connexion = DriverManager.getConnection(url, _user, _pwd);
            if (connexion != null) {
                JOptionPane.showMessageDialog(null, "La conexion a la base de datos " + url + "... Ok");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Imposible conectar a la base de datos", JOptionPane.ERROR_MESSAGE);
        }
        return connexion;
    }
    //Conexion a la base de datos para consulta general

    public ResultSet getQueryTopics(String querieBD) {

        Connection conn = connectionServer();

        Statement stm;
        try {
            stm = conn.createStatement();
            return stm.executeQuery(querieBD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }


    //Conexion a la base de datos para Ingreso de valores
    public void setQuery(String _query) {

        PreparedStatement ps;
        try {
            ps = (PreparedStatement) connexion.createStatement();
            ps.executeQuery(_query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            e.getMessage();
        }

    }

    //Cierre de las conexiones
    public void closeConnection() {
        try {
            connexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
    }

//        public ArrayList<String> returnLecture() throws SQLException{
//            ArrayList<String> ls = new ArrayList<String>();
//            PreparedStatement ps = null;
//
//                ps = connexion.prepareStatement("Select * from topics");
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    ls.add(rs.getString("GroupID"));
//                }
//                rs.close();
//                return ls;
//        }

    //Consulta de Materias
    public void queriesTopics() {

        //JTable table = null;
        ResultSet rs;
        ConnectionServer connexion = new ConnectionServer();

//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0);
//        rs = connexion.getQueryTopics("select * from topics");
//        try {
//            while (rs.next()) {
//                Vector v = new Vector();
//                v.addElement(rs.getString(1));
//                v.addElement(rs.getString(2));
//                v.addElement(rs.getString(3));
//                model.addRow(v);
//                table.setModel(model);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la Consulta", JOptionPane.ERROR_MESSAGE);
//        }
        //codigo implementado para consola y chequeo de conexiones a base de datos
        String GroupID, Lecture, Teachers;

        rs = getQueryTopics("select GroupID, Lecture, Teacher from topics");

        try {
            while (rs.next()) {

                GroupID = rs.getString("GroupID");
                Lecture = rs.getString("Lecture");
                Teachers = rs.getString("Teacher");
                System.out.println("Grupo: " + GroupID + " Materia: " + Lecture + " Profesor: " + Teachers);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Consulta de Estudiantes
    public void queriesStudent() {

        ResultSet rs;
        String GroupID, Student;

        rs = getQueryTopics("select GroupID, Student from studentregister");

        try {
            while (rs.next()) {

                GroupID = rs.getString("GroupID");
                Student = rs.getString("Student");
                System.out.println("Grupo: " + GroupID + " Estudiante: " + Student);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta");
            e.getMessage();
        }

    }

    //Consulta de Calificaciones
    public void queriesRecord() {

        ResultSet rs;
        String GroupID, Students, Results;

        rs = getQueryTopics("select * from schoolcatalogue.assessmentresults");

        try {
            while (rs.next()) {

                GroupID = rs.getString("GroupID");
                Students = rs.getString("Students");
                Results = rs.getString("Results");
                System.out.println("Grupo: " + GroupID + " Estudiante: " + Students + "Resultados: " + Results);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta");
            e.getMessage();
        }
    }


    //Alta de Estudiante

    public void InsertStudent(JTextField GroupID, JTextField Student) {

        String UpdateUsers = "INSERT INTO schoolcatalogue.studentregister (GroupID, Student) values (?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(UpdateUsers);
            ps.setString(1, String.valueOf(GroupID));
            ps.setString(2, String.valueOf(Student));
            ps.executeUpdate();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la Consulta", JOptionPane.ERROR_MESSAGE);

        }

    }

    //Actualizacion de Estudiante

    public void UpdateStudent(JTextField GroupID, JTextField Student) {

        String UpdateUsers = "INSERT INTO schoolcatalogue.studentregister (GroupID, Student) values (?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(UpdateUsers);
            ps.setString(1, String.valueOf(GroupID));
            ps.setString(2, String.valueOf(Student));
            ps.executeUpdate();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la Consulta", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void UpdateTopics(JTextField GroupID, JTextField Lecture, JTextField Teacher) {
        String UpdateUsers = "insert into studentregister (GroupID, Lecture, Teacher) values (?,?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(UpdateUsers);
            ps.setString(1, String.valueOf(GroupID));
            ps.setString(2, String.valueOf(Lecture));
            ps.setString(3, String.valueOf(Teacher));
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la consulta", JOptionPane.ERROR_MESSAGE);

        }
    }

}
