package lotto.game;

import lotto.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.game.LottoCount.ERROR_MESSAGE_INVALID_AMOUNT;
import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_INPUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoCountTest {

    @Test
    @DisplayName("로또 개수 객체 생성")
    void lottoCountCreate() {
        LottoCount lottoCount = new LottoCount(new Money("10000"));
        assertThat(lottoCount).isEqualTo(new LottoCount(new Money("10000")));
    }

    @Test
    @DisplayName("구매 금액에 따른 로또 티켓 장수확인")
    void lottoCount() {
        LottoCount lottoCount = new LottoCount(new Money("14000"));
        assertThat(lottoCount.getLottoCount()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수 입력에 따른 LottoCount 생성")
    void manualLottoCountCreate() {
        Money purchaseMoney = new Money("14000");
        LottoCount lottoCount = new LottoCount("3");
        assertThat(lottoCount).isEqualTo(new LottoCount("3"));
    }

    @Test
    @DisplayName("검증: 숫자로 입력되어야 한다.")
    void checkNumber() {
        assertThatThrownBy(() ->
                new LottoCount("^")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("검증: 음수가 아니어야 한다.")
    void notNegative() {
        assertThatThrownBy(() ->
                new LottoCount("-1")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("검증: 구매 금액 보다 더 많은 로또를 살 수 없다.")
    void checkPossibleAmount() {
        Money purchaseMoney = new Money("14000");
        LottoCount lottoCount = new LottoCount(purchaseMoney);
        LottoCount manualLottoCount = new LottoCount("15");
        assertThatThrownBy(() ->
                lottoCount.consumeTicket(manualLottoCount)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ERROR_MESSAGE_INVALID_AMOUNT);
    }

    @Test
    @DisplayName("수동 구매 후 남은 구입 가능 횟수 확인")
    void checkRemainCount() {
        Money purchaseMoney = new Money("14000");
        LottoCount lottoCount = new LottoCount(purchaseMoney);
        LottoCount manualLottoCount = new LottoCount("3");
        LottoCount remainCount = lottoCount.consumeTicket(manualLottoCount);
        assertThat(remainCount).isEqualTo(new LottoCount("11"));
    }
}
