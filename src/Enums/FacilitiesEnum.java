package Enums;

import java.util.ArrayList;

public class FacilitiesEnum {
    public static final String[] List = new String[] {
	        "Çocuk Misafir",
	        "Evcil Hayvan Girebilir",
	        "Kablosuz İnternet",
	        "Otopark",
	        "Spa / Hamam",
	        "Spor Salonu",
	        "Restoran",
	        "Oda Servisi",
	        "Açık / Kapalı Havuz"
	    };

    public static Integer getFacilityIndex(String facility) {
        for (int i = 0; i < List.length; i++) {
            if (facility.equals(List[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String getFacilityName(int index) {
        return List[index];
    }
    
    public static ArrayList<String> getFacilitiesList(boolean[] facilityFlags) {
    	ArrayList<String> result = new ArrayList<>();
    	
    	for (int i = 0; i < facilityFlags.length; i++)
    		if (facilityFlags[i]) result.add(List[i]);
    	
    	return result;
    }
}
