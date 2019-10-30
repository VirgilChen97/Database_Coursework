import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class DAO {
    static final String USER = "root";
    static final String PASS = "1A2S3D4F";
    private ResultSet access_data(String sql) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connector = DriverManager.getConnection("jdbc:mysql://10.0.0.11/mydb",USER,PASS);
        Statement statment = connector.createStatement();
        ResultSet rs = statment.executeQuery(sql);
        return rs;
    }

    public JTable get_table(String sql) {
        ResultSet rs = null;
        try{
            rs = access_data(sql);
            return displayResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JTable displayResultSet(ResultSet rs) throws SQLException {
        boolean moreRecords = rs.next(); // 定位到达第一条记录
        if (!moreRecords) {
            JOptionPane.showMessageDialog(null, "Result not found", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        Vector rows = new Vector();
        Vector columnHeads = new Vector();
        try {
            ResultSetMetaData rsmd = rs.getMetaData(); // 获得rs结果集中列属性信息
            for (int i = 1; i <= rsmd.getColumnCount(); ++i)
                columnHeads.addElement(rsmd.getColumnName(i)); // 获得列名(将列名存放至向量columnHeads)

            do {
                rows.addElement(getNextRow(rs, rsmd));
            }
            while (rs.next()); // 利用循环获得所有记录
            JTable dataTable = new JTable(rows, columnHeads); // 将获得的行列数据信息作为参数重新构造表格视图
            dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            return dataTable;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd)
            throws SQLException {
        Vector currentRow = new Vector(); // 定义一个向量,用于存放记录
        for (int i = 1; i <= rsmd.getColumnCount(); ++i)
            currentRow.addElement(rs.getString(i)); // 获取记录
        return currentRow; // 返回记录
    }
}
