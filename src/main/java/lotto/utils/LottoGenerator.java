package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PaidPrice;
import lotto.domain.WinningLotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator {
    private static final String SPLIT_DELIMETER = ",";
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final String INCORRECT_LOTTO_NUMBER_MSG = "로또 번호는 6개입니다.";
    private static RandomGenerator randomGenerator = new RandomGenerator();

    public static List<Lotto> createLottos(PaidPrice paidPrice) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < paidPrice.getLottoCount(); i++) {
            lottos.add(new Lotto(createRandomLottoNumbers()));
        }
        return lottos;
    }

    public static WinningLotto createWinningLottoByUserInput(String winningNumbersInput,
                                                             String bonusNumberInput) {
        String[] splitedWinningNumbersInput = getSplitedWinningLottoNumbers(winningNumbersInput);
        Set<LottoNumber> winningLottoNumbers = createLottoNumbersByUserInput(splitedWinningNumbersInput);
        return new WinningLotto(winningLottoNumbers, new LottoNumber(bonusNumberInput));
    }

    public static Set<LottoNumber> createRandomLottoNumbers() {
        List<Integer> randomNumbers = randomGenerator.getRandomNumbers();
        Set<LottoNumber> lottoNumbers = new TreeSet<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(randomNumbers.get(i)));
        }
        return lottoNumbers;
    }

    public static Set<LottoNumber> createLottoNumbersByUserInput(String[] splitedWinningLottoNumbers) {
        if (splitedWinningLottoNumbers.length != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(INCORRECT_LOTTO_NUMBER_MSG);
        }

        Set<LottoNumber> lottoNumbers = Arrays.stream(splitedWinningLottoNumbers)
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
        return lottoNumbers;
    }

    public static String[] getSplitedWinningLottoNumbers(String lottoNumbersInput) {
        return lottoNumbersInput.split(SPLIT_DELIMETER);
    }
}
