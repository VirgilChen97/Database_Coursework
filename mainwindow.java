import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class mainwindow extends JFrame{
    JPanel client = new JPanel();
    JScrollPane dataPane = new JScrollPane();
    JScrollPane resultPane = new JScrollPane();
    JPanel choose = new JPanel();
    JPanel extra = new JPanel();
    JButton submmit = new JButton("Search");

    public void mainwindow(String table){
        JTable dataTable = new DAO().get_table("SELECT * FROM " + table);
        TableColumnModel model = dataTable.getColumnModel();
        Container container = getContentPane();
        container.setLayout(new CardLayout());
        container.add(client);
        client.setLayout(new BorderLayout());
        client.add(dataPane,BorderLayout.CENTER);
        dataPane.setViewportView(dataTable);

        choose.setLayout(new GridLayout(0,2,20,20));
        int count = dataTable.getColumnCount();
        JTextField rule[] = new JTextField[count];
        JLabel rule_text[] = new JLabel[count];
        for(int i=0;i<count;i++){
            rule_text[i]=new JLabel(dataTable.getColumnName(i));
            rule[i] = new JTextField();
            rule[i].setSize(new Dimension(100,50));
            choose.add(rule_text[i]);
            choose.add(rule[i]);
        }

        extra.setLayout(new GridLayout(1,3));
        JTextField selected_column = new JTextField();
        JTextField group_column = new JTextField();
        selected_column.setPreferredSize(new Dimension(100,50));
        extra.add(selected_column);
        extra.add(group_column);
        extra.add(resultPane);
        client.add(extra,BorderLayout.SOUTH);

        submmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rule_get = new String[count];
                String[] rule_column = new String[count];
                String sql;
                for(int i=0;i<count;i++){
                    rule_get[i]= rule[i].getText();
                    rule_column[i]=dataTable.getColumnName(i);
                }
                if(group_column.getText().equals("")) {
                    sql = new Get_sql().stringToSQL(rule_column, rule_get, "" + table);
                }
                else{
                    sql = new Get_sql().groupBy(rule_column,rule_get,"" + table,group_column.getText(),"count(*)");
                }

                JTable dataTable = new DAO().get_table(sql);
                dataPane.setViewportView(dataTable);

                if(!selected_column.getText().equals("")) {
                    sql = new Get_sql().stringToSQL(rule_column, rule_get, "" + table,selected_column.getText());
                    JTable result_table = new DAO().get_table(sql);
                    resultPane.setViewportView(result_table);
                }

            }
        });


        choose.add(submmit);
        client.add(choose,BorderLayout.EAST);

        this.setSize(600,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }





    public static void main(String[] args) {
        new mainwindow();
    }
}
