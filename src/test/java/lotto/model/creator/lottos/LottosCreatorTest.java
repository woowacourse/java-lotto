package lotto.model.creator.lottos;

import lotto.model.creator.lotto.AutoLottoCreator;
import lotto.model.object.ManualPurchaseNumber;
import lotto.model.object.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
                lottosCreator = new LottosCreator(new AutoLottosCreatorStrategty(new AutoLottoCreator(), payment, manualPurchaseNumber));
                assertThat(lottosCreator.create().size()).isEqualTo(3);
        }

        @Test
        void 수동로또_생성_확인_로또개수() {
                List<String[]> inputs = new ArrayList<>();
                String[] input1 = {"1", "2", "3", "4", "5", "6"};
                String[] input2 = {"7", "8", "9", "10", "11", "12"};

                inputs.add(input1);
                inputs.add(input2);

                lottosCreator = new LottosCreator(new ManualLottosCreatorStrategy(inputs));

                assertThat(lottosCreator.create().size()).isEqualTo(2);
        }
}