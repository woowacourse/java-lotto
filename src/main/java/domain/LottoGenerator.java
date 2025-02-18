package domain;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import java.util.ArrayList;
import java.util.List;
import utils.InputParser;
import utils.RandomNumber;

public class LottoGenerator {

    private LottoGenerator() {

    }

    public static List<Lotto> createLottoBundleForQuantity(int quantity) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Lotto lotto = createLotto();
            lottoBundle.add(lotto);
        }
        return lottoBundle;
    }

    public static Lotto createWinningLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return new Lotto(parsedNumbers);
    }

    public static WinningInfo createWinningInfo(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }


    private static Lotto createLotto() {
        List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
        return new Lotto(numbers);
    }
}
