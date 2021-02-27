package lotto.domain.ticket;

import lotto.domain.ticket.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @DisplayName("유효한 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Not_ThrowException_When_LottoNumberInRange(int lottoNumber) {
        assertThatCode(() -> LottoNumber.of(lottoNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void Should_ThrowException_When_LottoNumberOutRange(int lottoNumber) {
        assertThatThrownBy(() -> LottoNumber.of(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

