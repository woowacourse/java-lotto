package lottogame;

import lottogame.domain.*;
import lottogame.utils.InvalidLottoNumberException;
import lottogame.utils.InvalidLottoPriceException;
import lottogame.utils.LottoNumbersParser;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        Money money = getMoney();
        LottoTickets lottoTickets = createLottoTickets(money);
        createLotto(lottoTickets, money);
        OutputView.printPurchaseResult(lottoTickets);
        WinningLotto winningLotto = createWinningLotto();
        addBonusNumber(winningLotto);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        OutputView.printLottoResult(lottoResult, 10000);
    }

    private static void createLotto(LottoTickets lottoTickets, Money money) {
        do {
            createManualLotto(lottoTickets);
        } while (!lottoTickets.isManualLottoFill());
        lottoTickets.addAutoLotto(money.getNumberOfTicket());
    }

    private static void createManualLotto(LottoTickets lottoTickets) {
        try {
            lottoTickets.addManualLotto(LottoNumbersParser.parse(InputView.getManualLottoNumber()));
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            createManualLotto(lottoTickets);
        }
    }

    private static Money getMoney() {
        try {
            return new Money(Integer.parseInt(InputView.getPrice()));
        } catch (NumberFormatException | InvalidLottoPriceException e) {
            System.out.println(e.getMessage());
            return getMoney();
        }
    }

    private static LottoTickets createLottoTickets(Money money) {
        try {
            return new LottoTickets(Integer.parseInt(InputView.getNumberOfMannualTicket()), money);
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            return createLottoTickets(money);
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(LottoNumbersParser.parse(InputView.getWinningLotto()));
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }

    private static void addBonusNumber(WinningLotto winningLotto) {
        try {
            winningLotto.addBonusNumber(Integer.parseInt(InputView.getBonusNumber()));
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            addBonusNumber(winningLotto);
        }
    }
}
