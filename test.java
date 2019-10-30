import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        Container container = jf.getContentPane();
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        container.setLayout(new FlowLayout());
        String[] columnNames = {"Product","Number of Boxes","Price"};
        Object[][] data =
                {
                        {"Apples", new Integer(5),"5.00"},
                        {"Oranges", new Integer(3),"6.00"},
                        {"Pears", new Integer(2),"4.00"},
                        {"Grapes", new Integer(3),"2.00"},
                        {"Apples", new Integer(5),"5.00"},
                        {"Oranges", new Integer(3),"6.00"},
                        {"Pears", new Integer(2),"4.00"},
                        {"Grapes", new Integer(3),"2.00"},
                        {"Apples", new Integer(5),"5.00"},
                        {"Oranges", new Integer(3),"6.00"},
                        {"Pears", new Integer(2),"4.00"},
                        {"Grapes", new Integer(3),"2.00"},
                        {"Apples", new Integer(5),"5.00"},
                        {"Oranges", new Integer(3),"6.00"},
                        {"Pears", new Integer(2),"4.00"},
                        {"Grapes", new Integer(3),"2.00"},
                        {"Apples", new Integer(5),"5.00"},
                        {"Oranges", new Integer(3),"6.00"},
                        {"Pears", new Integer(2),"4.00"},
                        {"Grapes", new Integer(3),"2.00"},
                };

        JTable dataTable = new JTable(data,columnNames);
        JScrollPane jsp = new JScrollPane(dataTable);
        dataTable.setEnabled(false);
        jsp.setPreferredSize(new Dimension(300,300));
        jp.add(jsp);
        container.add(jp);
        jf.setVisible(true);
        jf.pack();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
