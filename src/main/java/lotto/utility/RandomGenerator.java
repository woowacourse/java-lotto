package lotto.utility;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {
    private void addNumberToList(List<Integer> list, int number) {
        if (!list.contains(number)) {
            list.add(number);
        }
    }

    public List<Integer> generateUniqueRandomNumbers(int maxNumber) {
        List<Integer> randoms = new ArrayList<>();

        while (randoms.size() < 6) {
            int nextRandomNumber = (int) Math.ceil(Math.random() * maxNumber);
            addNumberToList(randoms, nextRandomNumber);
        }

        return randoms;
    }
}
