package lotto;

import lotto.domain.GameCounts;
import lotto.domain.LottoGames;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningNumbers;
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
        WinningNumbers winningNumber = getWinLotto();
        WinningLotto winningLotto = getWinningLotto(winningNumber);
        OutputView.winList(lottoGames, winningLotto);
        OutputView.rateOfReturn(purchaseAmount);
    }

    private static WinningLotto getWinningLotto(WinningNumbers winningNumbers) {
        try {
            int number = Integer.parseInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, LottoNumber.of(number));
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식 오류");
            return getWinningLotto(winningNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto(winningNumbers);
        }
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

    private static WinningNumbers getWinLotto() {
        try {
            return new WinningNumbers(InputView.getWinnerLotto());
        } catch (IllegalFormatException e) {
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }
}
