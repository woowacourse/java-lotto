package lottogame;

import lottogame.domain.*;
import lottogame.domain.InvalidLottoNumberException;
import lottogame.view.InputView;
import lottogame.view.OutputView;

import java.util.Optional;

public class LottoGameMain {
    public static void main(String[] args) {
        Money money = getMoney();
        LottoTickets lottoTickets = new LottoTickets();
        int numberOfManualTicket = getNumberOfManualTicket(money);
        createLottos(lottoTickets, money, numberOfManualTicket);
        OutputView.printPurchaseResult(lottoTickets, numberOfManualTicket);

        WinningLotto winningLotto = getWinningLotto();

        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        OutputView.printLottoResult(lottoResult, lottoResult.getRateOfLotto(money));
    }

    private static int getNumberOfManualTicket(Money money) {
        String input;
        do {
            input = InputView.getNumberOfMannualTicket();
        } while (isInValidNumberOfManualInput(input, money));
        return Integer.parseInt(input);
    }

    private static boolean isInValidNumberOfManualInput(String input, Money money) {
        try {
            return money.checkInValidNumber(Integer.parseInt(input));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private static Money getMoney() {
        Optional<Money> money;

        do {
            money = createMoney(InputView.getPrice());
        } while (!money.isPresent());

        return money.get();
    }

    private static Optional<Money> createMoney(String price) {
        try {
            return Optional.of(Money.generate(Integer.parseInt(price)));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    private static void createLottos(LottoTickets lottoTickets, Money money, int numberOfManualTicket) {
        if (lottoTickets.isPossibleCreateLottoNumberOf(numberOfManualTicket)) {
            OutputView.printRequestOfManualLottoNumber();
        }
        while (lottoTickets.isPossibleCreateLottoNumberOf(numberOfManualTicket)) {
            createManualLotto(lottoTickets);
        }
        while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
            lottoTickets.createAutoLottos();
        }
    }

    private static void createManualLotto(LottoTickets lottoTickets) {
        try {
            lottoTickets.addManualLotto(InputView.getManualLottoNumber());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    private static WinningLotto getWinningLotto() {
        Optional<Lotto> lotto;
        Optional<WinningLotto> winningLotto;
        do {
            lotto = Optional.ofNullable(createMatchLotto(InputView.getWinningLotto()));
        } while (!lotto.isPresent());
        do {
            winningLotto = Optional.ofNullable(createWinningLotto(lotto.get(), InputView.getBonusNumber()));
        } while (!winningLotto.isPresent());

        return winningLotto.get();
    }

    private static Lotto createMatchLotto(String winningLotto) {
        try {
            return ManualLottoGenerator.create(winningLotto);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static WinningLotto createWinningLotto(Lotto lotto, String bonusNumber) {
        try {
            return WinningLotto.generate(lotto, Integer.parseInt(bonusNumber));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
