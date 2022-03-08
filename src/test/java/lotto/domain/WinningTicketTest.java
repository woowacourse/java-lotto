package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningTicketTest {

    private LottoLine baseLottoLine;

    @BeforeEach
    public void setUp() {
        baseLottoLine = new LottoLine(new ArrayList<>(
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
        Assertions.assertThatCode(() -> new WinningTicket(baseLottoLine, bonusball))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열로 당첨번호를 생성한다.")
    public void createWinningTicketFromString() {
        // given
        String inputLottoLine = "1,2,3,4,5,6";
        String inputBonusBall = "7";
        // when
        WinningTicket winningTicket = WinningTicket.from(inputLottoLine, inputBonusBall);
        // then
        Assertions.assertThat(winningTicket).isNotNull();
    }

    @Test
    @DisplayName("보너스볼과 로또번호는 중복될 수 없다.")
    public void throwsExceptionWithDuplicatedLottoNumberAndBonusBall() {
        // when
        LottoNumber bonusBall = LottoNumber.from(6);

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new WinningTicket(baseLottoLine, bonusBall));
    }

    @Test
    @DisplayName("당첨번호와 로또번호를 비교할 수 있다.")
    public void compareWinningTicketWithLottoTicket() {
        // given
        LottoNumber bonusball = LottoNumber.from(7);
        WinningTicket winningTicket = new WinningTicket(baseLottoLine, bonusball);
        // when
        LottoLine lottoLine = new LottoLine(List.of(
            LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
            LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6)
        ));
        LottoRank result = winningTicket.compareLine(lottoLine);
        // then
        Assertions.assertThat(result).isEqualTo(LottoRank.FIRST);
    }

}
