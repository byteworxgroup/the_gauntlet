package be.byteworx.challenge5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChallengeFive {

    public List<Integer> fibonacci(int upperLimit) {
        //TODO: return a list of fibonacci numbers smaller than the upperLimit
        List<Integer> list = new ArrayList<>();
        return fibon(list, null, 0, upperLimit);
    }

    private List<Integer> fibon(List<Integer> list, Integer previous, Integer next, Integer upperLimit) {

        if(next < upperLimit) {
            list.add(next);
            return fibon(list, previous == null ? 0 : next, previous == null ? 1 : next+previous, upperLimit);
        } else {
            return list;
        }
    }
}
