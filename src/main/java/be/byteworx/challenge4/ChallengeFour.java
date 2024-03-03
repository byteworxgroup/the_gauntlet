package be.byteworx.challenge4;
public class ChallengeFour {

    public String drinkingSchedule(int day) {
        //TODO: print out the drinking schedule (BUZZED, FIZZY, FIZZY BUZZED, STAYING IN) for the year
        String value = "";

        if(day % 3 == 0 && day % 5 == 0){
            return "FIZZY BUZZED";
        } else if (day % 3 == 0){
            return "BUZZED";
        } else if (day % 5 == 0){
            return "FIZZY";
        } else {
            return "STAYING IN";
        }
    }
}
