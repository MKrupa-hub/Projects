package data;

import communication.ISite;

import javax.swing.table.AbstractTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class TableModelSites extends AbstractTableModel {
    private String[] columnNames = {"Sites"};
    private List<ISite> dataList = new ArrayList<>();

    public TableModelSites(List<ISite> dataList) {
        this.dataList = dataList;
    }

    public List<ISite> getDataList() {
        return dataList;
    }

    public void setDataList(List<ISite> daneList) {
        this.dataList = daneList;
    }

    public void add(ISite dane) {
        this.dataList.add(dane);
        this.fireTableRowsInserted(dataList.size() - 1, dataList.size());

    }
    public void remove(ISite dane) {
        this.dataList.remove(dane);
        this.fireTableRowsInserted(dataList.size() - 1, dataList.size());

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataList.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) {
            try {
                return dataList.get(row).getName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return null;
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

        fireTableCellUpdated(row, col);
    }

}