package lotto.model;

import lotto.model.Lotto;
import lotto.model.LottoMatcher;
import lotto.model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMatcherTest {

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        assertThatThrownBy(() -> {
            new LottoMatcher(Arrays.asList(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }
}