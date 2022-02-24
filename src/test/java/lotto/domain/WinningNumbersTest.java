package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import lotto.domain.enumeration.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    private static final String SEPARATOR = ", ";

    @DisplayName("당첨 번호 개수 만큼 당첨 번호를 생성한다.")
    @Test
    void 당첨_번호_생성_확인() {
        // given
        List<String> normalWinningNumbers = getStrings("1, 2, 3, 4, 5, 6");

        String bonusBall = "7";

        // when & then
        assertThatCode(() -> WinningNumbers.create(normalWinningNumbers, bonusBall))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoTicket 을 기반으로 당첨 정보를 반환한다.")
    @Test
    void 당첨_정보_확인_2등() {
        // given
        List<String> normalWinningNumbers = getStrings("1, 2, 3, 4, 5, 6");

        String bonusBall = "7";

        WinningNumbers winningNumbers = WinningNumbers.create(normalWinningNumbers, bonusBall);
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 7, 8));

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
        List<String> normalWinningNumbers = getStrings("1, 2, 3, 4, 5, 6");

        String bonusBall = "7";

        WinningNumbers winningNumbers = WinningNumbers.create(normalWinningNumbers, bonusBall);
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(1, 2, 3, 4, 5, 8));

        // when
        Rank rank = winningNumbers.compare(lottoTicket);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("당첨 번호에 중복된 번호가 있는지 검증")
    @Test
    public void 당첨_번호_보너스볼_중복_확인() {
        //given & when & then
        Assertions.assertThatThrownBy(() -> WinningNumbers.create(List.of("1, 2, 3, 4, 5, 6"), "6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 번호가 있는지 검증")
    @Test
    public void 당첨_번호_중복_확인() {
        //given & when & then
        Assertions.assertThatThrownBy(() -> WinningNumbers.create(List.of("1, 2, 3, 4, 5, 5"), "6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정수가 아닌 입력에 대한 테스트")
    @Test
    public void 정수_아닌_입력_테스트() {
        // given
        List<String> normalWinningNumbers = getStrings("일, 이, 삼, 사, 오, 육");

        String bonusBall = "칠";

        // when & then
        Assertions.assertThatThrownBy(() -> WinningNumbers.create(normalWinningNumbers, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<String> getStrings(String numbers) {
        List<String> normalWinningNumbers = Arrays.asList(numbers.split(SEPARATOR));
        return normalWinningNumbers;
    }
}