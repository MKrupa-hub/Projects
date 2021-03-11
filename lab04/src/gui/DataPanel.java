package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

class Data {
	@Override
	public String toString() {
		return "Data [name=" + name + ", surname=" + surname + ", age=" + age + "]";
	}
	public String name;
	public String surname;
	public int age;
	public Data(String name, String surname, int age) {
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
}
class MyTableModel extends AbstractTableModel {
    public List<Data> getDataList() {
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	public void add(Data data) {
		this.dataList.add(data);
		this.fireTableRowsInserted(dataList.size()-1, dataList.size());
		
	}
	public MyTableModel(List<Data> dataList) {
		this.dataList = dataList;
	}
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Name","Surname","Age"};
	private List<Data> dataList = new ArrayList<>();


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
    	if (col == 0) return dataList.get(row).name;
        if (col == 1) return dataList.get(row).surname;
    	if (col == 2) return dataList.get(row).age;
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
    	if (col == 0) dataList.get(row).name = (String) value;
        if (col == 1) dataList.get(row).surname = (String) value;
    	if (col == 2) dataList.get(row).age = (int) value;
    	fireTableCellUpdated(row, col);
    }
}
public class DataPanel extends JPanel {
	public JTable getTable() {
		return table;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DataPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		List<Data> data = new ArrayList<>();
		data.add(new Data("John","Kowalsky",1));
		table = new JTable();
		table.setModel(new MyTableModel(data));
		scrollPane.setViewportView(table);
		
	}

}
