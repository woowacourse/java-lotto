package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    @SuppressWarnings("NonAsciiCharacters")
    void 정상_입력_생성자_테스트() {
        Assertions.assertThat(new WinningRule(inputNumbers, 7))
                .isInstanceOf(WinningRule.class);
    }

    @ParameterizedTest
    @SuppressWarnings("NonAsciiCharacters")
    @ValueSource(ints = {0, 46})
    void 로또_숫자_범위_외의_값이_들어온_경우(int bonusBall) {
        Assertions.assertThatThrownBy(() -> new WinningRule(inputNumbers, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Lotto number out of range.");
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 중복된_숫자가_들어온_경우() {
        Assertions.assertThatThrownBy(() -> new WinningRule(inputNumbers, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Duplicate exist.");
    }

    @Test
    @SuppressWarnings("NonAsciiCharacters")
    void 당첨_로또_계산하는_테스트() {
        WinningRule winningRule = new WinningRule(inputNumbers, 7);
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(inputNumbers));
        winningRule.calculateWinningResult(new PurchaseLottos(lottos));
        Assertions.assertThat(Rank.FIRST_RANK.count)
                .isEqualTo(1);
    }
}
