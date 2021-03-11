package program;

import java.awt.*;
import java.util.List;

public class ChartPanel extends javax.swing.JPanel {
    private static final int BORDER_GAP = 30;
    public List<Data> dataList;
    boolean correct = true;

    public ChartPanel(List<Data> dataList) {
        initComponents();
        this.dataList = dataList;
    }


    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < getHeight(); i++) {
            int x0 = BORDER_GAP + i * 20;
            int x1 = x0;
            int y0 = getHeight() / 2 - BORDER_GAP + 3;
            int y1 = y0 - 6;
            g2.drawLine(x0, y0, x1, y1);
        }
        // create y and x
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() / 2 - BORDER_GAP, getWidth() - BORDER_GAP / 6, getHeight() / 2 - BORDER_GAP);


        for (int j = 0; j < dataList.size(); j++) {

            if (dataList.get(j).pd.size() == 0) {
                correct = false;
            }

        }

        if (correct = true) {
            for (int k = 0; k < dataList.size(); k++) {
                for (int i = 0; i < dataList.get(k).pd.size(); i++) {

                    g2.drawLine(BORDER_GAP + (i * 20), getHeight() / 2 - BORDER_GAP - 10 * (calculateavarage(i, i - 1)),
                            BORDER_GAP + (i * 20) + 20, getHeight() / 2 - BORDER_GAP - 10 * ((calculateavarage(i + 1, i))))
                    ;
                }
            }
        }
    }

    int calculateavarage(int i, int l) {
        int val = 0;
        if (i != 0) {
            for (int j = 0; j < dataList.size(); j++) {

                val += (dataList.get(j).pd.get(l) - dataList.get(j).dd.get(l));

            }
        }
        return val / dataList.size();
    }

    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
    }


}

class ChartPanel2 extends javax.swing.JPanel {
    private static final int BORDER_GAP = 30;
    public List<Data> dataList;
    boolean correct = true;
    public int number = 0;

    public ChartPanel2(List<Data> dataList, int number) {
        initComponents();
        this.dataList = dataList;
        this.number = number;
    }


    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < getHeight(); i++) {
            int x0 = BORDER_GAP + i * 20;
            int x1 = x0;
            int y0 = getHeight() / 2 - BORDER_GAP + 3;
            int y1 = y0 - 6;
            g2.drawLine(x0, y0, x1, y1);
        }
        // create y and x
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() / 2 - BORDER_GAP, getWidth() - BORDER_GAP / 6, getHeight() / 2 - BORDER_GAP);


        g2.drawLine(BORDER_GAP, getHeight() / 2 - BORDER_GAP,
                BORDER_GAP + 20, getHeight() / 2 - BORDER_GAP - 10 * ((dataList.get(number).pd.get(0) - dataList.get(number).dd.get(0))))
        ;
        for (int i = 1; i < dataList.get(number).pd.size(); i++) {

            g2.drawLine(BORDER_GAP + (i * 20), getHeight() / 2 - BORDER_GAP - 10 * (dataList.get(number).pd.get(i-1) - dataList.get(number).dd.get(i-1)),
                    BORDER_GAP + (i * 20) + 20, getHeight() / 2 - BORDER_GAP - 10 * ((dataList.get(number).pd.get(i) - dataList.get(number).dd.get(i))))
            ;
        }

    }


    private void initComponents() {

        setLayout(new java.awt.BorderLayout());

        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
    }


}

