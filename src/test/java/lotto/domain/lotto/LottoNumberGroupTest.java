package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberGroupTest {
    @Test
    void 로또_정상생성() {
        assertThat(LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6)))
                .isEqualTo(LottoNumberGroup.create(() -> Arrays.asList(3, 4, 5, 6, 1, 2)));
    }

    @Test
    void 중복_로또번호() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 5));
        });
    }

    @Test
    void 로또번호_5개() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5));
        });
    }

    @Test
    void 로또번호_7개() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        });
    }

    @Test
    void 번호범위_벗어남() {
        assertThrows(InvalidLottoNumberGroupException.class, () -> {
            LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 47));
        });
    }

    @Test
    void 번호_일치_개수_확인() {
        LottoNumberGroup user = LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumberGroup goal = LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 8, 9, 10));
        assertThat(user.countOfMatch(goal)).isEqualTo(3);
    }

    @Test
    void 포함하는_번호_확인() {
        LottoNumberGroup user = LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(user.contains(LottoNumber.of(6))).isTrue();
    }

    @Test
    void 포함하지않는_번호_확인() {
        LottoNumberGroup user = LottoNumberGroup.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(user.contains(LottoNumber.of(7))).isFalse();
    }
}