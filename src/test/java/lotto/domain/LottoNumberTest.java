package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("유효한 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Not_ThrowException_When_LottoNumberInRange(int lottoNumber) {
        assertThatCode(() -> new LottoNumber(lottoNumber))
            .doesNotThrowAnyException();
        assertThat(new LottoNumber(lottoNumber).getNumber()).isEqualTo(lottoNumber);
    }

    @DisplayName("유효하지 않은 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void Should_ThrowException_When_LottoNumberOutRange(int lottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(lottoNumber))
            .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("캐싱되어있는 로또 번호 객체 가져오기 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Return_ExpectedNumber_When_ValueOf(int value) {
        LottoNumber lottoNumber = LottoNumber.valueOf(value);
        assertThat(lottoNumber.getNumber()).isEqualTo(value);
    }

    @DisplayName("유효하지 않은 값으로 캐싱되어있는 로또 번호 가져오기 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void Should_ThrowException_When_ValueOfOutRange(int value) {
        assertThatThrownBy(() -> LottoNumber.valueOf(value))
            .isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("로또 번호 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Return_ExpectedNumber_When_Get(int lottoNumberInput) {
        LottoNumber lottoNumber = new LottoNumber(lottoNumberInput);
        assertThat(lottoNumber.getNumber()).isEqualTo(lottoNumberInput);
    }

    @DisplayName("로또 번호 VO 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Be_Equal_ExpectedNumber_When_Compare(int lottoNumber) {
        assertThat(new LottoNumber(lottoNumber)).isEqualTo(new LottoNumber(lottoNumber));
        assertThat(new LottoNumber(lottoNumber)).hasSameHashCodeAs(new LottoNumber(lottoNumber));
    }
}

