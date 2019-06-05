package domain;

import exception.LottoDuplicationException;
import exception.LottoExceedSizeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoFactoryTest {

    @Test
    void createRandomLotto() {
        for (int i = 0; i < 10; i++) {
            LottoFactory.createRandomLotto();
        }
    }

    @Test
    void createLottoFromNumbers_로또에_필요한_숫자개수_부족() {
        List<Number> numbers = createNumberList(Arrays.asList(1, 2, 3, 4, 5));

        assertThrows(LottoExceedSizeException.class, () -> LottoFactory.createLottoFromNumbers(numbers));
    }

    @Test
    void createLottoFromNumbers_로또에_필요한_숫자개수_초과() {
        List<Number> numbers = createNumberList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        assertThrows(LottoExceedSizeException.class, () -> LottoFactory.createLottoFromNumbers(numbers));
    }

    @Test
    void createLottoFromNumbers_중복있는_숫자() {
        List<Number> numbers = createNumberList(Arrays.asList(1, 1, 3, 4, 5, 6));

        assertThrows(LottoDuplicationException.class, () -> LottoFactory.createLottoFromNumbers(numbers));
    }

    @Test
    void createLottoFromNumbers_정렬안된입력() {
        List<Number> numbers = createNumberList(Arrays.asList(5, 6, 4, 3, 2, 1));
        List<Number> sortedNumbers = createNumberList(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(LottoFactory.createLottoFromNumbers(numbers)).isEqualTo(new Lotto(sortedNumbers));
    }

    private List<Number> createNumberList(List<Integer> integers) {
        return integers.stream().map(Number::from).collect(Collectors.toList());
    }
}