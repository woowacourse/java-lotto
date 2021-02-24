package lotto.domain.lottos;

import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.service.LottoTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.lottos.LottoTicket.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    private LottoWinner lottoWinner;
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        LottoTicket lottoWinnerTicket = new LottoTicket(lottoNumbers);
        LottoBonusNumber lottoBonusNumber = LottoBonusNumber.of("7", lottoWinnerTicket);

        lottoWinner = new LottoWinner(lottoWinnerTicket, lottoBonusNumber);
    }

    @Test
    @DisplayName("로또 티켓을 생성한다.")
    public void createLottoTicketTest() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );

        assertThat(new LottoTicket(lottoNumbers1)).isInstanceOf(LottoTicket.class);
    }

    @ParameterizedTest(name = "Null은 생성자의 매개변수로 허용하지 않는다.")
    @NullSource
    public void nullNotAllowedTest(List<LottoNumber> lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(NullPointerException.class).hasMessage(NULL_ERROR_MESSAGE);
    }

    @ParameterizedTest(name = "빈값을 생성자의 매개변수로 허용하지 않는다.")
    @EmptySource
    public void emptyParameterTest(List<LottoNumber> lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(EMPTY_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("중복된 숫자가 있는지 검사")
    public void duplicateNumberTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(DUPLICATE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("숫자가 6개를 초과하면 예외발생 검사")
    public void numberCountGreaterThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(45)
        );

        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("숫자가 6개미만이면 예외발생 검사")
    public void numberCountLessThanSixThrowsExceptionTest() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class).hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("구매한 로또 티켓의 숫자가 잘 정렬 되있는지 검사")
    public void lottoTicketSortTest() {
        LottoTicket lottoTicket = LottoTicketService.createAutoLottoTicket();

        int number = 0;
        for (LottoNumber lottoNumber : lottoTicket.getLottoNumbers()) {
            assertThat(lottoNumber.getNumber() > number).isTrue();
            number = lottoNumber.getNumber();
        }
    }

    @Test
    @DisplayName("1등 매칭 된다.")
    public void matchFirstPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE);
    }

    @Test
    @DisplayName("2등 매칭 된다.")
    public void matchSecondPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(7)
                )
        );

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SECOND_PLACE);
    }

    @Test
    @DisplayName("3등 매칭 된다.")
    public void matchThirdPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(8)
                )
        );

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD_PLACE);
    }

    @Test
    @DisplayName("4등 매칭 된다.")
    public void matchFourthPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(8),
                        new LottoNumber(9)
                )
        );

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH_PLACE);
    }

    @Test
    @DisplayName("5등 매칭 된다.")
    public void matchFifthPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(8),
                        new LottoNumber(9),
                        new LottoNumber(10)
                )
        );

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH_PLACE);
    }

    @Test
    @DisplayName("6등 매칭 된다.")
    public void matchSixthPriceTest() {
        LottoTicket lottoTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(11),
                        new LottoNumber(22),
                        new LottoNumber(33),
                        new LottoNumber(45)
                )
        );

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SIXTH_PLACE);
    }
}
