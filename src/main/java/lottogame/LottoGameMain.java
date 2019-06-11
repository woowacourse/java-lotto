package lottogame;

import lottogame.domain.*;
import lottogame.lottogameexception.InvalidLottoNumberException;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        Money money = getMoney();
        LottoTickets lottoTickets = new LottoTickets();
        int numberOfManualTicket = getNumberOfManualTicket(money);
        createLottos(lottoTickets, money, numberOfManualTicket);
        OutputView.printPurchaseResult(lottoTickets, numberOfManualTicket);

        WinningLotto winningLotto = createWinningLotto();

        LottoResult lottoResult = LottoResult.generate(lottoTickets, winningLotto);
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
            return money.isInValidNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return true;
        }
    }

    private static Money getMoney() {
        Money money;

        do {
            money = Money.generate(InputView.getPrice());
        } while (money.isNotCreatedWell());

        return money;
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

    private static WinningLotto createWinningLotto() {
        WinningLotto winningLotto;
        Lotto lotto;
        do {
            lotto = ManualLottoGenerator.create(InputView.getWinningLotto());
        } while (lotto.isNotCreatedWell());
        do {
            winningLotto = WinningLotto.generate(lotto, InputView.getBonusNumber());
        } while (winningLotto.isNotCreatedWell());

        return winningLotto;
    }

}
