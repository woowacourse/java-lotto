package lotto;

import lotto.domain.GameCounts;
import lotto.domain.LottoGames;
import lotto.domain.WinLotto;
import lotto.exceptions.IllegalInputFormatException;
import lotto.exceptions.InvalidPurchaseAmountException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args){
        GameCounts gameCounts = getGameCounts();
        LottoGames lottoGames = getLottoGames(gameCounts);
        OutputView.lottoList(lottoGames);
        WinLotto winLotto = getWinLotto();
        OutputView.winList(lottoGames, winLotto);
    }

    private static GameCounts getGameCounts() {
        try {
            return new GameCounts(InputView.getPurchaseAmount());
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return getGameCounts();
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력해주세요");
            return getGameCounts();
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
            e.printStackTrace();
            System.out.println(e.getMessage());
            return getWinLotto();
        }
    }
}
