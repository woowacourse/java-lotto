package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
    @Test
    void generate_winning_numbers() {
        assertThatCode(() -> new WinningNumbers("1,2,3,4,5,6", "7"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 보너스볼과 로또넘버가 중복되는 경우")
    void generate_duplicated_case() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,6", "6"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 보너스볼이 숫자가 아닌경우")
    void generate_duplicated_case2() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,6", "s"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 로또넘버가 6개가 아닌 경우")
    void generate_duplicated_case3() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5", "6"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 보너스볼이 1개가 아닌 경우")
    void generate_duplicated_case4() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,6", "7,8"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 보너스볼 값이 1~45 사이가 아닌 경우")
    void generate_duplicated_case5() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,4,5,6", "46"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("1등 랭크 반환")
    void getRank1() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,6");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.FIRST_PLACE);
    }

    @Test
    @DisplayName("1등 - 순서 일정하지 않을 경우 랭크 반환")
    void count_same_numbers4() {
        WinningNumbers winningNumbers = new WinningNumbers("3,2,1,6,5,4", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,6");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.FIRST_PLACE);
    }


    @Test
    @DisplayName("2등 랭크 반환")
    void getRank2() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,7");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.SEC0ND_PLACE);
    }

    @Test
    @DisplayName("3등 랭크 반환")
    void getRank3() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,12");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.THIRD_PLACE);
    }

    @Test
    @DisplayName("4등 랭크 반환")
    void getRank4() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,12,13");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.FOURTH_PLACE);
    }

    @Test
    @DisplayName("5등 랭크 반환")
    void getRank5() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("1,2,3,12,13,14");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.FIFTH_PLACE);
    }

    @Test
    @DisplayName("NONE 랭크 반환")
    void getRank_none() {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket("11,12,13,14,15,16");

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(Rank.UNRANKED);
    }
}