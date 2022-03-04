package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private Store() {
    }

    public static List<Lotto> buyLottos(LottoMoney leftMoney, List<Lotto> manualLottos) {
        List<Lotto> lottos = buyManualLottos(leftMoney, manualLottos);
        lottos.addAll(buyAutomaticLottos(leftMoney));
        return lottos;
    }

    private static List<Lotto> buyManualLottos(LottoMoney leftMoney, List<Lotto> manualLottos) {
        leftMoney.subtract(LottoMoney.createLottoMoneyByCount(manualLottos.size()));
        return new ArrayList<>(manualLottos);
    }

    private static List<Lotto> buyAutomaticLottos(LottoMoney leftMoney) {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy(leftMoney)) {
            lottos.add(buy(leftMoney));
        }
        return lottos;
    }

    private static boolean canBuy(LottoMoney leftMoney) {
        return leftMoney.isGreaterThan(LottoMoney.createMinimumLottoMoney());
    }

    private static Lotto buy(LottoMoney leftMoney) {
        leftMoney.subtract(LottoMoney.createMinimumLottoMoney());
        return LottoGenerator.generate();
    }

}
