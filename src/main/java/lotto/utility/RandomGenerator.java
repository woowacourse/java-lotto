package lotto.utility;

import java.util.ArrayList;
import java.util.List;

public class RandomGenerator {

    public List<Integer> generateUniqueRandomNumbers(int maxNumber, int count) {
        List<Integer> randoms = new ArrayList<>();

        while (randoms.size() < count) {
            int nextRandomNumber = (int) Math.ceil(Math.random() * maxNumber);
            addNumberToList(randoms, nextRandomNumber);
        }

        return randoms;
    }

    private void addNumberToList(List<Integer> list, int number) {
        if (!list.contains(number)) {
            list.add(number);
        }
    }
}
