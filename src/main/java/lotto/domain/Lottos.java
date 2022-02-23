package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.utils.Validation;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos(int money){
        Validation.checkDivideMoney(money);
        int count = money/Money.BASIC_LOTTO_MONEY;

        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoNumber.createLottoNumbers()));
        }
    }

    public List<Lotto> getLottos(){
        return lottos;
    }
}
