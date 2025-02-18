package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static domain.LottoInformation.LOTTO_COUNT;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        sortingNumbers(numbers);
        List<LottoNumber> sortedNumbers = new ArrayList<>();
        for (int number : numbers) {
            sortedNumbers.add(new LottoNumber(number));
        }
        this.lottoNumbers = sortedNumbers;
    }

    public List<Integer> getNumbers() {
        List<Integer> numbersList = new ArrayList<>();
        for (LottoNumber lottoNumber : lottoNumbers) {
            numbersList.add(lottoNumber.getNum());
        }
        return numbersList;
    }

    private void validate(List<Integer> numbers) {
        validateDistinct(numbers);
        validateSize(numbers);
    }

    private void validateDistinct(List<Integer> numbers) {
        if (numbers.stream().distinct().count() == LOTTO_COUNT) {
            return;
        }
        throw new IllegalArgumentException(String.format("로또 번호는 %d자리여야 합니다.", LOTTO_COUNT));
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() == LOTTO_COUNT) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private void sortingNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

}
