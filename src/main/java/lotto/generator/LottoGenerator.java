package lotto.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.view.OutputView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static Random random;
    private static List<LottoNumber> totalRandomNumbers;

    static {
        random = new Random();
        totalRandomNumbers = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static Lotto create() {
        Collections.shuffle(totalRandomNumbers);
        Set<LottoNumber> randomNumbers = new TreeSet<>();
        int length = Lotto.LOTTO_NUMBERS_SIZE;
        for (int i = 0; i < length; i++) {
            randomNumbers.add(totalRandomNumbers.get(i));
        }
        return new Lotto(randomNumbers);
    }

    public static Lotto create(String lottoNumbersInput) {
        String[] splitedLottoNumbers = getSplitedLottoNumbers(lottoNumbersInput);
        Set<LottoNumber> lottoNumbers = createLottoNumbers(splitedLottoNumbers);
        return new Lotto(lottoNumbers);
    }

    private static String[] getSplitedLottoNumbers(String lottoNumbers) {
        return lottoNumbers.split(OutputView.SPLIT_DELIMETER);
    }

    private static Set<LottoNumber> createLottoNumbers(String[] splitedLottoNumbers) {
        return Arrays.stream(splitedLottoNumbers)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
