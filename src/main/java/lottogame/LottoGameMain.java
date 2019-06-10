package lottogame;

import lottogame.domain.*;
import lottogame.lottogameexception.InvalidLottoNumberException;
import lottogame.lottogameexception.InvalidLottoPriceException;
import lottogame.utils.LottoNumbersParser;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        Money money = getMoney();
        LottoTickets lottoTickets = createLottoTickets(money);
        createLottos(lottoTickets, money);
        OutputView.printPurchaseResult(lottoTickets);

        WinningLotto winningLotto = createWinningLotto();
        addBonusNumber(winningLotto);

        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        OutputView.printLottoResult(lottoResult, money);
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
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
        }
        return createLottoTickets(money);
    }

    private static void createLottos(LottoTickets lottoTickets, Money money) {
        if (lottoTickets.isPossibleCreateManualLotto()) {
            OutputView.printRequestOfManualLottoNumber();
        }
        while (lottoTickets.isPossibleCreateManualLotto()) {
            createManualLotto(lottoTickets);
        }
        while (lottoTickets.isPossibleCreateLottoNumberOf(money.getNumberOfTicket())) {
            lottoTickets.createAutoLottos();
        }
    }

    private static void createManualLotto(LottoTickets lottoTickets) {
        try {
            lottoTickets.addManualLotto(LottoNumbersParser.parse(InputView.getManualLottoNumber()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(LottoNumbersParser.parse(InputView.getWinningLotto()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
            return createWinningLotto();
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }

    private static void addBonusNumber(WinningLotto winningLotto) {
        while(winningLotto.isBonusNumberNull()){
            setBonusNumber(winningLotto);
        }
    }

    private static void setBonusNumber(WinningLotto winningLotto) {
        try {
            winningLotto.addBonusNumber(Integer.parseInt(InputView.getBonusNumber()));
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
        } catch (InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}
