package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PaidPrice;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
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

    public static WinningLotto createWinningLottoByUserInput (String winningNumbersInput,
                                                              String bonusNumberInput) {
        List<LottoNumber> winningLottoNumbers = createLottoNumbersByUserInput(winningNumbersInput);
        return new WinningLotto(winningLottoNumbers, new LottoNumber(bonusNumberInput));
    }

    public static List<LottoNumber> createRandomLottoNumbers() {
        List<Integer> randomNumbers = randomGenerator.getRandomNumbers();
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(randomNumbers.get(i)));
        }
        return lottoNumbers;
    }

    public static List<LottoNumber> createLottoNumbersByUserInput(String lottoNumbersInput) {
        String[] splitedWinningLottoNumbers = lottoNumbersInput.split(",");
        if (splitedWinningLottoNumbers.length != LOTTO_NUMBER_SIZE)
            throw new IllegalArgumentException(INCORRECT_LOTTO_NUMBER_MSG);

        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(new LottoNumber(splitedWinningLottoNumbers[i]));
        }
        return lottoNumbers;
    }
}
