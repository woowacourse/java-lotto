package lotto.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.enumeration.BallType;
import lotto.domain.generator.AutoLottoTicketGenerator;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketTest {

    @DisplayName("로또 티켓 생성 시점에 로또 번호 생성을 위한 전략을 활용한다.")
    @Test
    void 로또_티켓_정상_생성() {
        // given & when & then
        assertThatCode(() -> new LottoTicket(new AutoLottoTicketGenerator()))
                .doesNotThrowAnyException();
    }

    @DisplayName("Getter로 꺼내오는 리스트를 변경할 경우 예외를 던진다.")
    @Test
    void 로또_티켓_불변_검증() {
        // given
        LottoTicket lottoTicket = new LottoTicket(new AutoLottoTicketGenerator());

        // when
        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        // then
        assertThatThrownBy(() -> lottoNumbers.add(new LottoNumber(0)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoTicket을 기반으로 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void 당첨_번호_인지_확인() {
        // given
        LottoTicket lottoTicket = new LottoTicket(() -> List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));

        WinningNumber winningNumber = new WinningNumber(new LottoNumber(2), BallType.BONUS);

        // when
        boolean result = lottoTicket.isSame(winningNumber);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("로또 티켓의 개수가 6개인지 확인한다.")
    @Test
    void 당첨_번호_개수_확인() {
        // given
        List<LottoNumber> lottoNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7));

        // when & then
        assertThatThrownBy(() -> new LottoTicket(() -> lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 중복을 검증한다.")
    @Test
    void 당첨_번호_중복_확인() {
        // given
        List<LottoNumber> lottoNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        // when & then
        assertThatThrownBy(() -> new LottoTicket(() -> lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<LottoNumber> getLottoNumbers(List<Integer> values) {
        return values.stream()
                .map(LottoNumber::new)
                .collect(toList());
    }
}