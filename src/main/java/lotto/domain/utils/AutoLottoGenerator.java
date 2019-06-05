package lotto.domain.utils;

import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.model.Number;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoGenerator implements LottoGenerator {

    private Money money = null;
    private int manualLottoSize  = 0;

    public AutoLottoGenerator(Money money, int manualLottoSize) {
        this.money = money;
        this.manualLottoSize = manualLottoSize;
    }

    @Override
    public List<Lotto> generate(List<Number> lottoNumber) {
        List<Lotto> autoLottos = new ArrayList<>();
        int autoLottoSize = money.availablePurchseTicketCount() - manualLottoSize;

        for (int i = 1; i <= autoLottoSize; i++) {
            autoLottos.add(new Lotto(lottoNumber));
        }
        return autoLottos;
    }
}
