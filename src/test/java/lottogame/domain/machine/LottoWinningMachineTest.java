package lottogame.domain.machine;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoWinningMachineTest {

    private LottoWinningMachine lottoWinningMachine;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        LottoNumber bonusNumber = LottoNumber.of(7);

        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(LottoNumber.of(i));
        }

        LottoNumbers winningNumbers = new LottoNumbers(lottoNumberGroup);
        lottoWinningMachine = new LottoWinningMachine(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 번호와 티켓 번호 비교 결과가 올바르면 통과한다")
    void validMatchResult() {
        LottoTicket lottoTicket = LottoTicket.of(new LottoNumbers("1,2,3,4,5,6"));

        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isEqualTo(6);
        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isNotEqualTo(5);
    }

    @Test
    @DisplayName("보너스 번호가 일치할 경우 통과한다")
    void checkBonusLottoNumber() {

        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int i = 2; i <= 7; ++i) {
            lottoNumberGroup.add(LottoNumber.of(i));
        }

        LottoNumbers numbers = new LottoNumbers(lottoNumberGroup);
        LottoTicket lottoTicket = LottoTicket.of(numbers);

        assertThat(lottoWinningMachine.isMatchBonusNumber(lottoTicket)).isTrue();
    }
}
