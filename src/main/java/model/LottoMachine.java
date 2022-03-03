package model;

import java.util.List;
import model.generator.Generator;
import model.generator.LottoNumberGenerator;
import model.lottonumber.Lotto;
import model.lottonumber.Lottos;
import model.money.ManualLottoCount;
import model.money.PurchaseMoney;

public class LottoMachine {

    private Lottos lottos;
    private PurchaseMoney purchaseMoney;

    public void insertPurchaseMoney(int purchaseMoney) {
        this.purchaseMoney = new PurchaseMoney(purchaseMoney);
    }

    public void purchaseLottos(List<Lotto> manualLottos, ManualLottoCount manualLottoCount) {
        int autoPurchaseCount = purchaseMoney.makePurchaseLottoCount() - manualLottoCount.getCount();
        Generator generator = new LottoNumberGenerator();

        new Lottos(manualLottos, autoPurchaseCount, generator);
    }

    public int getPurchaseLottoCount() {
        return purchaseMoney.makePurchaseLottoCount();
    }
}
