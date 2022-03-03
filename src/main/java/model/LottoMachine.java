package model;

import java.util.List;

import model.lottonumber.Lotto;
import model.lottonumber.Lottos;
import model.totalmoney.TotalPurchaseMoney;

public class LottoMachine {

    private Lottos lottos;
    private TotalPurchaseMoney totalPurchaseMoney;

    public void insertTotalPurchaseMoney(final int totalPurchaseMoney, final int manualLottoCount) {
        this.totalPurchaseMoney = new TotalPurchaseMoney(totalPurchaseMoney, manualLottoCount);
    }

    public void purchaseLottos(List<Lotto> manualLottos) {
        int autoPurchaseCount = totalPurchaseMoney.getAutoPurchaseCount();

        lottos = new Lottos(manualLottos, autoPurchaseCount);
    }

    public int sendManualLottoCount() {
        return totalPurchaseMoney.getManualLottoCount();
    }

    public int sendAutoLottoCount() {
        return totalPurchaseMoney.getAutoPurchaseCount();
    }

    public List<Lotto> sendLottosInMachine() {
        return lottos.getLottos();
    }
}
