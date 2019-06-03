package lotto.domain.generator;

import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottosGeneratorTest {
    List<String> lottoNostrings;

    @BeforeEach
    void setUp() {
        lottoNostrings = Arrays.asList("1,2,3,4,5,6", "3,2,11,4,5,6", "12,2,3,4,5,6");
    }

    @Test
    void 수동_입력이_총구매_횟수보다_많을_경우() {
        assertThrows(IllegalArgumentException.class,
                () -> LottosGenerator.of(lottoNostrings, 1));
    }

    @Test
    void 개수_맞는지_확인() {
        int countOfPurchase = 10;
        List<Lotto> lottos = LottosGenerator.of(lottoNostrings, countOfPurchase).generate();
        assertThat(countOfPurchase).isEqualTo(lottos.size());
    }
}