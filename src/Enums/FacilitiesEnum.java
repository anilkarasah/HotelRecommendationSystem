package Enums;

public class FacilitiesEnum {
    public static final String[] List;

    static {
        List = new String[] {
                "KidsAreGuest",
                "PetsAreAllowed",
                "HasWifi",
                "HasCarParking",
                "HasSpa",
                "HasGym",
                "HasRestaurant",
                "HasRoomService",
                "HasPool"
        };
    }

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
}
