package domain;

import Lotto.domain.Lotto;
import Lotto.domain.LottoNumber;
import Lotto.domain.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {
    @Test
    @DisplayName("당첨번호와 중복되지 않는 보너스넘버 입력했을 때")
    void rightInputInit() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 5, 10, 11, 34);
        Lotto winningNumber = new Lotto(winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(37);
        assertThat(new WinningNumber(winningNumber, bonusNumber)).isNotNull();
    }

    @Test
    @DisplayName("당첨번호와 중복되는 보너스넘버 입력했을 때")
    void wrongInputInit() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 5, 10, 11, 34);
        Lotto winningNumber = new Lotto(winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(10);
        assertThatThrownBy(() -> new WinningNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스넘버");
    }
}
