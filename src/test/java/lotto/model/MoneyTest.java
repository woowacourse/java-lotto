package lotto.model;

import lotto.controller.LottoManager;
import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import lotto.exception.OverRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoneyTest {
    @Test
    @DisplayName("Payment 생성자 예외처리 - 숫자가 아닐때")
    void Payment_NotNumber() {
        assertThatThrownBy(() -> {
            new Money("a");
        }).isInstanceOf(NotNumberException.class)
        .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("천 단위 입력이 안될때")
    void Payment_Not_Unit_K() {
        assertThatThrownBy(() -> {
            new Money("9999");
        }).isInstanceOf(NotMultipleOfThousandException.class)
            .hasMessage("천 단위로 입력하세요.");
    }

    @Test
    @DisplayName("수익률 계산")
    void getYield() {
        Money money = new Money("10000");
        LottoResult lottoResult = new LottoResult();
        lottoResult.updateResultCount(RankType.THREE);
        assertThat(money.getYield(lottoResult)).isEqualTo(50);
    }
}
