package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoTest {
    @Test
    void 로또_생성() {
        assertThat(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 로또_정렬() {
        assertThat(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(Lotto.of(Arrays.asList(6, 5, 4, 3, 2, 1)));
    }

    @Test
    void 로또_길이_예외_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5));
        }).withMessage("로또의 크기는 6 입니다.");
    }

    @Test
    void 로또_중복_예외_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 5));
        }).withMessage("중복된 로또 번호가 존재합니다.");
    }

    @Test
    void 로또_포함_확인() {
        assertThat(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)).contains(LottoNo.of(3))).isTrue();
    }

    @Test
    void 로또_포함_번호_개수_확인() {
        assertThat(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)).includedNumber(Lotto.of(Arrays.asList(2, 3, 4, 9, 7, 8)))).isEqualTo(3);
    }
}