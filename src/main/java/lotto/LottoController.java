package lotto;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoController {
    private static final String DELIMITER = ",";
    private static Lottos lottos;
    private static WinningLotto winningLotto;
    private static ArrayList<Integer> wins = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0));

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
        LottoView.printLottos(lottos);
        makeWinningLotto();
    }

    public void makeWinningLotto() {
        String winningInput = LottoView.requestWinningNumber();
        String bonusInput = LottoView.requestBonusBallNumber();
        winningLotto = new WinningLotto(winningInput, bonusInput);
    }

    public void drawLotto() {
        ArrayList<Lotto> lottoGroup = lottos.getLottoGroup();
        ArrayList<Integer> win = winningLotto.getLotto().getLottoNumbers();
        int bonusBall = winningLotto.getBonusBall();
        for (int i = 0; i < lottoGroup.size(); i++) {
            int match = lottoGroup.get(i).howManyWins(win);
            boolean bonusMatch = lottoGroup.get(i).isContainNum(bonusBall);
            Rank rank = Rank.makeRankByMatch(match, bonusMatch);
        }
    }
}
