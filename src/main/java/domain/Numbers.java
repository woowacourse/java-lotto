package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Numbers {
    private final List<LottoNumber> numbers;

    public Numbers(List<Integer> numbers) {
        sortingNumbers(numbers);
        List<LottoNumber> sortedNumbers = new ArrayList<>();
        for(int number : numbers) {
            sortedNumbers.add(new LottoNumber(number));
        }
        this.numbers = sortedNumbers;
    }

    private void sortingNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    public List<Integer> getNumbers() {
        List<Integer> numbersList = new ArrayList<>();
        for(LottoNumber lottoNumber : numbers) {
                numbersList.add(lottoNumber.getNum());
        }
        return numbersList;
    }
}
