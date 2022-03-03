package lotto.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.domain.enumeration.BallType;
import lotto.domain.generator.AutoLottoTicketGenerator;
import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("로또 티켓 생성 시점에 로또 번호 생성을 위한 전략을 활용한다.")
    @Test
    void checkNormalCase() {
        // given & when & then
        assertThatCode(() -> new LottoTicket(new AutoLottoTicketGenerator()))
                .doesNotThrowAnyException();
    }

    @DisplayName("Getter로 꺼내오는 리스트를 변경할 경우 예외를 던진다.")
    @Test
    void checkImmutable() {
        // given
        LottoTicket lottoTicket = new LottoTicket(new AutoLottoTicketGenerator());

        List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        // when & then
        assertThatThrownBy(() -> lottoNumbers.add(LottoNumber.from(0)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoTicket을 기반으로 당첨 번호가 존재하는지 유무를 반환한다.")
    @Test
    void checkWinningNumber() {
        // given
        LottoTicket lottoTicket = new LottoTicket(() -> getLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        WinningNumber winningNumber = new WinningNumber(LottoNumber.from(2), BallType.BONUS);

        // when
        boolean result = lottoTicket.isSame(winningNumber);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("로또 티켓의 개수가 6개인지 확인한다.")
    @Test
    void checkWinningNumberSize() {
        // given
        List<LottoNumber> lottoNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7));

        // when & then
        assertThatThrownBy(() -> new LottoTicket(() -> lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 중복을 검증한다.")
    @Test
    void checkWinningNumberDuplication() {
        // given
        List<LottoNumber> lottoNumbers = getLottoNumbers(List.of(1, 2, 3, 4, 5, 5));

        // when & then
        assertThatThrownBy(() -> new LottoTicket(() -> lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private List<LottoNumber> getLottoNumbers(List<Integer> values) {
        return values.stream()
                .map(LottoNumber::from)
                .collect(toList());
    }
}