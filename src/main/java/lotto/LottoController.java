package lotto;

public class LottoController {

    public void run() {
        int money = 14000;
        Store store = new Store(money, new LottoGenerator());
        Lottos lottos = new Lottos(store.buyLottos());
    }
}
