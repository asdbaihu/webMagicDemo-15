package zp.demo.bean;

import java.util.ArrayList;
import java.util.List;

public class comEmpbean {
    private static String totalRows = "91";
    
    public static String getTotalRows() {
        return totalRows;
    }
    
    public static void setTotalRows(String totalRows) {
        comEmpbean.totalRows = totalRows;
    }
    
    public static List getLll() {
        return lll;
    }
    
    public static void setLll(List lll) {
        comEmpbean.lll = lll;
    }
    
    private static List lll = new ArrayList<EmpBean>();
    
    public static void main(String[] args) {
        // lll.add(eee);
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("totalRows:"+this.getTotalRows());
        sb.append(",[");
        for (int i = 0; i < this.lll.size(); i++) {
            sb.append("{");
            sb.append(this.lll.get(i));
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
