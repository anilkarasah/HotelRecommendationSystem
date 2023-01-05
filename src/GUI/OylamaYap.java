package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import Enums.FacilitiesEnum;
import Models.Hotel;
import Models.HotelReview;
import Models.UserReview;
import Services.RecommendationService;

import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OylamaYap extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OylamaYap frame = new OylamaYap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public OylamaYap(Hotel hotel, ArrayList<HotelReview> reviewList,float basarı_oranı,int index,RecommendationService rs,int flag,JLabel label) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 657, 435);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Otel Adı:");
		lblNewLabel_1.setBounds(10, 36, 93, 32);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Adres:");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 79, 93, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fiyat:");
		lblNewLabel_1_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 122, 93, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Olanaklar:");
		lblNewLabel_1_3.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(10, 165, 123, 32);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel(hotel.name);
		lblNewLabel_1_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(113, 36, 287, 32);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel(hotel.address);
		lblNewLabel_1_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_5.setBounds(85, 79, 534, 32);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel(String.format("%.2f", hotel.price));
		lblNewLabel_1_6.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_6.setBounds(76, 122, 93, 32);
		panel.add(lblNewLabel_1_6);
		
		ArrayList<String> facilities = FacilitiesEnum.getFacilitiesList(hotel.facilities);
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<html>");
		for (String facility : facilities)
			strBuilder.append("<p>> ").append(facility).append("</p>");
		String facilitiesString = strBuilder.append("</html>").toString();
		
		JLabel lblNewLabel_1_7 = new JLabel(facilitiesString);
		lblNewLabel_1_7.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_7.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_7.setBounds(10, 209, 321, 215);
		panel.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Skor:");
		lblNewLabel_1_8.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8.setBounds(511, 36, 63, 32);
		panel.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel(String.format("%2.1f", hotel.avgScore));
		lblNewLabel_1_9.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_9.setBounds(584, 36, 63, 32);
		panel.add(lblNewLabel_1_9);
		
		JLabel lblNewLabel_1_8_1 = new JLabel();
		JSlider slider = new JSlider(0,100,50);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblNewLabel_1_8_1.setText(String.format("%2.1f", (float)slider.getValue()/10));
			}
		});
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(25);
		slider.setBounds(341, 228, 306, 53);
		panel.add(slider);
		
		
		lblNewLabel_1_8_1.setText(String.format("%2.1f", (float)slider.getValue()/10));
		lblNewLabel_1_8_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_8_1.setBounds(479, 204, 73, 32);
		panel.add(lblNewLabel_1_8_1);
		
		JButton btnNewButton = new JButton("Değerlendir");
		HotelReview review = new HotelReview(null,5);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				review.setHotel(hotel);
				review.setScore((float)slider.getValue()/10);		
				reviewList.add(review);
				if(flag == 1) {
					rs.addUserReview(new UserReview(index - 1, review.getScore()));
					rs.calculateMeanSquaredError();
					label.setText(String.format("%.2f", 100-rs.getMeanSquaredError()));
				}
						
				dispose();
			}
		});
		btnNewButton.setBounds(341, 292, 203, 31);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Geri Dön");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnNewButton_1.setBounds(554, 292, 93, 32);
		panel.add(btnNewButton_1);
	}
	
}
