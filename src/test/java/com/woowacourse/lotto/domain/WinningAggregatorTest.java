package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningAggregatorTest {
    @Test
    void calculateEarningRate() {
        WinningAggregator aggregator = new WinningAggregator();
        aggregator.addResult(LottoResult.FIFTH);
        aggregator.addResult(LottoResult.FIFTH);
        aggregator.addResult(LottoResult.FOURTH);
        aggregator.addResult(LottoResult.NONE);
        aggregator.addResult(LottoResult.NONE);
        aggregator.addResult(LottoResult.NONE);
        assertThat(aggregator.calculateEarningRate(1000)).isEqualTo(9.0);
    }

    @Test
    void getResultCount() {
        WinningAggregator aggregator = new WinningAggregator();
        aggregator.addResult(LottoResult.SECOND);
        aggregator.addResult(LottoResult.FIRST);
        aggregator.addResult(LottoResult.NONE);
        aggregator.addResult(LottoResult.NONE);
        aggregator.addResult(LottoResult.NONE);
        assertThat(aggregator.getResultCount(LottoResult.FIRST)).isEqualTo(1);
        assertThat(aggregator.getResultCount(LottoResult.SECOND)).isEqualTo(1);
        assertThat(aggregator.getResultCount(LottoResult.THIRD)).isEqualTo(0);
        assertThat(aggregator.getResultCount(LottoResult.FOURTH)).isEqualTo(0);
        assertThat(aggregator.getResultCount(LottoResult.FIFTH)).isEqualTo(0);
        assertThat(aggregator.getResultCount(LottoResult.NONE)).isEqualTo(3);
    }
}
