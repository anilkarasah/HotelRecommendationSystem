package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Enums.DistrictsEnum;
import Models.Hotel;
import Models.HotelRecommendation;
import Models.HotelReview;
import Models.User;
import Services.CSVService;
import Services.RecommendationService;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MainMenu extends JFrame {
	boolean rsflag = true;
	ArrayList<HotelReview> review_list = new ArrayList<>();
	JList<String> liste = new JList<>(l1);
	static User user;
	static int oneri_flag = 0;
	static float basarı_oranı;
	static RecommendationService rs;
	static ArrayList<String> cities;
	static ArrayList<String> districts;
	static ArrayList<Hotel> hotelList;
	static ArrayList<Hotel> selected_hotelList = new ArrayList<>();
	static ArrayList<HotelRecommendation> recommendations;
	ArrayList<String> arr1 = new ArrayList<>();
	DefaultComboBoxModel<String> cb2 = new DefaultComboBoxModel<>();
	static int otel_counter = 0;
	static String selected_city = null;
	static String selected_district = null;
	static DefaultListModel<String> l1 = new DefaultListModel<>();
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
		try{
			CSVService csvService = new CSVService("./hotels.csv");
			hotelList = csvService.ReadAllValues();
		}catch(Exception e) {
			e.printStackTrace();
		}
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
		lblEmirhanPaksoy.setText("EMİRHAN PAKSOY - 19011048 | MEHMET ANIL KARAŞAH - 19011036");
		lblEmirhanPaksoy.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmirhanPaksoy.setBounds(10, 475, 417, 25);
		panel.add(lblEmirhanPaksoy);
		
		cities = DistrictsEnum.getCityList();
		JComboBox comboBox = new JComboBox(cities.toArray());	
		//ŞEHİR KUTUCUĞU SEÇİLDİĞİNDEKİ İŞLEMLER
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected_city = comboBox.getSelectedItem().toString();
				districts = DistrictsEnum.getDistrictsOfCity(selected_city);
				cb2.removeAllElements();
				cb2.addElement("Tümü");
				for(String i : districts) {
					cb2.addElement(i);
				}
				
			}
		});
		
		
		
		comboBox.setBounds(20, 119, 100, 25);
		panel.add(comboBox);
		System.out.println();
		
		JComboBox<String> comboBox_1 = new JComboBox<>(cb2);
		//SEMT KUTUCUĞU SEÇİLDİĞİNDEKİ İŞLEMLER
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem() == null) {
					selected_district = null;
				}
				else {
					selected_district = comboBox_1.getSelectedItem().toString();
				}				
			}
		});
		comboBox_1.setBounds(130, 119, 100, 25);
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
		//OTEL ÖNER BUTONUNA TIKLANDIĞINDA YAPILAN İŞLEMLER
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(review_list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Daha önce bir otele gitmemişsiniz !!!");
				}
				else {
					oneri_flag = 1;
					user = new User(review_list);
					if(rsflag) {
						rs = new RecommendationService(user, hotelList);
						rsflag = false;
					}
					else {
						rs.setUser(user);
					}
					
					rs.recommendHotels();
					recommendations = rs.getRecommendedHotels();
					l1.removeAllElements();
					l1.addElement("Önerilen Oteller:");
					int i=0;
					int j=0;
					selected_hotelList.clear();
					selected_hotelList.add(null);
					//LİSTEYE 15 TANE ÖNERİLEN OTEL EKLENİR.
					while(j<15 && recommendations.get(i) != null) {
						if(recommendations.get(i).getHotel().province.equals(comboBox.getSelectedItem())) {
							l1.addElement(recommendations.get(i).getHotel().toString());
							selected_hotelList.add(recommendations.get(i).getHotel());
							j++;
						}					
						i++;
					}
				}									
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnNewButton.setBounds(20, 247, 117, 25);
		panel.add(btnNewButton);
		
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		//LİSTELE BUTONUNA TIKLANDIĞINDA GERÇEKLEŞTİRİLEN İŞLEMLER
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				l1.removeAllElements();
				if(selected_city == null) {
					JOptionPane.showMessageDialog(null, "Şehir seçiniz !!!");
				}
				else {
					if(selected_district == null) {
						JOptionPane.showMessageDialog(null, "Semt seçiniz !!!");
					}
					else {
						oneri_flag = 0;
						if(selected_district.equals("Tümü")) {
							//BELİRTİLEN ŞEHİRDEKİ BÜTÜN OTELLERİ ÇEKME
							selected_hotelList = DistrictsEnum.getHotelsOfCity(selected_city);
						}
						else {
							//BELİRTİLEN SEMTTEKİ OTELLERİ ÇEKME
							selected_hotelList = DistrictsEnum.getHotelsOfDistrict(selected_district);
						}				
						if(selected_hotelList == null) {
							JOptionPane.showMessageDialog(null, "Bu konumda otel bulunmamaktadır !!!");
						}
						else {
							//ÇEKİLEN OTELLERİ LİSTELEME
							for(Hotel h : selected_hotelList) {
								l1.addElement(h.toString());
							}
						}
					}		
				}									
			}
		});
		btnNewButton_1.setBounds(20, 174, 117, 25);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1_1_2 = new JLabel();
		lblNewLabel_1_1_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(664, 475, 84, 25);
		panel.add(lblNewLabel_1_1_2);
		
		JButton btnRezervasyonYap = new JButton("Değerlendir");
		//DEĞERLENDİR BUTONUNA TIKLANDIĞINDA YAPILAN İŞLEMLER
		btnRezervasyonYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				if(index != -1) {
					//OYLAMANIN YAPILDIĞI SAYFA AÇILIR
					OylamaYap oy = new OylamaYap(selected_hotelList.get(index), review_list,basarı_oranı,index,rs,oneri_flag,lblNewLabel_1_1_2);
					oy.setVisible(true);
					
					
				}			
			}
		});
		btnRezervasyonYap.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnRezervasyonYap.setBounds(20, 210, 117, 25);
		panel.add(btnRezervasyonYap);
		
		liste.setFont(new Font("Consolas", Font.PLAIN, 12));
		liste.setBounds(281, 98, 587, 338);
		liste.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 98, 587, 338);
		panel.add(scrollPane);
		scrollPane.setViewportView(liste);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Başarı Oranı :");
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(525, 475, 141, 25);
		panel.add(lblNewLabel_1_1_1);
		
		
		
	}
}