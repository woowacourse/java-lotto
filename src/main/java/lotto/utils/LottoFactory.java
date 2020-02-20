package lotto.utils;

import lotto.domain.Lotto;
import lotto.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> createLottoList(Payment payment) {
        NumberGenerator randomGenerator = new RandomGenerator();
        List<Lotto> lottoList = new ArrayList<>();

        for (int i = 0; i < payment.getLottoCount(); i++) {
            lottoList.add(new Lotto(randomGenerator.generateNumbers()));
        }
        return lottoList;
    }
}
