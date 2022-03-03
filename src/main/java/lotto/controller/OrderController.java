package lotto.controller;

import java.util.List;
import lotto.domain.LottoOrder;

public class OrderController {

    private LottoOrder lottoOrder;

    public void takeOrder(int inputMoney, List<List<Integer>> numbers) {
        lottoOrder = new LottoOrder(inputMoney, numbers);
    }

    public LottoOrder getLottoOrder() {
        return lottoOrder;
    }
}
