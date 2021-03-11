package program;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton addnewplayer;
    private JButton train;
    private JButton wykresButton;
    private JTextField Singlechart;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 1050, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        DataPanel dataPanel = new DataPanel();
        contentPane.add(dataPanel, BorderLayout.CENTER);

        addnewplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((MyTableModel) (dataPanel.getTable().getModel())).add(new Data("", "", 0, 0, 0, 0));
            }
        });
        dataPanel.add(addnewplayer, BorderLayout.NORTH);

        train.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((MyTableModel) (dataPanel.getTable().getModel())).doaTraining();

            }
        });
        dataPanel.add(train, BorderLayout.AFTER_LAST_LINE);

        wykresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(((MyTableModel) (dataPanel.getTable().getModel())).getDataList());
                if (((MyTableModel) dataPanel.getTable().getModel()).getDataList().size() != 0) {
                    JFrame jf = new JFrame();
                    jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    jf.getContentPane().add(new ChartPanel(((MyTableModel) dataPanel.getTable().getModel()).getDataList()));
                    jf.pack();
                    jf.setVisible(true);
                }
            }
        });
        dataPanel.add(wykresButton, BorderLayout.WEST);
        Singlechart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice=Integer.parseInt(Singlechart.getText())-1;
                System.out.println(((MyTableModel) (dataPanel.getTable().getModel())).getDataList());
                if (((MyTableModel) dataPanel.getTable().getModel()).getDataList().size() != 0) {
                    JFrame jf = new JFrame();
                    jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    jf.getContentPane().add(new ChartPanel2(((MyTableModel) dataPanel.getTable().getModel()).getDataList(), choice));
                    jf.pack();
                    jf.setVisible(true);
                }

            }
        });
        dataPanel.add(Singlechart, BorderLayout.EAST);

    }


}
