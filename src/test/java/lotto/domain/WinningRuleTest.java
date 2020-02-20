package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

public class WinningRuleTest {
    List<Integer> inputNumbers;

    @BeforeEach
    void initiate() {
        inputNumbers = new ArrayList<>();
        inputNumbers.add(1);
        inputNumbers.add(2);
        inputNumbers.add(3);
        inputNumbers.add(4);
        inputNumbers.add(5);
        inputNumbers.add(6);
    }

    @Test
    @DisplayName("정상 입력")
    void winningRule() {
        Assertions.assertThat(new WinningRule(inputNumbers, 7))
                .isInstanceOf(WinningRule.class);
    }

    @ParameterizedTest
    @DisplayName("범위 외의 값이 들어간 경우")
    @ValueSource(ints = {0, 46})
    void winningRule_outOfRange(int bonusBall) {
        Assertions.assertThatThrownBy(() -> new WinningRule(inputNumbers, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number out of range.");
    }

    @Test
    @DisplayName("중복된 숫자가 들어간 경우")
    void winningRule_duplicate() {
        Assertions.assertThatThrownBy(() -> new WinningRule(inputNumbers, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Duplicate exist.");
    }

    @Test
    @DisplayName("당첨 결과 계산하는 테스트")
    void calculateRank() {
        WinningRule winningRule = new WinningRule(inputNumbers, 7);
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(inputNumbers));
        winningRule.calculateRank(new PurchaseLottos(lottos));
        Assertions.assertThat(Rank.FIRST_RANK.count)
                .isEqualTo(1);
    }
}
