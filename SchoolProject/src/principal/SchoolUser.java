package principal;

import com.efrenmorales.ConnectionServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by Efren on 28/07/2016,12:25 PM.
 * SchoolProject
 */
public class SchoolUser extends JFrame implements ActionListener {

    static ResultSet rs;
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel groupID = new JLabel(" GroupID:");
    private JLabel lecture = new JLabel("Lecture: ");
    private JLabel teacher = new JLabel("Teacher: ");
    private JLabel student = new JLabel("Student: ");
    private JLabel score = new JLabel("Score: ");
    private JTextField GroupID = new JTextField();
    private JTextField Lecture = new JTextField();
    private JTextField Teacher = new JTextField();
    private JTextField Student = new JTextField();
    private JTextField Score = new JTextField();
    private JButton NewUser;
    private JButton UpdateUser;
    private JButton QuerieTopics;
    private JTable table = new JTable(10, 5);
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private FlowLayout fl1 = new FlowLayout();


    //JPopupMenu popmenu = new JPopupMenu();


    private SchoolUser() {

    }

    public static void main(String[] args) {

        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setSize(650, 400);
        schoolUser.GUI();
        schoolUser.setResizable(false);
        schoolUser.setVisible(true);
        schoolUser.setBackground(Color.lightGray);
        schoolUser.setTitle("School Catalogue");
        schoolUser.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    private void GUI() {

        NewUser = new JButton("NewUser");
        NewUser.addActionListener(this);
        UpdateUser = new JButton("UpdateUser");
        UpdateUser.addActionListener(this);
        QuerieTopics = new JButton("QuerieTopics");
        QuerieTopics.addActionListener(this);

        GroupID.setColumns(5);
        Lecture.setColumns(50);
        Teacher.setColumns(50);
        Student.setColumns(50);
        Score.setColumns(5);


        setLayout(gbl);

        //Primer fila
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(groupID);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        add(GroupID, gbc);
        //segunda fila
        gbc.gridwidth = 1;
        add(lecture);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(Lecture, gbc);
        //tercera fila
        gbc.gridwidth = 1;
        add(teacher);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(Teacher, gbc);
        //cuarta fila
        gbc.gridwidth = 1;
        add(student);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(Student, gbc);
        //quinta fila
        gbc.gridwidth = 1;
        add(score);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(Score, gbc);
        //panel de botones
        panel.setLayout(fl1);
        panel.add(NewUser);
        panel.add(UpdateUser);
        panel.add(QuerieTopics);

        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(15, 0, 0, 0);
        add(panel, gbc);

        //agregamos la tabla para los datos
        panel2.add(table);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(panel2, gbc);

    }

    public void actionPerformed(ActionEvent arg0) {
        try {

            if (arg0.getSource() == NewUser) {
                ConnectionServer connexion = new ConnectionServer();
                connexion.InsertStudent(GroupID, Student);
            }

            if (arg0.getSource() == UpdateUser) {
                ConnectionServer connexion = new ConnectionServer();
                connexion.UpdateTopics(GroupID, Lecture, Teacher);
            }
            if (arg0.getSource() == QuerieTopics) {

                try {
                    DefaultTableModel modelo = new DefaultTableModel();
                    this.table.setModel(modelo);
                    ConnectionServer connexion = new ConnectionServer();
                    ResultSet rs = connexion.getQueryTopics(GroupID.getText() + Lecture.getText() + Teacher.getText());
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadColumnas = rsMd.getColumnCount();
                    for (int i = 1; i <= cantidadColumnas; i++) {
                        modelo.addColumn(rsMd.getColumnLabel(i));
                    }
                    while (rs.next()) {
                        Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    rs.close();
                    connexion.closeConnection();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de Conexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error al cargar: ", JOptionPane.ERROR_MESSAGE);
        }


    }


}