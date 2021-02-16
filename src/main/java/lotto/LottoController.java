package lotto;

public class LottoController {
    private static Lottos lottos;

    public void startLotto() {
        lottos = new Lottos(LottoView.requestMoney());
        LottoView.buyLotto(lottos.getCount());
        lottos.generateLottos();
        LottoView.printLottos(lottos);
    }

}
