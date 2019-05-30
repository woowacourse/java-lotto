package lotto.model.object;

import lotto.model.creator.AutoPurchasedLottoCreator;
import lotto.model.creator.BonusBallCreator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
        @Test
        void 매칭_숫자_검사() {
                List<Integer> numbers1 = new ArrayList<>();
                numbers1.add(1);
                numbers1.add(2);
                numbers1.add(3);
                numbers1.add(4);
                numbers1.add(5);
                numbers1.add(6);
                Lotto lotto1 = AutoPurchasedLottoCreator.create(numbers1);

                List<Integer> numbers2 = new ArrayList<>();
                numbers2.add(1);
                numbers2.add(20);
                numbers2.add(3);
                numbers2.add(40);
                numbers2.add(5);
                numbers2.add(10);
                Lotto lotto2 = AutoPurchasedLottoCreator.create(numbers2);

                assertThat(lotto1.getMatchNumber(lotto2)).isEqualTo(3);
        }

        @Test
        void 보너스볼_일치_검사() {
                BonusBall bonusBall = BonusBallCreator.create(45);

                List<Integer> numbers1 = new ArrayList<>();
                numbers1.add(1);
                numbers1.add(2);
                numbers1.add(3);
                numbers1.add(4);
                numbers1.add(5);
                numbers1.add(45);
                Lotto lotto1 = AutoPurchasedLottoCreator.create(numbers1);

                assertThat(lotto1.hasBonusBall(bonusBall)).isEqualTo(true);
        }
}
