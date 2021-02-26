package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import lotto.Money;
import lotto.domain.lotto.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("로또 번호는 6개로 구성되어있어야한다.")
    @Test
    void 로또_번호_길이_테스트() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.generate(Arrays.asList(1, 2, 3, 4, 5));
        });
    }

    @DisplayName("6개의 로또 번호는 서로 다른 번호여야한다.")
    @Test
    void 로또_번호_중복_테스트() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Lotto lotto = Lotto.generate(Arrays.asList(1, 1, 2, 3, 4, 5));
        });
    }

    @RepeatedTest(10)
    void 로또_번호_랜덤_생성_테스트() {
        // given, when
        Lotto lotto1 = Lotto.generate(new RandomLottoGenerator());

        // then
        lotto1.getNumbers()
            .stream()
            .map(number -> number.getValue())
            .forEach(number -> System.out.print(number + " "));
        System.out.println();
    }

    @Test
    void 로또_구매_수량_테스트() {
        Money money = new Money("14990");
        LottoStore lottoStore = new LottoStore();
        Lottos lottos = lottoStore.buyLottos(money, new ManualLotto());

        assertThat(lottos.size()).isEqualTo(14);
    }

    @Test
    void 에러_메세지_출력_테스트() {
        try {
            throw new IllegalArgumentException("thisError");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
