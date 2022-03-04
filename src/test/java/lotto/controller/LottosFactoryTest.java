package lotto.controller;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class LottosFactoryTest {

    @DisplayName("로또의 개수가 음수일 경우 예외를 발생한다.")
    @ParameterizedTest
    @EnumSource(LottosFactory.class)
    void throwExceptionNegativeLottoAmount(LottosFactory lottosFactory) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> lottosFactory.generate(-1))
            .withMessage("로또의 개수는 음수일 수 없다.");
    }

    @DisplayName("로또의 개수가 0개일 경우 빈 리스트를 반환한다.")
    @ParameterizedTest
    @EnumSource(LottosFactory.class)
    void getEmptyLottoIsZeroAmount(LottosFactory lottosFactory) {
        assertThat(lottosFactory.generate(0)).isEmpty();
    }
}