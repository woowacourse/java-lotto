package model;

import constant.Constants;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoNumbers {
    private final List<Integer> numbers;

    public LottoNumbers() {
        this.numbers = generateLottoNumbers();
    }

    private List<Integer> generateLottoNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < Constants.LOTTO_NUMBER_COUNT) {
            Random random = new Random();
            int randomNumber = random.nextInt(Constants.MAXIMUM_LOTTO_NUMBER) + 1;
            lottoNumbers.add(randomNumber);
        }
        return lottoNumbers.stream().toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
