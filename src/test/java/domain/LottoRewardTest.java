package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoRewardTest {

    @ParameterizedTest
    @CsvSource(value = {"6:2000000000:false", "5:30000000:true", "5:1500000:false", "0:0:false"}, delimiter = ':')
    @DisplayName("유효한 로또 리워드를 반환하는 경우")
    void returnLottoReward(int matchCount, int price, boolean hasBonus) {
        LottoReward lottoReward = LottoReward.find(matchCount, hasBonus);

        assertThat(lottoReward.getPrice()).isEqualTo(price);
    }
}
