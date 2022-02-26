package model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomLottoNumbersGenerator {
    private static final int LOTTO_SIZE = 6;

    public static Set<Integer> pickSixNumbers() {
        List<Integer> shuffledNumbers = shuffleLottoNumbers();
        return shuffledNumbers.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toSet());
    }

    private static List<Integer> shuffleLottoNumbers() {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        randomNumbers.addAll(LottoNumbers.getLottoNumbers());
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }
}

