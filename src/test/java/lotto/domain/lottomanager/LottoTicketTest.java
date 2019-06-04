package lotto.domain.lottomanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    private static List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = IntStream.rangeClosed(1, 5)
                .mapToObj(LottoNumber::createLottoNumber)
                .collect(Collectors.toList());
    }

    @Test
    void null_check() {
        assertThrows(NullPointerException.class, () -> {
            LottoTicket.createLottoTicket(null);
        });
    }

    @Test
    void 중복_번호() {
        lottoNumbers.add(LottoNumber.createLottoNumber(5));
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket.createLottoTicket(lottoNumbers);
        });
    }

    @Test
    void 번호_개수_6개_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket.createLottoTicket(lottoNumbers);
        });
    }

    @Test
    void 당첨번호개수() {
        List<LottoNumber> numbers1 = new ArrayList<>(lottoNumbers);
        numbers1.add(LottoNumber.createLottoNumber(6));

        List<LottoNumber> numbers2 = new ArrayList<>(lottoNumbers);
        numbers2.add(LottoNumber.createLottoNumber(7));
        assertThat(LottoTicket.createLottoTicket(numbers1)
                .getMatchedNumbersCount(LottoTicket.createLottoTicket(numbers2))).isEqualTo(5);
    }
}