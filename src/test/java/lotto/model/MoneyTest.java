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
    @DisplayName("범위 안에 숫자를 입력 안할때")
    void Payment_Over_Range() {
        assertThatThrownBy(() -> {
            new Money("0");
        }).isInstanceOf(OverRangeException.class)
            .hasMessage("범위를 벗어났습니다.");
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
    @DisplayName("상금계산")
    void getYield() {
        Money money = new Money("10000");
        LottoManager.lottoResultMap.put("THREE", 1);
        System.out.println(LottoManager.lottoResultMap);
        assertThat(money.getYield()).isEqualTo(50);
    }
}
