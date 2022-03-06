package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @Test
    @DisplayName("생성된 수동 로또를 검증한다")
    void createManualTest() {
        List<List<Integer>> rawIntegers = List.of(List.of(1, 2, 3, 4, 5, 6));
        LottoFactory factory = new ManualLottoFactory(rawIntegers);
        Lotto actual = factory.generate();

        Lotto expected = new Lotto(
            Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(Collectors.toUnmodifiableSet()));
        assertThat(actual.toString()).isEqualTo(expected.toString());
    }
}
