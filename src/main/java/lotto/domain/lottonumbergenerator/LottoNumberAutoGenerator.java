package lotto.domain.lottonumbergenerator;

import static lotto.domain.LottoNumber.MAXIMUM_RANGE;
import static lotto.domain.LottoNumber.MINIMUM_RANGE;
import static lotto.domain.LottoTicket.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;

public class LottoNumberAutoGenerator implements LottoNumberGenerator {

    private static final List<Integer> numbers;

    static {
        numbers = IntStream.rangeClosed(MINIMUM_RANGE, MAXIMUM_RANGE)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<List<LottoNumber>> getLottoNumbersBy(int count) {
        List<List<LottoNumber>> allLottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            allLottoNumbers.add(getRandomLottoNumbers());
        }
        return allLottoNumbers;
    }

    private List<LottoNumber> getRandomLottoNumbers() {
        List<Integer> numbers = createRandomNumbers();
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .sorted(LottoNumber::compareTo)
                .collect(Collectors.toList());
    }

    private List<Integer> createRandomNumbers() {
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBER_SIZE);
    }
}
