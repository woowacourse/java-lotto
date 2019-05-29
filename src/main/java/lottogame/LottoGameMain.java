package lottogame;

import lottogame.domain.LottoResult;
import lottogame.domain.LottoResultGenerator;
import lottogame.domain.LottoTickets;
import lottogame.domain.WinningLotto;
import lottogame.utils.InvalidLottoNumberException;
import lottogame.utils.InvalidLottoPriceException;
import lottogame.utils.LottoNumbersParser;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        LottoTickets lottoTickets = createLottoTickets();
        OutputView.printPurchaseResult(lottoTickets);
        WinningLotto winningLotto = createWinningLotto();
        addBonusNumber(winningLotto);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        OutputView.printLottoResult(lottoResult, lottoTickets.price());
    }

    private static LottoTickets createLottoTickets() {
        try {
            return new LottoTickets(Integer.parseInt(InputView.getPrice()));
        } catch (NumberFormatException | InvalidLottoPriceException e) {
            System.out.println(e.getMessage());
            return createLottoTickets();
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
