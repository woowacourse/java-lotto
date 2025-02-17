package lotto.domain;

import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;
import static lotto.domain.Lotto.LOTTO_SIZE;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MatchResultTest {

    @DisplayName("MatchResult 정상 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,LOTTO_SIZE})
    void test_MatchResult(int matchCount) {
        // given
        boolean isBonus = false;

        //given
        MatchResult matchResult = new MatchResult(matchCount, isBonus);

        //then
        assertThat(matchResult).isNotNull();
    }

}