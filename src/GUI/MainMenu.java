package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private final JButton btnNewButton_1 = new JButton("Listele");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 912, 523);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Otel Öneri Sistemi");
		lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 461, 88);
		panel.add(lblNewLabel);
		
		JLabel lblEmirhanPaksoy = new JLabel();
		lblEmirhanPaksoy.setText("EMİRHAN PAKSOY  --> 19011048  MEHMET ANIL KARAŞAH  --> 19011036");
		lblEmirhanPaksoy.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmirhanPaksoy.setBounds(10, 475, 417, 25);
		panel.add(lblEmirhanPaksoy);
		String arr[] = {"Ankara","Antalya","Bursa","İzmir","Muğla"};
		JComboBox comboBox = new JComboBox(arr);
		comboBox.setBounds(20, 119, 72, 25);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(new Object[]{});
		comboBox_1.setBounds(127, 120, 72, 25);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Şehir:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(20, 83, 84, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Semt:");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(127, 83, 84, 25);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Otel Öner");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNewButton.setBounds(20, 334, 110, 25);
		panel.add(btnNewButton);
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(20, 174, 117, 25);
		panel.add(btnNewButton_1);
		
		
	}
}