package lotto.domain.ticket.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoFactoryTest {

    @DisplayName("예외 테스트: 로또 번호 범위 외의 숫자를 요구할 때")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void getLottoBallByNumber(int number) {
        assertThatThrownBy(() -> LottoFactory.getLottoBallByNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%d: 로또 범위 이외 숫자입니다", number));
    }
}
