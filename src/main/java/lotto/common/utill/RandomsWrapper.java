package lotto.common.utill;

import java.util.ArrayList;
import java.util.List;

public final class RandomsWrapper {

    public static List<Integer> getRandomNumbers() {
        List<Integer> list = new ArrayList<>();
        while (list.size() < 6) {
            int randomInt = (int)(Math.random() * 45) + 1;
            if (!list.contains(randomInt)) {
                list.add(randomInt);
            }
        }

        return list;
    }
}
