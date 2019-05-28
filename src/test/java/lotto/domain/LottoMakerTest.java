package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoMakerTest {
    private static final int COUNT = 6;

    @Test
    void 숫자_개수_확인() {
        List<Integer> numbers = LottoMaker.generator();
        assertThat(numbers.size()).isEqualTo(COUNT);
    }

    @Test
    void 중복_확인() {
        List<Integer> numbers = LottoMaker.generator();
        assertThat(numbers.size()).isEqualTo(new HashSet<>(numbers).size());
    }
}
