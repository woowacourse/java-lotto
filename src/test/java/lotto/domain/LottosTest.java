package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @DisplayName("구입 개수만큼 로또를 생성한다")
    @Test
    void lottosCreationTest() {
        int quantity = 7;
        Lottos lottos = Lottos.ofSize(quantity);

        // TODO: 사이즈 체크하기
        assertThat(lottos).isNotNull()
                .isInstanceOf(Lottos.class);
    }

    @DisplayName("")
    @Test
    void calculatePrizesTest() {
        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        Lotto lotto1 = Lotto.of(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto lotto2 = Lotto.of(List.of(1, 2, 3, 4, 5, 7)); // 2둥
        Lotto lotto3 = Lotto.of(List.of(1, 2, 3, 4, 5, 8)); // 3둥
        Lotto lotto4 = Lotto.of(List.of(1, 2, 3, 4, 9, 10)); // 4둥
        Lotto lotto5 = Lotto.of(List.of(11, 12, 13, 4, 5, 6)); // 5둥
        Lotto lotto6 = Lotto.of(List.of(11, 12, 13, 14, 5, 6)); // 6둥

        List<Lotto> myLotto = List.of(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6);
        Lottos lottos = Lottos.of(myLotto);

        List<Prize> prizes = lottos.calculatePrizes(winningLotto);

        assertThat(prizes).isEqualTo(Arrays.stream(Prize.values()).toList());
    }

    @Test
    void earningRateTest() {
        Lotto winningNumbers = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusNumber);

        List<Lotto> myLotto = new ArrayList<>();
        myLotto.add(Lotto.of(List.of(8, 21, 23, 41, 42, 43))); //6등
        myLotto.add(Lotto.of(List.of(3, 5, 11, 16, 32, 38))); //6등
        myLotto.add(Lotto.of(List.of(7, 11, 16, 35, 36, 44))); //6등
        myLotto.add(Lotto.of(List.of(1, 8, 11, 31, 41, 42))); //6등
        myLotto.add(Lotto.of(List.of(13, 14, 16, 38, 42, 45))); //6등
        myLotto.add(Lotto.of(List.of(7, 11, 30, 40, 42, 43))); //6등
        myLotto.add(Lotto.of(List.of(2, 13, 22, 32, 38, 45))); //6등
        myLotto.add(Lotto.of(List.of(23, 25, 33, 36, 39, 41))); //6등
        myLotto.add(Lotto.of(List.of(1, 3, 5, 14, 22, 45))); //3등
        myLotto.add(Lotto.of(List.of(5, 9, 38, 41, 43, 44))); //6등
        myLotto.add(Lotto.of(List.of(2, 8, 9, 18, 19, 21))); //6등
        myLotto.add(Lotto.of(List.of(13, 14, 18, 21, 23, 35))); //6등
        myLotto.add(Lotto.of(List.of(17, 21, 29, 37, 42, 45))); //6등
        myLotto.add(Lotto.of(List.of(3, 8, 27, 30, 35, 44))); //6등

        Lottos lottos = Lottos.of(myLotto);

        double earningRate = lottos.calculateEarningRate(lottos.calculatePrizes(winningLotto));
        double formattedRate = (long) (earningRate * 100) / 100.0;

        assertThat(formattedRate).isEqualTo(0.35);
    }
}
