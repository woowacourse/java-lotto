package lotto;

public class LottoController {
    private static final String DELIMITER = ",";
    private static Lottos lottos;
    private static WinningLotto winningLotto;

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
}
