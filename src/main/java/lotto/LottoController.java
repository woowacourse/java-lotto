package lotto;

public class LottoController {
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
        for (Lotto lotto : lottoGroup) {
            Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
    }
}
