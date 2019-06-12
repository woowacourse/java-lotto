package lotto.model.creator.lottos;

import lotto.model.creator.lotto.LottoCreator;
import lotto.model.object.Lotto;
import lotto.model.object.ManualPurchaseNumber;
import lotto.model.object.Payment;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosCreatorStrategty implements LottosCreatorStrategy {
        private final Payment payment;
        private final ManualPurchaseNumber manualPurchaseNumber;
        private final LottoCreator lottoCreator;

        public AutoLottosCreatorStrategty(final LottoCreator lottoCreator, final Payment payment, final ManualPurchaseNumber manualPurchaseNumber) {
                this.lottoCreator = lottoCreator;
                this.payment = payment;
                this.manualPurchaseNumber = manualPurchaseNumber;
        }

        public List<Lotto> create() {
                List<Lotto> lottos = new ArrayList<>();
                for (int index = 0; index < payment.getAmount() / Payment.LOTTO_PRICE - manualPurchaseNumber.getNumber(); index++) {
                        lottos.add(lottoCreator.create());
                }
                return lottos;
        }
}
