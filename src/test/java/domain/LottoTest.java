package domain;

import exception.LottoDuplicationException;
import exception.LottoExceedSizeException;
import exception.LottoNotSortedException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    @Test
    void 생성자_6개초과한_숫자들입력() {
        assertThrows(LottoExceedSizeException.class, () -> createLotto(Arrays.asList(1, 2, 3, 4, 5, Lotto.NUM_CNT, Lotto.NUM_CNT + 1)));
    }

    @Test
    void 생성자_중복된입력() {
        assertThrows(LottoDuplicationException.class, () -> createLotto(Arrays.asList(1, 1, 3, 4, 5, 6)));
    }

    @Test
    void 생성자_정렬안된입력() {
        assertThrows(LottoNotSortedException.class, () -> createLotto(Arrays.asList(6, 5, 4, 3, 2, 1)));
    }

    @Test
    void countEqualNumbers_모두동일하지않음() {
        Lotto lotto1 = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = createLotto(Arrays.asList(11, 12, 13, 14, 15, 16));

        assertThat(lotto1.countEqualNumbers(lotto2)).isEqualTo(0);
    }

    @Test
    void countEqualNumbers_모두동일() {
        Lotto lotto1 = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(lotto1.countEqualNumbers(lotto2)).isEqualTo(6);
    }

    private Lotto createLotto(List<Integer> integers) {
        List<Number> numbers = integers.stream().map(i -> Number.from(i)).collect(Collectors.toList());
        return new Lotto(numbers);
    }
}