package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.exception.LottoTicketException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    @DisplayName("로또숫자들이 6개가 아닐 경우 예외 발생")
    @Test
    void wrongSizeOfLottoNumbers() {
        Assertions.assertThatThrownBy(() -> {
            createLottoTicket("1,2,3,4,5");
        }).isInstanceOf(LottoTicketException.class)
                .hasMessage("로또의 숫자는 6개여야 합니다.");
    }

    @DisplayName("로또숫자들이 중복될 경우 예외 발")
    @Test
    void duplicatedLottoNumbers() {
        Assertions.assertThatThrownBy(() -> {
            createLottoTicket("1,2,3,4,5,5");
        }).isInstanceOf(LottoTicketException.class)
                .hasMessage("로또의 숫자는 중복될 수 없습니다.");
    }

    @DisplayName("로또티켓을 당첨번호와 비교해서 순위를 반환")
    @Test
    void compareLottoTicket() {
        LottoTicket lottoTicket = createLottoTicket("1,2,3,4,5,6");

        LottoTicket winningLottoTicket = createLottoTicket("1,2,3,10,11,12");
        LottoNumber bonusNumber = new LottoNumber(13);

        assertThat(lottoTicket.compare(winningLottoTicket, bonusNumber)).isEqualTo(Rank.FIFTH);
    }

    private static LottoTicket createLottoTicket(String numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }
}