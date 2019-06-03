package lotto.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        lottoResult = new LottoResult();
    }

    @Test
    void LottoResult_초기화가_올바르게_되는지_체크() {
        assertThat(lottoResult.getCountsBy(Rank.FIRST)).isEqualTo(0);
    }

    @Test
    void count_하나를_증가시키는_메소드() {
        lottoResult.increaseOneCountBy(Rank.FIRST);
        assertThat(lottoResult.getCountsBy(Rank.FIRST)).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        lottoResult = null;
    }
}