package be.byteworx.challenge8;

import java.util.List;

public class ChallengeEight {
    public int longestContinuingNumberChain(List<Integer> numbers) {
        //TODO: return the max number of consecutive numbers
        int number = 0;

        for(int i=0; i<numbers.size();){
            int temp = numberStreak(numbers, i, 1);
            i+=temp;

            if(temp > number) {
                number = temp;
            }
        }

        return number;
    }

    private int numberStreak(List<Integer> numbers, int index, int number) {
        if(numbers.size()>index+1 && numbers.get(index)+1 == numbers.get(index+1)) {
            return numberStreak(numbers, index+1, number+1);
        } else {
            return number;
        }
    }
}