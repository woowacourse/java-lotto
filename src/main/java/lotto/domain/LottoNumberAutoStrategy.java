package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberAutoStrategy implements LottoNumberStrategy {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<List<LottoNumber>> lottoNumbers;
    private static final List<LottoNumber> lottoNumberCandidate = new ArrayList<>();

    static {
        for (int i = LottoNumber.MINIMUM_RANGE; i <= LottoNumber.MAXIMUM_RANGE; i++) {
            lottoNumberCandidate.add(LottoNumber.valueOf(i));
        }
    }

    private LottoNumberAutoStrategy(List<List<LottoNumber>> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static LottoNumberAutoStrategy generateAutoLottoNumber(int count) {
        return new LottoNumberAutoStrategy(createLottoNumbers(count));
    }

    private static List<List<LottoNumber>> createLottoNumbers(int count) {
        List<List<LottoNumber>> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoNumbers.add(createLottoNumbers());
        }
        return lottoNumbers;
    }

    private static List<LottoNumber> createLottoNumbers() {
        List<LottoNumber> numbers = new ArrayList<>(lottoNumberCandidate);
        shuffleNumbers(numbers);
        numbers = numbers.subList(0, LOTTO_NUMBER_SIZE);
        Collections.sort(numbers);
        return numbers;
    }

    private static void shuffleNumbers(List<LottoNumber> numbers) {
        Collections.shuffle(numbers);
    }

    @Override
    public List<List<LottoNumber>> getLottoNumbers() {
        return new ArrayList<>(lottoNumbers);
    }
}
