package lotto.utility;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumberGenerator {

    public List<Integer> generateNumbers(int maxNumber, int count) {
        return generateNumbers(() -> (int) Math.ceil(Math.random() * maxNumber), count);
    }

    public List<Integer> generateNumbers(RandomNumberGenerator randomNumberGenerator, int count) {
        Set<Integer> randoms = new HashSet<>();

        while (randoms.size() < count) {
            int nextRandomNumber = randomNumberGenerator.generate();
            randoms.add(nextRandomNumber);
        }

        return randoms.stream()
                .toList();
    }
}
