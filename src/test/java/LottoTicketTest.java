import domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class LottoTicketTest {

    private static List<LottoNumber> numbers;
    private static LottoTicket lottoTicket;

    @BeforeAll
    static void lottoNumbersInit() {
        numbers = IntStream.of(1, 2, 3, 4, 5, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        lottoTicket = new LottoTicket(numbers);

        System.out.println(lottoTicket.getTicketNumbers());
    }

    @Test
    @DisplayName("로또 1등 당첨")
    void lottoFirstPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(7));

        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("로또 3등 당첨")
    void lottoThirdPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 7)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(8));

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("로또 2등 당첨")
    void lottoSecondPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 5, 7)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(6));

        assertThat(lottoRank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("로또 4등 당첨")
    void lottoFourthPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 3, 7, 8)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(6));

        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("로또 5등 당첨")
    void lottoFifthPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 4, 9, 7, 8)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(6));

        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("로또 당첨 실패")
    void lottoNonPlace() {
        List<LottoNumber> inputWinningNumbers = IntStream.of(2, 1, 10, 9, 7, 8)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(inputWinningNumbers);

        LottoRank lottoRank = lottoTicket.rank(lottoNumbers, LottoNumber.from(6));

        assertThat(lottoRank).isEqualTo(LottoRank.NOTHING);
    }
}
