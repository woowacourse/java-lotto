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
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Test
    void null_check() {
        assertThrows(NullPointerException.class, () -> {
            new LottoTicket(null);
        });
    }

    @Test
    void 중복_번호() {
        lottoNumbers.add(new LottoNumber(5));
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 번호_개수_6개_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 당첨번호개수() {
        List<LottoNumber> numbers1 = new ArrayList<>(lottoNumbers);
        numbers1.add(new LottoNumber(6));

        List<LottoNumber> numbers2 = new ArrayList<>(lottoNumbers);
        numbers2.add(new LottoNumber(7));
        assertThat( new LottoTicket(numbers1)
                .getMatchedNumbersCount( new LottoTicket(numbers2))).isEqualTo(5);
    }
}