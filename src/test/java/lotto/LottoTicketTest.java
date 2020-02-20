package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    @Test
    void 로또숫자들이_6개가_아닐_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            new LottoTicket(Arrays.asList(new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5)
            ));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 숫자는 6개여야 합니다.");
    }

    @Test
    void 로또숫자들이_중복될_경우_예외_발생() {
        Assertions.assertThatThrownBy(() -> {
            createLottoTicket("1,2,3,4,5,5");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 숫자는 중복될 수 없습니다.");
    }

    @Test
    void compare() {
        //given
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