package program;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;

class Data {
    public void setDistancedone(int distancedone) {
        this.distancedone = distancedone;
    }

    public void setRealtime(int realtime) {
        this.realtime = realtime;
    }

    @Override
    public String toString() {
        return "\n" + "Dane [imie=" + name + ", identyfikator=" + identify + ", dystans do pokonania=" + pd + ", przewidywany czas=" + et + ", dystans pokonany=" + dd + ", rzeczywisty czas=" + rt + "]";
    }

    public String name;
    public String identify;
    public int planneddistance;
    public int expectedtime;
    public int distancedone;
    public int realtime;
    public ArrayList<Integer> pd =new ArrayList<Integer>();
    public ArrayList<Integer> et =new ArrayList<Integer>();
    public ArrayList<Integer> dd =new ArrayList<Integer>();
    public ArrayList<Integer> rt =new ArrayList<Integer>();


    public Data(String name, String identify, int planneddistance, int expectedtime, int distancedone, int realtime) {
        this.name = name;
        this.identify = identify;
        this.planneddistance = planneddistance;
        this.expectedtime = expectedtime;
        this.distancedone = distancedone;
        this.realtime = realtime;
    }

    public void addpd(int val) {

        pd.add(val);
    }
    public void addet(int val) {
        et.add(val);
    }
    public void adddd(int val) {
        dd.add(val);
    }
    public void addrt(int val) {
        rt.add(val);
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
        this.fireTableRowsInserted(dataList.size() - 1, dataList.size());

    }

    public MyTableModel(List<Data> dataList) {
        this.dataList = dataList;
    }

    private static final long serialVersionUID = 2L;
    private String[] columnNames = {"Imie", "Identyfikator", "Dystans do pokonania", "Przewidywany czas", "Dystans pokonany", "Rzeczywisty czas"};
    private List<Data> dataList = new ArrayList<>();


    public int getColumnCount() {
        return columnNames.length;
    }
    public void addToTrainData(Data data){
        data.addpd(data.planneddistance);
        data.addet(data.expectedtime);
        data.adddd(data.distancedone);
        data.addrt(data.realtime);
    }
    public void doaTraining() {

        for (Data data : dataList) {
            Random rand = new Random();
            if (data.expectedtime != 0 && data.planneddistance != 0) {
                data.setDistancedone(data.planneddistance + rand.nextInt(data.planneddistance)/3-rand.nextInt(data.planneddistance)/2);
                data.setRealtime(data.expectedtime + rand.nextInt(data.expectedtime + 1) - rand.nextInt(data.expectedtime));
                this.fireTableRowsInserted(0, dataList.size());
                addToTrainData(data);
            }
        }
    }

    public int getRowCount() {
        return dataList.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) return dataList.get(row).name;
        if (col == 1) return dataList.get(row).identify;
        if (col == 2) return dataList.get(row).planneddistance;
        if (col == 3) return dataList.get(row).expectedtime;
        if (col == 4) return dataList.get(row).distancedone;
        if (col == 5) return dataList.get(row).realtime;
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


    public void setValueAt(Object value, int row, int col) {
        if (col == 0) dataList.get(row).name = (String) value;
        if (col == 1) dataList.get(row).identify = (String) value;
        if (col == 2) dataList.get(row).planneddistance = (int) value;
        if (col == 3) dataList.get(row).expectedtime = (int) value;
        fireTableCellUpdated(row, col);
    }
}

public class DataPanel extends JPanel {
    public JTable getTable() {
        return table;
    }


    private static final long serialVersionUID = 2L;
    private JTable table;


    public DataPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);


        List<Data> data = new ArrayList<>();
        table = new JTable();
        table.setModel(new MyTableModel(data));
        scrollPane.setViewportView(table);

    }

}
