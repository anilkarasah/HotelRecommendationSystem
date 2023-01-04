package Enums;

import java.util.ArrayList;
import java.util.HashMap;

public class DistrictsEnum {
	public static HashMap<String, ArrayList<String>> cityMap;
	
	public static void setDistrictsEnum(HashMap<String, ArrayList<String>> cityDistrictMap) {
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
}
