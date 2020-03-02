package lotto.model;

import lotto.exception.NotMultipleOfThousandException;
import lotto.exception.NotNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoneyTest {
    @Test
    @DisplayName("Payment 생성자 예외처리 - 숫자가 아닐때")
    void Should_Exception_isNotNumber() {
        assertThatThrownBy(() -> {
            new Money("a");
        }).isInstanceOf(NotNumberException.class)
        .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("천 단위 입력이 안될때")
    void Should_Exception_isNotUnitThousand() {
        assertThatThrownBy(() -> {
            new Money("9999");
        }).isInstanceOf(NotMultipleOfThousandException.class)
            .hasMessage("천 단위로 입력하세요.");
    }

    @Test
    @DisplayName("수익률 계산")
    void test_Yield() {
        Money money = new Money("10000");
        Ticket ticket = new Ticket("1, 2, 3, 4, 5, 6");
        WinLottoNumbers winLottoNumbers = new WinLottoNumbers("1, 2, 3, 8, 9, 10", "7");
        LottoResult lottoResult = new LottoResult();
        lottoResult.resultCount(ticket, winLottoNumbers);
        assertThat(money.getYield(lottoResult)).isEqualTo(50);
    }
}
