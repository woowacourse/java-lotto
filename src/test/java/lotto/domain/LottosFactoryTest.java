package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LottosFactoryTest {
    @Test
    void createLottoAuto_Money_1000원_미만_입력시_예외처리() {
        assertThatThrownBy(() -> {
            Integer inputMoney = 500;
            LottosFactory.createLottosAuto(new Money(inputMoney));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("1000원 이상 입력해주세요.");
    }

    @Test
    void createLottoManual_Money_1000원_미만_입력시_예외처리() {
        assertThatThrownBy(() -> {
            Integer inputMoney = 500;
            LottosFactory.createLottosManual(
                new Money(inputMoney),
                Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6)));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("1000원 이상 입력해주세요.");
    }

    @Test
    void createLottoManual_수동로또_갯수가_총_로또갯수보다_클때_예외처리() {
        assertThatThrownBy(() -> {
            Integer inputMoney = 1_000;
            LottosFactory.createLottosManual(
                new Money(inputMoney),
                Arrays.asList(
                    Arrays.asList(1, 2, 3, 4, 5, 6),
                    Arrays.asList(1, 2, 9, 4, 3, 6),
                    Arrays.asList(2, 3, 4, 5, 6, 10),
                    Arrays.asList(1, 2, 3, 4, 5, 6))
                );
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수동으로 구매할 로또의 갯수가 총 구매할 로또 갯수보다 클 수 없습니다.");
    }
}
