package model;


import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoNumbersGenerator {
    private static final int LOTTO_SIZE = 6;

    public List<Integer> pickSixNumbers(List<Integer> shuffledNumbers) {
        return shuffledNumbers.stream()
                .limit(LOTTO_SIZE)
                .collect(Collectors.toList());
    }
}
