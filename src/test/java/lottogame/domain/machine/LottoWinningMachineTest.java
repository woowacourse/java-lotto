package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWinningMachineTest {

    private LottoWinningMachine lottoWinningMachine;

    @BeforeEach
    void setUp() {
        LottoNumbers winningNumbers = new LottoNumbers();
        LottoNumber bonusNumber = new LottoNumber("7");

        for (int i = 1; i <= 6; ++i) {
            winningNumbers.add(new LottoNumber(i + ""));
        }

        lottoWinningMachine = new LottoWinningMachine(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 번호와 티켓 번호 비교 결과가 올바른지")
    void validMatchResult() {

        LottoTicket lottoTicket = new LottoTicket() {
            @Override
            public List<LottoNumber> getLottoNumbers() {
                LottoNumbers drawingNumbers = new LottoNumbers();

                for (int i = 1; i <= 6; ++i) {
                    drawingNumbers.add(new LottoNumber(i + ""));
                }
                return drawingNumbers.toList();
            }
        };

        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isEqualTo(6);
        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isNotEqualTo(5);
    }

    @Test
    @DisplayName("보너스 번호 일치 확인")
    void checkBonusLottoNumber() {
        assertThat(lottoWinningMachine.isMatchBonusNumber(new LottoNumber("7"))).isTrue();
        assertThat(lottoWinningMachine.isMatchBonusNumber(new LottoNumber("6"))).isFalse();
    }
}
