package lotto.model.shufflemethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleMethod implements ShuffleMethod {

    @Override
    public List<Integer> shuffle(List<Integer> all45Numbers) {
        String returnString = "";
        List<Integer> all45NumbersCopied = new ArrayList<>(all45Numbers);
        Collections.shuffle(all45NumbersCopied);
        //        for (Integer currentInteger : result) {
//            returnString += "" + currentInteger;
//            returnString += ",";
//        }
        return all45NumbersCopied.subList(0, 6);

    }
}
