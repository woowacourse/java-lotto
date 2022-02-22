package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoNumbersGenerator {
    private static final int LOTTO_SIZE = 6;

    public List<Integer> pickSixNumbers() {
        List<Integer> shuffledNumbers = shuffleLottoNumbers();
        return shuffledNumbers.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toList());
    }

    private List<Integer> shuffleLottoNumbers() {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        randomNumbers.addAll(LottoNumbers.getLottoNumbers());
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }
}

