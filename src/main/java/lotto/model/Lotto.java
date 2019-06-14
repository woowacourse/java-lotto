package lotto.model;

import lotto.model.exceptions.IllegalNumberCombinationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Lotto {
    public static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String SEPERATOR = ",";

    private List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers){
        checkDuplication(numbers);
        checkLottoLength(numbers);
        this.numbers = LottoNumber.convertNumbersToLottoNumbers(numbers);
    }

    public Lotto(String inputNumbers) {
        this(convert(inputNumbers));
    }

    private static List<Integer> convert(String inputNumbers) {
        List<String> inputs = new ArrayList<>(Arrays.asList(inputNumbers.split(SEPERATOR)));
        List<Integer> lottoNumbers = new ArrayList<>();

        inputs.forEach(input -> lottoNumbers.add(Integer.parseInt(input)));
        return lottoNumbers;
    }

    private void checkDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalNumberCombinationException();
        }
    }

    private void checkLottoLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalNumberCombinationException();
        }
    }

    public int countMatchLottoNumber(Lotto lotto) {
        return (int) numbers.stream()
                .filter(number -> lotto.containsNumber(number))
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }


    public Prize getRank(Lotto winningLotto, LottoNumber bonusNumber) {
        return Prize.getPrizeRank(countMatchLottoNumber(winningLotto), containsNumber(bonusNumber));
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
