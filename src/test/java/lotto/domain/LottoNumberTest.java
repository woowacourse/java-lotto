package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("유효한 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 45})
    void Should_Not_ThrowException_When_LottoNumberInRange(int lottoNumber) {
        assertThatCode(() -> new LottoNumber(lottoNumber))
            .doesNotThrowAnyException();
    }

    @DisplayName("유효하지 않은 로또 번호 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void Should_ThrowException_When_LottoNumberOutRange(int lottoNumber) {
        assertThatThrownBy(() -> new LottoNumber(lottoNumber))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호 설정 및 확인 테스트")
    @Test
    void Should_True_When_BonusNumber() {
        LottoNumber lottoNumber = new LottoNumber(1, true);
        assertThat(lottoNumber.isBonusNumber()).isTrue();
    }

    @DisplayName("보너스 번호 비설정 및 확인 테스트")
    @Test
    void Should_False_When_Not_BonusNumber() {
        LottoNumber lottoNumber = new LottoNumber(1, false);
        assertThat(lottoNumber.isBonusNumber()).isFalse();
    }
}

