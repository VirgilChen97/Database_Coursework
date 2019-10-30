import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu {
    static int i=0;
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2,4));
        JButton jb[] = new JButton[8];
        String table[]={
                "car_info",
                "client_info",
                "office_info",
                "staff_info",
                "test",
                "time_table",
                "bad_client",
                "Interview_info"
        };

        jb[0]=new JButton(table[0]);
        jb[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new mainwindow().mainwindow(table[0]);
                }
            });
        jp.add(jb[0]);

        jb[1]=new JButton(table[1]);
        jb[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[1]);
            }
        });
        jp.add(jb[1]);

        jb[2]=new JButton(table[2]);
        jb[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[2]);
            }
        });
        jp.add(jb[2]);

        jb[3]=new JButton(table[3]);
        jb[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[3]);
            }
        });
        jp.add(jb[3]);

        jb[4]=new JButton(table[4]);
        jb[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[4]);
            }
        });
        jp.add(jb[4]);

        jb[5]=new JButton(table[5]);
        jb[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[5]);
            }
        });
        jp.add(jb[5]);

        jb[6]=new JButton(table[6]);
        jb[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[6]);
            }
        });
        jp.add(jb[6]);

        jb[7]=new JButton(table[7]);
        jb[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainwindow().mainwindow(table[7]);
            }
        });
        jp.add(jb[7]);

        jf.add(jp);
        jf.setVisible(true);
        jf.setSize(new Dimension(400,200));
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
