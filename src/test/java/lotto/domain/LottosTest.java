package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("Lotto 추가 테스트")
    void testAdd() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6));
        Lottos lottos = new Lottos(Arrays.asList(new Lotto(numbers), new Lotto(numbers)));
        lottos.add(new Lotto(numbers));
        assertThat(lottos.lottos().size()).isEqualTo(3);
    }
}
