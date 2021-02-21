package lotto.domain;

import lotto.domain.number.LottoNumberFactory;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {
    @DisplayName("로또번호 리스트 생성 테스트")
    @Test
    void 로또번호_리스트_생성_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTicket testLottoNumbers = LottoTicket.createLottoTicket(randomNumbers);

        assertThat(testLottoNumbers.list())
                .containsExactly(LottoNumberFactory.of(1),
                        LottoNumberFactory.of(2),
                        LottoNumberFactory.of(3),
                        LottoNumberFactory.of(4),
                        LottoNumberFactory.of(5),
                        LottoNumberFactory.of(6));
    }

    @DisplayName("예외 처리 : 로또 번호 리스트의 개수가 6개가 아닐 경우")
    @Test
    void 로또번호_범위_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> LottoTicket.createLottoTicket(randomNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 로또 번호 리스트 요소 중 중복이 있을 경우")
    @Test
    void 로또번호_중복_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 4, 5);

        assertThatThrownBy(() -> LottoTicket.createLottoTicket(randomNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}