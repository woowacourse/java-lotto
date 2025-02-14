package lotto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    private static final Random random = new Random();

    private RandomNumberGenerator() {
    }

    public static List<LottoNumber> generate() {
//        ArrayList<LottoNumber> randomNumbers = new ArrayList<>();
//        for (int count = 0; count < Lotto.LOTTO_SIZE; count++) {
//            randomNumbers.add(new LottoNumber(random.nextInt(Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER)));
//        }
        List<Integer> randomNumbers = generateNotDuplicateSixNumbers();
        return randomNumbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private static List<Integer> generateNotDuplicateSixNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int count = 0; count < Lotto.LOTTO_SIZE; count++) {
            randomNumbers.add(random.nextInt(Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER));
        }
        HashSet<Integer> uniqueNumbers = new HashSet<>(randomNumbers);
        if (uniqueNumbers.size() != Lotto.LOTTO_SIZE) {
            return generateNotDuplicateSixNumbers();
        }
        return randomNumbers;
    }
}
