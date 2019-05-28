package lotto;

import lotto.domain.GameCounts;
import lotto.domain.LottoGames;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinLotto;
import lotto.exceptions.IllegalInputFormatException;
import lotto.exceptions.InvalidPurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자로 입력해주세요";

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        GameCounts gameCounts = getGameCounts(purchaseAmount);
        LottoGames lottoGames = getLottoGames(gameCounts);
        OutputView.lottoList(lottoGames);
        WinLotto winLotto = getWinLotto();
        OutputView.winList(lottoGames, winLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.getPurchaseAmount());
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION);
            return getPurchaseAmount();
        }
    }

    private static GameCounts getGameCounts(PurchaseAmount purchaseAmount) {
        try {
            return new GameCounts(purchaseAmount);
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getGameCounts(purchaseAmount);
        }
    }

    private static LottoGames getLottoGames(GameCounts gameCounts) {
        try {
            return new LottoGames(gameCounts);
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getLottoGames(gameCounts);
        }
    }

    private static WinLotto getWinLotto() {
        try {
            return new WinLotto(InputView.getWinnerLotto());
        } catch (IllegalInputFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }
}
