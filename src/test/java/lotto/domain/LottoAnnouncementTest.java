package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.exception.LottoAnnouncementException;
import lotto.exception.NumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoAnnouncementTest {

    @Test
    @DisplayName("로또 당첨 번 발표 성공")
    void successAnnouncement() {
        List<Integer> announcedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Number> winnerNumbers = packWinnerNumbers(announcedNumbers);
        int announcedBonusNumber = 7;
        LottoAnnouncement lottoannouncement = new LottoAnnouncement(announcedNumbers,
            announcedBonusNumber);
        Number bonusNumber = new Number(announcedBonusNumber);
        assertThat(lottoannouncement.getWinners()).isEqualTo(winnerNumbers);
        assertThat(lottoannouncement.getBonusNumber()).isEqualTo(bonusNumber);
    }

    private List<Number> packWinnerNumbers(List<Integer> announcedWinnerNumbers) {
        List<Number> packWinnerNumbers = new ArrayList<>();
        for (int winnerNumber : announcedWinnerNumbers) {
            packWinnerNumbers.add(new Number(winnerNumber));
        }
        return packWinnerNumbers;
    }

    @Test
    @DisplayName("로또 당첨 번호 중복")
    void overlappedAnnouncedNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        int announcedBonusNumber = 7;
        assertThatThrownBy(() -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(LottoAnnouncement.OVERLAPPED_WINNER_MESSAGE);
    }

    @Test
    @DisplayName("로또 당첨 번호와 보너스 번호 중복")
    void overlappedBonusNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int announcedBonusNumber = 6;
        assertThatThrownBy(() -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(LottoAnnouncement.OVERLAPPED_BONUS_MESSAGE);
    }

    @Test
    @DisplayName("범위 벗어나는 숫자 발")
    void checkExcessNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int announcedBonusNumber = 46;
        assertThatThrownBy(() -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(NumberException.class)
            .hasMessageContaining(Number.EXCESS_NUMBER_MESSAGE);
    }
}
