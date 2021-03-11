package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import chart.ChartPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Data");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		DataPanel dataPanel = new DataPanel();
		contentPane.add(dataPanel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MyTableModel)(dataPanel.getTable().getModel())).add(new Data("","",0));
				//((MyTableModel)(dataPanel.getTable().getModel())).getDataList().add(new Data("","",0));
				//((MyTableModel)(dataPanel.getTable().getModel())).fireTableDataChanged();
			}
		});
		dataPanel.add(btnNewButton, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Print debug");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(((MyTableModel)(dataPanel.getTable().getModel())).getDataList());
			}
		});
		dataPanel.add(btnNewButton_1, BorderLayout.EAST);
		
		JButton btnNewButton_2 = new JButton("Show chart");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            JFrame jf = new JFrame();			
	            jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		        jf.getContentPane().add(new ChartPanel(1,-2,1));
		        jf.pack();
			    jf.setVisible(true);
			}
		});
		dataPanel.add(btnNewButton_2, BorderLayout.WEST);
	}

}
