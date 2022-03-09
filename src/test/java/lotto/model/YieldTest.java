package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class YieldTest {

    @ParameterizedTest
    @CsvSource(value = {"2_000_000_000:true", "2_000_001_000:false"}, delimiter = ':')
    @DisplayName("수익률 1기준 이득 테스트")
    void isGainTest(int rawLottoMoney, boolean expected) {
        LottoMoney lottoMoney = new LottoMoney(rawLottoMoney, 0);
        Map<Rank, Long> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIRST, 1L);

        Yield yield = new Yield(lottoMoney, result);

        assertThat(yield.isGain()).isEqualTo(expected);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateTest() {
        LottoMoney lottoMoney = new LottoMoney(14000, 0);
        Map<Rank, Long> result = new EnumMap<>(Rank.class);
        result.put(Rank.FIFTH, 1L);
        Yield yield = new Yield(lottoMoney, result);

        assertThat(yield.getYield()).isCloseTo(0.35714f, Percentage.withPercentage(0.01));
    }
}
