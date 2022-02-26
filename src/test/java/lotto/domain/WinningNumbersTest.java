package lotto.domain;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.enumeration.Rank;
import lotto.domain.vo.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    @DisplayName("당첨 번호 개수 만큼 당첨 번호를 생성한다.")
    @Test
    void 당첨_번호_생성_확인() {
        // given
        List<LottoNumber> normalWinningNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        LottoNumber bonusBall = new LottoNumber(7);

        // when & then
        assertThatCode(() -> WinningNumbers.create(normalWinningNumbers, bonusBall))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_2등() {
        // given
        List<LottoNumber> normalWinningNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        LottoNumber bonusBall = new LottoNumber(7);

        WinningNumbers winningNumbers = WinningNumbers.create(normalWinningNumbers, bonusBall);

        LottoTicket lottoTicket = new LottoTicket(() -> getLottoNumbers(List.of(1, 2, 3, 4, 7, 8)));

        // when
        Rank rank = winningNumbers.compare(lottoTicket);

        System.out.println("rank = " + rank);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_3등() {
        // given
        List<LottoNumber> normalWinningNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        LottoNumber bonusBall = new LottoNumber(7);

        WinningNumbers winningNumbers = WinningNumbers.create(normalWinningNumbers, bonusBall);

        LottoTicket lottoTicket = new LottoTicket(() -> getLottoNumbers(List.of(1, 2, 3, 4, 5, 8)));

        // when
        Rank rank = winningNumbers.compare(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("당첨 번호에 중복된 번호가 있는지 검증")
    @Test
    public void 당첨_번호_보너스볼_중복_확인() {
        // given
        List<LottoNumber> normalWinningNumber = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        LottoNumber bonusWinningNumber = new LottoNumber(6);

        // when & then
        Assertions.assertThatThrownBy(() -> WinningNumbers.create(normalWinningNumber, bonusWinningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 번호가 있는지 검증")
    @Test
    public void 당첨_번호_중복_확인() {
        // given
        List<LottoNumber> normalWinningNumber = getLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        LottoNumber bonusWinningNumber = new LottoNumber(6);

        //given & when & then
        Assertions.assertThatThrownBy(() -> WinningNumbers.create(normalWinningNumber, bonusWinningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<LottoNumber> getLottoNumbers(List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }
}