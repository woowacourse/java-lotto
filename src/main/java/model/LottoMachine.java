package model;

import model.generator.Generator;
import model.lottonumber.Lottos;
import model.money.PurchaseMoney;

public class LottoMachine {

    private final Lottos lottos;
    private final PurchaseMoney purchaseMoney;

    public LottoMachine(int purchaseMoney, Generator generator) {
        this.purchaseMoney = new PurchaseMoney(purchaseMoney);
        this.lottos = new Lottos(this.purchaseMoney, generator);
    }
}
