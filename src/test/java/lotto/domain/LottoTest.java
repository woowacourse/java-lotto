package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private static final String NUMBER_SIZE_ERROR = "[ERROR] 중복되지 않는 6개의 숫자를 입력해야 합니다.";

    @Test
    @DisplayName("포함된 숫자가 6개인지 검증")
    void lottoNumbers_size() {
        assertThatThrownBy(() -> {
            new Lotto(new HashSet<>(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3)
            )));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_SIZE_ERROR);
    }

    @Test
    @DisplayName("숫자가 6개 초과로 들어올 때 검증")
    void lottoNumbers_size_over() {
        assertThatThrownBy(() -> {
            new Lotto(new HashSet<>(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6),
                LottoNumber.of(7)
            )));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_SIZE_ERROR);
    }


    @Test
    @DisplayName("중복이 있을 때 에러 발생")
    void lottoNumbers_duplicate() {
        assertThatThrownBy(() -> {
            new Lotto(new HashSet<>(Arrays.asList(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(5)
            )));
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_SIZE_ERROR);
    }
}
