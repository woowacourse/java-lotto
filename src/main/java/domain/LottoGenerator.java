package domain;

import static domain.LottoRules.WINNING_NUMBERS_REQUIRED;

import java.util.ArrayList;
import java.util.List;
import utils.InputParser;
import utils.RandomNumber;

public class LottoGenerator {
    public List<Lotto> createLottoBundleForTicket(TicketMachine ticketMachine) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < ticketMachine.getQuantity(); i++) {
            Lotto lotto = createLotto();
            lottoBundle.add(lotto);
        }

        return lottoBundle;
    }

    private Lotto createLotto() {
        List<Integer> numbers = RandomNumber.generateNumbers(WINNING_NUMBERS_REQUIRED);
        return Lotto.from(numbers);
    }

    public Lotto createWinningLotto(String winningNumbers) {
        List<Integer> parsedNumbers = InputParser.parseAndCreateWinningNumbers(winningNumbers);
        return Lotto.from(parsedNumbers);
    }

    public WinningInfo createWinningInfo(Lotto winningNumbers, int bonusNumber) {
        return WinningInfo.of(winningNumbers, bonusNumber);
    }
}
