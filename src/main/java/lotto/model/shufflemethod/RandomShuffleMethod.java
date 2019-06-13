package lotto.model.shufflemethod;

import lotto.model.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleMethod implements ShuffleMethod {

    @Override
    public String shuffle(List<Integer> all45Numbers) {
        String returnString = "";
        List<Integer> all45NumbersCopied = new ArrayList<>(all45Numbers);
        Collections.shuffle(all45NumbersCopied);
        List<Integer> result = all45NumbersCopied.subList(0,6);
        for (Integer currentInteger : result) {
            returnString += "" + currentInteger;
            returnString += ",";
        }
        return returnString;

    }
}
