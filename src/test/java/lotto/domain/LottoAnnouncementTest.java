package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.exception.LottoAnnouncementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoAnnouncementTest {

    private static final String OVERLAPPED_WINNER_MESSAGE = "당첨 번호가 중복되었습니다.";
    private static final String OVERLAPPED_BONUS_MESSAGE = "보너스 번호가 중복되었습니다.";
    private static final String EXCESS_NUMBER_MESSAGE = "범위를 벗어난 숫자입니다.";

    @Test
    @DisplayName("로또 당첨 번 발표 성공")
    void successAnnouncement() {
        List<Integer> announcedNumbers = Arrays.asList(1,2,3,4,5,6);
        int announcedBonusNumber = 7;
        LottoAnnouncement lottoannouncement = new LottoAnnouncement(announcedNumbers,
            announcedBonusNumber);
        assertThat(lottoannouncement.getWinners()).isEqualTo(announcedNumbers);
        assertThat(lottoannouncement.getBonusNumber()).isEqualTo(announcedBonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 번호 중복")
    void overlappedAnnouncedNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1,2,3,4,5,5);
        int announcedBonusNumber = 7;
        assertThatThrownBy( ()-> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(OVERLAPPED_WINNER_MESSAGE);
    }

    @Test
    @DisplayName("로또 당첨 번호와 보너스 번호 중복")
    void overlappedBonusNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1,2,3,4,5,6);
        int announcedBonusNumber = 6;
        assertThatThrownBy( () -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(OVERLAPPED_BONUS_MESSAGE);
    }

    @Test
    @DisplayName("범위 벗어나는 숫자 발")
    void checkExcessNumber() {
        List<Integer> announcedNumbers = Arrays.asList(1,2,3,4,5,6);
        int announcedBonusNumber = 46;
        assertThatThrownBy( () -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(EXCESS_NUMBER_MESSAGE);
    }
}
