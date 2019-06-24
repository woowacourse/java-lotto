package lotto.model.creator.lottos;

import lotto.model.creator.lotto.AutoLottoCreatorStrategy;
import lotto.model.creator.lotto.LottoCreatorStrategy;
import lotto.model.creator.lotto.ManualLottoCreatorStrategy;
import lotto.model.object.LottoNumber;
import lotto.model.object.ManualPurchaseNumber;
import lotto.model.object.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

class LottosCreatorTest {
        Payment payment;
        ManualPurchaseNumber manualPurchaseNumber;
        LottosCreator lottosCreator;


        @BeforeEach
        void setUp() {
                payment = new Payment(5000);
                manualPurchaseNumber = new ManualPurchaseNumber(2, payment);
        }

        @Test
        void 자동로또_생성_확인_로또개수() {
                lottosCreator = new LottosCreator(new AutoLottosCreatorStrategty(AutoLottoCreatorStrategy.getInstance(), payment, manualPurchaseNumber));
                assertThat(lottosCreator.create().size()).isEqualTo(3);
        }

        @Test
        void 수동로또_생성_확인() {
                List<LottoCreatorStrategy> lottoCreatorStrategies = new ArrayList<>();

                lottoCreatorStrategies.add(new ManualLottoCreatorStrategy(new String[]{"1", "2", "3", "4", "5", "6"}));
                lottoCreatorStrategies.add(new ManualLottoCreatorStrategy(new String[]{"7", "8", "9", "10", "11", "12"}));

                lottosCreator = new LottosCreator(new ManualLottosCreatorStrategy(lottoCreatorStrategies));

                assertThat(lottosCreator.create().get(1).getLottoNumbers()).isEqualTo(Arrays.asList(
                    LottoNumber.getInstance(7),
                    LottoNumber.getInstance(8),
                    LottoNumber.getInstance(9),
                    LottoNumber.getInstance(10),
                    LottoNumber.getInstance(11),
                    LottoNumber.getInstance(12)
                ));
        }
}