package domain;

import Lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @Test
    @DisplayName("1~45의 범위 안에서 로또넘버 생성")
    void rightInputTest() {
        LottoNumber lottoNumber = LottoNumber.of(1);
        assertThat(LottoNumber.of(1)).isEqualTo(lottoNumber);
        assertThat(LottoNumber.of(1) == LottoNumber.of(1)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("1~45의 범위 밖의 로또넘버 생성 시도 시에 Exception 처리")
    @ValueSource(ints = {0, 46, 1000})
    void wrongInputTest(int input) {
        assertThatThrownBy(() -> {
            LottoNumber.of(input);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위");
    }

    @Test
    @DisplayName("같은 입력으로 생성된 로또 넘버가 같은 객채로 인식되는지 확인")
    void sameInputSameObject() {
        LottoNumber l1 = new LottoNumber(2);
        LottoNumber l2 = new LottoNumber(2);
        assertThat(l1.equals(l2)).isTrue();
    }

    @Test
    @DisplayName("디른 입력으로 생성된 로또 넘버는 다른 객체로 인식됨을 확인")
    void diffInputDiffObject() {
        LottoNumber l1 = new LottoNumber(2);
        LottoNumber l2 = new LottoNumber(6);
        assertThat(l1.equals(l2)).isFalse();
    }
}
