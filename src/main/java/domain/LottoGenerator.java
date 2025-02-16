package domain;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;
import static error.ErrorMessage.INVALID_LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;
import utils.InputParser;
import utils.RandomNumber;

public class LottoGenerator {
    private static final int TICKET_PRICE = 1_000;

    public int purchaseLottoByAmount(int amount) {
        validateAmount(amount);
        return calculateQuantity(amount);
    }

    public List<Lotto> createLottoBundleForQuantity(int quantity) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Lotto lotto = createLotto();
            lottoBundle.add(lotto);
        }
        return lottoBundle;
    }

    public Lotto createWinningLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return new Lotto(parsedNumbers);
    }

    public WinningInfo createWinningInfo(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }

    private void validateAmount(int amount) {
        if (amount % TICKET_PRICE == 0) {
            return;
        }
        throw new IllegalArgumentException(INVALID_LOTTO_PRICE.getMessage());
    }

    private int calculateQuantity(int amount) {
        return amount / TICKET_PRICE;
    }

    private Lotto createLotto() {
        List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
        return new Lotto(numbers);
    }
}
