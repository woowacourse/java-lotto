package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.object.ManualPurchaseNumber;
import lotto.model.object.Payment;

import java.util.ArrayList;
import java.util.List;

public class LottosCreator {
        //auto
        public static List<Lotto> create(final Payment payment, final ManualPurchaseNumber manualPurchaseNumber) {
                List<Lotto> lottos = new ArrayList<>();
                for (int index = 0; index < payment.getAmount() / Payment.LOTTO_PRICE - manualPurchaseNumber.getNumber(); index++) {
                        lottos.add(new Lotto(AutoLottoNumbersCreator.create()));
                }
                return lottos;
        }

        //manual
        public static List<Lotto> create(final List<String[]> lottoInputs) {
                List<Lotto> lottos = new ArrayList<>();
                for (String[] lottoInput : lottoInputs) {
                        lottos.add(new Lotto(lottoInput));
                }
                return lottos;
        }
}
