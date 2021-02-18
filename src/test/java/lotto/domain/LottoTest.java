package lotto.domain;

import lotto.exception.LottoNumberCountException;
import lotto.exception.NumberScopeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    private static final Lotto winningLotto = new Lotto("1,2,3,4,5,6");

    @Test
    @DisplayName("보너스 볼 포함하는 경우")
    void bonusBallDuplicated() {
        Lotto lotto = new Lotto("11,12,13,14,15,16");
        assertEquals(lotto.isContainNumber(11), true);
    }

    @ParameterizedTest
    @DisplayName("입력된 번호의 수가 6개가 아닐 경우")
    @ValueSource(strings = {"1,2,3,4,5,6,7", "1,2,3"})
    void numbersCountValid(String numbers) {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(LottoNumberCountException.class);
    }

    @ParameterizedTest
    @DisplayName("입력된 번호의 수가 1 ~ 45의 수가 아닐 경우")
    @ValueSource(strings = {"1,2,3,4,5,6,46", "0,1,2,3,4,5"})
    void numbersScopeValid(String numbers) {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(numbers);
        }).isInstanceOf(NumberScopeException.class);
    }
}
