package model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;
    private final List<Integer> numbers;

    public LottoNumbers() {
        this.numbers = generateLottoNumbers();
    }

    public LottoNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    private List<Integer> generateLottoNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();
        while (lottoNumbers.size() < LOTTO_NUMBER_COUNT) {
            Random random = new Random();
            int randomNumber = random.nextInt(MAXIMUM_LOTTO_NUMBER) + 1;
            lottoNumbers.add(randomNumber);
        }
        return lottoNumbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer countMatchNumber(LottoNumbers lottoNumbers) {
        List<Integer> matchList = lottoNumbers.getNumbers().stream()
                .filter(lottoNumber -> numbers.stream().anyMatch((number) -> number == lottoNumber))
                .toList();
        return matchList.size();
    }

    public Boolean bonusMatch(Integer bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
