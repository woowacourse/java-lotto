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
        return InputView.getNumberOfMannualTicket(money);
    }

    private static Money getMoney() {
        Money money;

        do {
            money = Money.generate(InputView.getPrice());
        } while (money == null);

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
            lotto = LottoGenerator.create(InputView.getWinningLotto());
        } while (lotto == null);
        do {
            winningLotto = WinningLotto.generate(lotto, InputView.getBonusNumber());
        } while (winningLotto == null);

        return winningLotto;
    }

}
