package lotto.domain;

import static lotto.common.constant.Constant.LOTTO_NUMBER_COUNT;
import static lotto.common.exception.ErrorMessage.ERROR_MATCH_NUMBER_NOT_VALID;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MatchResultTest {

    @DisplayName("MatchResult 정상 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0,LOTTO_NUMBER_COUNT})
    void test_MatchResult(int matchCount) {
        // given
        boolean isBonus = false;

        //given
        MatchResult matchResult = new MatchResult(matchCount, isBonus);

        //then
        assertThat(matchResult).isNotNull();
    }

    @DisplayName("당첨번호와 일치하는 개수가 로또 번호개수를 넘어선다면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1,LOTTO_NUMBER_COUNT+1})
    void test_matchCountRangeError(int matchCount) {
        boolean isBonus = false;

        assertThatThrownBy(() -> {
            new MatchResult(matchCount, isBonus);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MATCH_NUMBER_NOT_VALID);

    }

}