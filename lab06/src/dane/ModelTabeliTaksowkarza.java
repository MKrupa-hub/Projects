package dane;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelTabeliTaksowkarza extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String[] columnNames = {"Identyfikator taksowki", "Status"};
    private List<Dane> daneList = new ArrayList<>();

    public ModelTabeliTaksowkarza(List<Dane> daneList) {
        this.daneList = daneList;
    }

    public List<Dane> getDataList() {
        return daneList;
    }

    public void setDataList(List<Dane> daneList) {
        this.daneList = daneList;
    }

    public void add(Dane dane) {
        this.daneList.add(dane);
        this.fireTableRowsInserted(daneList.size() - 1, daneList.size());

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return daneList.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) return daneList.get(row).identyfikator;
        if (col == 1) return daneList.get(row).status_zlecenie;
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return true;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (col == 0) daneList.get(row).identyfikator = (String) value;
        if (col == 1) daneList.get(row).status_zlecenie = (String) value;
        fireTableCellUpdated(row, col);
    }
}