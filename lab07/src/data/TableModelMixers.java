package data;

import support.Mixer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModelMixers extends AbstractTableModel {

    private String[] columnNames = {"Mixers"};
    private List<Mixer> dataList = new ArrayList<>();

    public TableModelMixers(List<Mixer> dataList) {
        this.dataList = dataList;
    }

    public List<Mixer> getDataList() {
        return dataList;
    }

    public void setDataList(List<Mixer> daneList) {
        this.dataList = daneList;
    }

    public void add(Mixer dane) {
        this.dataList.add(dane);
        this.fireTableRowsInserted(dataList.size() - 1, dataList.size());

    }
    public void remove(Mixer dane) {
        for (Mixer mix : dataList) {
            if (mix.getName().equals(dane.getName())) {
                dataList.remove(mix);
            }
        }
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
        if (col == 0) return dataList.get(row).getName();

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