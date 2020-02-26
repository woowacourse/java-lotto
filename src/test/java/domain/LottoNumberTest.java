package domain;

import Lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("옳은 입력으로 로또넘버 생성")
    @ValueSource(ints = {1, 5, 45})
    void rightInputTest(int input) {
        LottoNumber lottoNumber = new LottoNumber(input);
        assertThat(lottoNumber).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("잘못된 입력으로 로또넘버 생성 시도 시에 Exception 처리")
    @ValueSource(ints = {0, 46, 1000})
    void wrongInputTest(int input) {
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }

}
