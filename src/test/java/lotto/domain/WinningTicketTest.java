package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningTicketTest {

    private LottoTicket baseLottoTicket;

    @BeforeEach
    public void setUp() {
        baseLottoTicket = new LottoTicket(new ArrayList<>(
            List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6))
        ));
    }

    @Test
    @DisplayName("당첨번호를 생성한다.")
    public void createWinningTicket() {
        // given
        LottoNumber bonusball = LottoNumber.from(7);
        // then
        Assertions.assertThatCode(() -> new WinningTicket(baseLottoTicket, bonusball))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스볼과 로또번호는 중복될 수 없다.")
    public void throwsExceptionWithDuplicatedLottoNumberAndBonusBall() {
        // when
        LottoNumber bonusBall = LottoNumber.from(6);

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new WinningTicket(baseLottoTicket, bonusBall));
    }

    @Test
    @DisplayName("당첨번호와 로또번호를 비교할 수 있다.")
    public void compareWinningTicketWithLottoTicket() {
        // given
        LottoNumber bonusball = LottoNumber.from(7);
        WinningTicket winningTicket = new WinningTicket(baseLottoTicket, bonusball);
        // when
        LottoTicket purchaseTicket = new LottoTicket(List.of(
            LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
            LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6)
        ));
        LottoRank result = winningTicket.compare(purchaseTicket);
        // then
        Assertions.assertThat(result).isEqualTo(LottoRank.FIRST);
    }

}
