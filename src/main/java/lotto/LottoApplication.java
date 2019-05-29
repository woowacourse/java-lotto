package lotto;

import lotto.domain.BonusNumber;
import lotto.domain.GameCounts;
import lotto.domain.LottoGames;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.exceptions.IllegalFormatException;
import lotto.exceptions.PurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    private static final String NUMBER_FORMAT_EXCEPTION = "숫자로 입력해주세요";

    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        GameCounts gameCounts = getGameCounts(purchaseAmount);
        LottoGames lottoGames = getLottoGames(gameCounts);
        OutputView.lottoList(lottoGames);
        WinningLotto winningLotto = getWinLotto();
        BonusNumber bonusNumber = getBonusNumber(winningLotto);
        OutputView.winList(lottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.getPurchaseAmount());
        } catch (PurchaseAmountException e) {
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
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getGameCounts(purchaseAmount);
        }
    }

    private static LottoGames getLottoGames(GameCounts gameCounts) {
        try {
            return new LottoGames(gameCounts);
        } catch (PurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getLottoGames(gameCounts);
        }
    }

    private static WinningLotto getWinLotto() {
        try {
            return new WinningLotto(InputView.getWinnerLotto());
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }

    private static BonusNumber getBonusNumber(WinningLotto winningLotto) {
        try {
            return BonusNumber.of(InputView.getBonusNumber(), winningLotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber(winningLotto);
        }
    }
}
