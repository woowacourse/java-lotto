package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.number.LottoNumberFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    @DisplayName("로또번호 리스트 생성 테스트")
    @Test
    void 로또번호_리스트_생성_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTicket testLottoNumbers = new LottoTicket(randomNumbers);

        assertThat(testLottoNumbers.list())
            .containsExactly(LottoNumberFactory.getInstance(1),
                LottoNumberFactory.getInstance(2),
                LottoNumberFactory.getInstance(3),
                LottoNumberFactory.getInstance(4),
                LottoNumberFactory.getInstance(5),
                LottoNumberFactory.getInstance(6));
    }

    @DisplayName("예외 처리 : 로또 번호 리스트의 개수가 6개가 아닐 경우")
    @Test
    void 로또번호_범위_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new LottoTicket(randomNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("예외 처리 : 로또 번호 리스트 요소 중 중복이 있을 경우")
    @Test
    void 로또번호_중복_예외_테스트() {
        List<Integer> randomNumbers = Arrays.asList(1, 2, 3, 4, 4, 5);

        assertThatThrownBy(() -> new LottoTicket(randomNumbers))
            .isInstanceOf(IllegalArgumentException.class);
    }
}