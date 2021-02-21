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
        LottoNumber bonusNumber = new LottoNumber(7);

        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(new LottoNumber(i));
        }

        LottoNumbers winningNumbers = new LottoNumbers(lottoNumberGroup);
        lottoWinningMachine = new LottoWinningMachine(winningNumbers, bonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 번호와 티켓 번호 비교 결과가 올바르면 통과한다")
    void validMatchResult() {
        LottoTicket lottoTicket = new LottoTicket() {
            @Override
            public List<LottoNumber> getLottoNumbers() {
                List<LottoNumber> lottoNumberGroup = new ArrayList<>();
                for (int i = 1; i <= 6; ++i) {
                    lottoNumberGroup.add(new LottoNumber(i));
                }
                LottoNumbers drawingNumbers = new LottoNumbers(lottoNumberGroup);
                return drawingNumbers.toList();
            }
        };

        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isEqualTo(6);
        assertThat(lottoWinningMachine.countMatchedWinningNumber(lottoTicket)).isNotEqualTo(5);
    }

    @Test
    @DisplayName("보너스 번호가 일치할 경우 통과한다")
    void checkBonusLottoNumber() {
        LottoTicket lottoTicket = new LottoTicket() {
            @Override
            public List<LottoNumber> getLottoNumbers() {
                List<LottoNumber> lottoNumberGroup = new ArrayList<>();

                for (int i = 1; i <= 5; ++i) {
                    lottoNumberGroup.add(new LottoNumber(i + ""));
                }
                lottoNumberGroup.add(new LottoNumber("7"));
                LottoNumbers drawingNumbers = new LottoNumbers(lottoNumberGroup);
                return drawingNumbers.toList();
            }
        };

        assertThat(lottoWinningMachine.isMatchBonusNumber(lottoTicket)).isTrue();
    }
}
