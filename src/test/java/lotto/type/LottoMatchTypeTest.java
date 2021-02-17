package lotto.type;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMatchTypeTest {
    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 0개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Empty() {
        List<LottoMatchType> lottoMatchTypes
            = LottoMatchType.getLottoMatchType(1);
        Assertions.assertThat(lottoMatchTypes.size()).isEqualTo(0);
    }

    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 1개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Single() {
        List<LottoMatchType> lottoMatchTypes
            = LottoMatchType.getLottoMatchType(3);
        Assertions.assertThat(lottoMatchTypes.size()).isEqualTo(1);
    }

    @DisplayName("countMatchedNumbers를 통해 특정 LottoMatchType 찾기 - 2개")
    @Test
    void Should_Return_ExactLottoMatchType_When_CountMatchedNumbers_Double() {
        List<LottoMatchType> lottoMatchTypes
            = LottoMatchType.getLottoMatchType(5);
        Assertions.assertThat(lottoMatchTypes.size()).isEqualTo(2);
    }
}
