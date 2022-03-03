package lotto.domain;

import static lotto.domain.LottoTestDataGenerator.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("당첨 번호 개수 만큼 당첨 번호를 생성한다.")
    @Test
    void 당첨_번호_생성_확인() {
        // given
        int bonusBall = 7;

        // when & then
        assertDoesNotThrow(() -> new WinningNumber(new LottoTicket(generateNumbers()), LottoNumber.of(bonusBall)));
    }

    @DisplayName("당첨 번호의 총 개수가 7개가 아닌 경우 예외를 던진다.")
    @Test
    void 당첨_번호_개수_확인() {
        // given
        List<LottoNumber> winningNumbers = parseLottoNumbers(List.of(1, 2, 3, 4, 5));
        int bonusBall = 7;

        // when & then
        assertThatThrownBy(() -> new WinningNumber(new LottoTicket(winningNumbers), LottoNumber.of(bonusBall)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("번호의 갯수가 적절하지 않습니다. 또한 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호는 서로 중복되는 경우 예외를 던진다.")
    @Test
    void 당첨_번호_중복_확인() {
        List<LottoNumber> winningNumbers = parseLottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        int bonusBall = 1;

        // when & then
        assertThatThrownBy(() -> new WinningNumber(new LottoTicket(winningNumbers), LottoNumber.of(bonusBall)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_2등() {
        // given
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(parseLottoNumbers(List.of(1, 2, 3, 4, 5, 6))),
                LottoNumber.of(7));
        LottoTicket lottoTicket = new LottoTicket(parseLottoNumbers(List.of(1, 2, 3, 4, 5, 7)));

        // when
        Rank rank = winningNumber.compare(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_3등() {
        // given
        WinningNumber winningNumber = new WinningNumber(new LottoTicket(parseLottoNumbers(List.of(1, 2, 3, 4, 5, 6))),
                LottoNumber.of(7));
        LottoTicket lottoTicket = new LottoTicket(parseLottoNumbers(List.of(1, 2, 3, 4, 5, 8)));

        // when
        Rank rank = winningNumber.compare(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }
}