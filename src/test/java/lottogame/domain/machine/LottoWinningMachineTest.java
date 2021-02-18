package lottogame.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import lottogame.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWinningMachineTest {

    @Test
    @DisplayName("로또 당첨 번호와 티켓 번호 비교 결과가 올바른지")
    void validMatchResult() {
        LottoNumbers winningNumbers = new LottoNumbers();
        LottoNumber bonusNumber = new LottoNumber("7");

        for (int i = 1; i <= 6; ++i) {
            winningNumbers.add(new LottoNumber(i + ""));
        }

        LottoWinningMachine lottoWinningMachine = new LottoWinningMachine(winningNumbers,
            bonusNumber);

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
}
