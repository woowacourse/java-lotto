package lotto.domain.lottonumbergenerator;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;

public class LottoNumberAutoGenerator implements LottoNumberGenerator {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 45;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumberAutoGenerator() {
        lottoNumbers = createLottoNumbers();
    }

    @Override
    public List<List<LottoNumber>> getLottoNumbersBy(int count) {
        List<List<LottoNumber>> allLottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            allLottoNumbers.add(getRandomLottoNumbers());
        }
        return allLottoNumbers;
    }

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(MINIMUM_RANGE, MAXIMUM_RANGE)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private List<LottoNumber> getRandomLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> numbers = lottoNumbers.subList(0, LOTTO_NUMBER_SIZE);
        Collections.sort(numbers);
        return new ArrayList<>(numbers);
    }
}
