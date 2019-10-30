public class Get_sql {
    public static void main(String[] args) {
        Get_sql a=new Get_sql();
        String [] column={"staff_ID","Name","Gender","Position","Office_ID","Age"};
        String [] text={"1234","","","","",""};
        String table_name="STAFF";
        String co_s="Age";
        char c='a';
        System.out.println(a.groupBy(column,text,table_name,co_s,"COUNT(*)"));
    }

    public String groupBy(String [] column, String []text, String table_name, String column_s, String ag_f){
        String sql_output="SELECT "+column_s+","+ag_f+" FROM ";
        boolean flag=true;
        for(int i=0;i<text.length;i++){
            if(!text[i].equals(""))
                flag=false;
        }
        if(flag)
            sql_output=sql_output+" "+table_name+ " GROUP BY "+column_s;
        else {
            String whr = where_SQL(column, text);
            sql_output = sql_output + table_name+ " WHERE " + whr + " GROUP BY " + column_s;
        }
        return sql_output;
    }

    public String stringToSQL(String [] column, String [] text, String table_name)
    {
        String Sql_output="SELECT * FROM";
        boolean flag=true;
        for(int i=0;i<text.length;i++){
            if(!text[i].equals(""))
                flag=false;
        }
        if(flag){
            Sql_output="SELECT * FROM "+table_name;
        }
        else
            Sql_output=Sql_output + " " + table_name + " WHERE " + where_SQL(column,text);
        return Sql_output;
    }

    public String stringToSQL(String [] column, String [] text, String table_name, String s_column, boolean order)
    {
        String Sql_output;
        if(!order)
            Sql_output=stringToSQL(column,text,table_name)+" ORDER BY "+s_column+" DESC";
        else if(order)
            Sql_output=stringToSQL(column,text,table_name)+" ORDER BY "+s_column+" ASC";
        else
            Sql_output=stringToSQL(column,text,table_name);
        return Sql_output;
    }

    public String stringToSQL(String [] column, String [] text, String table_name, String column_s)
    {
        String Sql_output ="SELECT AVG("+column_s+"),MAX("+column_s+"),MIN("+column_s+"),SUM("+column_s+") FROM ";

        boolean flag=true;
        for(int i=0;i<text.length;i++){
            if(!text[i].equals(""))
                flag=false;
        }
        if(flag){
                Sql_output=Sql_output+table_name;
        }
        else
                Sql_output=Sql_output + table_name + " WHERE " + where_SQL(column,text);
        return Sql_output;
    }

    public String count_SQL(String [] column, String [] text, String table_name){
        String sql_output="SELECT COUNT(*) FROM ";
        boolean flag=true;
        for(int i=0;i<text.length;i++){
            if(!text[i].equals(""))
                flag=false;
        }
        if(flag){
            sql_output=sql_output+table_name;
        }
        else
            sql_output=sql_output + " " + table_name + " WHERE " + where_SQL(column,text);
        return sql_output;
    }

    public boolean is_inequation(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='<'||s.charAt(i)=='>') {
                return true;
            }
        }
        return false;
    }
    public String [] inequation(String s){
        String [] max_min=new String[2];
        max_min[0]=null;
        max_min[1]=null;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='<') {
                int j = i + 1;
                while (j < s.length()&&s.charAt(j) != '>')
                    j++;
                String max = s.substring(i+1,j);
                max_min[0]=max;
            }
            if(s.charAt(i)=='>')
            {
                int j=i+1;
                while(j<s.length()&&s.charAt(j)!='<')
                    j++;
                String min = s.substring(i+1,j);
                max_min[1]=min;
            }
        }
        return max_min;
    }
    public String where_SQL(String [] column, String [] text) {
        int count=column.length;
        String [] condition=new String[count];
        String whr="";
        for(int i=0;i<count;i++)
        {
            condition[i]="";
        }
        for (int i = 0; i < text.length; i++) {
            if (!is_inequation(text[i]) && !text[i].equals("")) {
                condition[i] = column[i] + "=" + "'" + text[i] + "'";
            }
            if (is_inequation(text[i])) {
                String[] max_min = new String[2];
                max_min = inequation(text[i]);
                if (max_min[0] == null) {
                    condition[i] = column[i] + ">" + max_min[1];
                } else if (max_min[1] == null) {
                    condition[i] = column[i] + "<" + max_min[0];
                } else {
                    condition[i] = column[i] + ">" + max_min[1] + " AND " + column[i] + "<" + max_min[0];
                }
            }
        }
        int tag = 0;
        for (int i = 0; i < count; i++) {
            if (condition[i] != "") {
                tag++;
                if (tag == 1)
                    whr = whr + condition[i];
                else
                    whr = whr + " AND " + condition[i];
            }
        }
        return whr;
    }
}

