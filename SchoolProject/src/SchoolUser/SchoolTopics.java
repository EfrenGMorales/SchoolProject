package SchoolUser;

import javax.swing.*;
import java.sql.ResultSet;

/**
 * Created by Efren on 28/07/2016,12:22 PM.
 * SchoolProject
 */
public class SchoolTopics extends JFrame {

    static ResultSet rs;
    JTable table = new JTable(10, 5);


    public SchoolTopics() {
        initComponents();
        //Topics();
    }

    private void initComponents() {
    }

    //codigo implementado para consola, para hacer las pruebas de conexion
//    public void Topics() {
//        ConnectionServer connexion = new ConnectionServer();
//
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0);
//        rs = connexion.getQueryTopics("select * from topics");
//        try {
//            while (rs.next()) {
//                Vector v = new Vector();
//                v.add(rs.getString(1));
//                v.add(rs.getString(2));
//                v.add(rs.getString(3));
//                model.addRow(v);
//                table.setModel(model);
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error en la Consulta", JOptionPane.ERROR_MESSAGE);
//        }
//    }


//   public void AddStudent(String GroupID, String Lecture, String Teacher)
//   {
//    String addregister = "insert into topics(GroupID, Lecture, Teacher) values (?,?,?)";
//       try {
//           PreparedStatement ps = connexion.prepareStatement(addregister);
//           ps.setString(1,GroupID);
//           ps.setString(2,Lecture);
//           ps.setString(3,Teacher);
//           ps.executeUpdate();
//
//
//       } catch (SQLException e) {
//           e.printStackTrace();
//       }
//
//
//   }
}












