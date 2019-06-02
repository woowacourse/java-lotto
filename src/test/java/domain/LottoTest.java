package domain;

import exception.LottoDuplicationException;
import exception.LottoExceedSizeException;
import exception.LottoNotSortedException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    @Test
    void 생성자_6개초과한_숫자들입력() {
        assertThrows(LottoExceedSizeException.class, () -> LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, Lotto.NUM_CNT, Lotto.NUM_CNT + 1)));
    }

    @Test
    void 생성자_중복된입력() {
        assertThrows(LottoDuplicationException.class, () -> LottoGenerator.from(Arrays.asList(1, 1, 3, 4, 5, 6)));
    }

    @Test
    void 생성자_정렬안된입력() {
        assertThrows(LottoNotSortedException.class, () -> LottoGenerator.from(Arrays.asList(6, 5, 4, 3, 2, 1)));
    }

    @Test
    void countEqualNumbers_모두동일하지않음() {
        Lotto lotto1 = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = LottoGenerator.from(Arrays.asList(11, 12, 13, 14, 15, 16));

        assertThat(lotto1.countEqualNumbers(lotto2)).isEqualTo(0);
    }

    @Test
    void countEqualNumbers_모두동일() {
        Lotto lotto1 = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lotto1.countEqualNumbers(lotto2)).isEqualTo(6);
    }

    @Test
    void contains_존재하는_번호() {
        Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(Number.from(1))).isTrue();
    }

    @Test
    void contains_존재하지않는_번호() {
        Lotto lotto = LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lotto.contains(Number.from(45))).isFalse();
    }
}