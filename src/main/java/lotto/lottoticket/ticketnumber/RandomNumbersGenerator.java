package lotto.lottoticket.ticketnumber;

import lotto.lottoticket.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumbersGenerator implements NumbersGenerator {
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 45;
    public static final int NUMBER_COUNT_IN_LOTTO = 6;

    @Override
    public List<LottoNumber> generate() {
        final List<Integer> numbers = IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(numbers);
        return makeLottoNumbers(makeAscendingNumbers(numbers));
    }

    private List<Integer> makeAscendingNumbers(List<Integer> numbers) {
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT_IN_LOTTO; i++) {
            randomNumbers.add(numbers.get(i));
        }
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    private List<LottoNumber> makeLottoNumbers(List<Integer> randomNumbers) {
        return randomNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());
    }
}
