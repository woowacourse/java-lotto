package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Number;
import lotto.exception.LottoAnnouncementException;
import lotto.exception.NumberException;
import lotto.utility.NumberListTranslator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoAnnouncementTest {

    @Test
    @DisplayName("로또 당첨 번 발표 성공")
    void successAnnouncement() {
        List<Number> announcedNumbers =
            NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto winnerLotto = new Lotto(announcedNumbers);
        Number announcedBonusNumber = Number.from(7);
        LottoAnnouncement lottoannouncement = new LottoAnnouncement(announcedNumbers,
            announcedBonusNumber);
        assertThat(lottoannouncement.getWinners()).isEqualTo(winnerLotto);
        assertThat(lottoannouncement.getBonusNumber()).isEqualTo(announcedBonusNumber);
    }

    @Test
    @DisplayName("로또 당첨 번호 중복")
    void overlappedAnnouncedNumber() {
        List<Number> announcedNumbers =
            NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 5));
        Number announcedBonusNumber = Number.from(7);
        assertThatThrownBy(() -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(LottoAnnouncement.OVERLAPPED_WINNER_MESSAGE);
    }

    @Test
    @DisplayName("로또 당첨 번호와 보너스 번호 중복")
    void overlappedBonusNumber() {
        List<Number> announcedNumbers =
            NumberListTranslator.translateIntToNumber(Arrays.asList(1, 2, 3, 4, 5, 6));
        Number announcedBonusNumber = Number.from(6);
        assertThatThrownBy(() -> new LottoAnnouncement(announcedNumbers, announcedBonusNumber))
            .isInstanceOf(LottoAnnouncementException.class)
            .hasMessageContaining(LottoAnnouncement.OVERLAPPED_BONUS_MESSAGE);
    }
}
