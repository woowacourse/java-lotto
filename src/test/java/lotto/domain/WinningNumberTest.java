package lotto.domain;

import lotto.exceptions.LottoNumberDuplicatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {
    private Lotto winningNumber;

    @BeforeEach
    void setUp() {
        winningNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }


    @Test
    void initWinningNumberTest() {
        BonusNumber bonusNumber = new BonusNumber(7);

        assertThat(new WinningNumber(winningNumber, bonusNumber)).isNotNull();
    }

    @Test
    void initExceptionWinningNumberTest() {
        BonusNumber duplicatedBonusNumber = new BonusNumber(4);

        assertThatThrownBy(() -> new WinningNumber(winningNumber, duplicatedBonusNumber))
                .isInstanceOf(LottoNumberDuplicatedException.class)
                .hasMessageContaining("중복");
    }

}