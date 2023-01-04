package Enums;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Hotel;

public class DistrictsEnum {
	private static ArrayList<String> cityList;
	private static HashMap<String, ArrayList<String>> cityMap;
	private static HashMap<String, ArrayList<Hotel>> districtMap;
	
	public static void setCityList(ArrayList<String> citiesList) {
		cityList = citiesList;
	}
	
	public static ArrayList<String> getCityList() {
		return cityList;
	}
	
	public static void setCityMap(HashMap<String, ArrayList<String>> cityDistrictMap) {
		cityMap = cityDistrictMap;
	}
	
	public static ArrayList<String> addCity(String cityName) {
		return cityMap.put(cityName, null);
	}
	
	public static boolean addDistrictsToCity(String cityName, ArrayList<String> districtList) {
		ArrayList<String> currentDistrictList = cityMap.get(cityName);
		if (currentDistrictList == null) {
			currentDistrictList = addCity(cityName);
		}
		
		return currentDistrictList.addAll(districtList);
	}
	
	public static ArrayList<String> getDistrictsOfCity(String cityName) {
		return cityMap.get(cityName);
	}
	
	public static void setDistrictMap(HashMap<String, ArrayList<Hotel>> districtHotelMap) {
		districtMap = districtHotelMap;
	}
	
	public static ArrayList<Hotel> addDistrict(String districtName) {
		return districtMap.put(districtName, null);
	}
	
	public static boolean addHotelsToDistrict(String districtName, ArrayList<Hotel> hotelsList) {
		ArrayList<Hotel> currentHotelsList = districtMap.get(districtName);
		if (currentHotelsList == null) {
			currentHotelsList = addDistrict(districtName);
		}
		
		return currentHotelsList.addAll(hotelsList);
	}
	
	public static ArrayList<Hotel> getHotelsOfDistrict(String districtName) {
		return districtMap.get(districtName);
	}
}
