package lotto.domain.lottos;

import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.util.ManualNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static lotto.domain.lottos.LottoTicket.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {

    private LottoWinner lottoWinner;
    private String lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = "1,2,3,4,5,6";

        LottoTicket lottoWinnerTicket = LottoTicket.createManualLottoTicket(lottoNumbers);
        LottoBonusNumber lottoBonusNumber = LottoBonusNumber.of("7", lottoWinnerTicket);

        lottoWinner = new LottoWinner(lottoWinnerTicket, lottoBonusNumber);
    }

    @Test
    @DisplayName("로또 티켓의 번호를 자동으로 생성한다.")
    public void createLottoTicket() {
        assertThat(createAutoLottoTicket()).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("로또 티켓을 번호를 수동으로 생성한다.")
    public void createLottoWinnerTicket() {
        LottoTicket lottoWinnerTicket = createManualLottoTicket("1,2,3,4,5,6");

        assertThat(lottoWinnerTicket).isInstanceOf(LottoTicket.class);
    }

    @Test
    @DisplayName("당첨 번호를 5개 입력하면 IllegalArgumanetException 발생")
    public void invalidLottoNumbersTest() {
        assertThatThrownBy(() -> {
            createManualLottoTicket("1,2,3,4,5");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("당첨 번호 입력에 숫자이외의 것을 입력하면 NumberFormatException 발생")
    public void invalidNumberFormatTest() {
        assertThatThrownBy(() -> {
            createManualLottoTicket("1,2,3,4,이건문자열이다,6");
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage(String.format(ManualNumberGenerator.NUMBER_FORMAT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @ParameterizedTest(name = "Null은 생성자의 매개변수로 허용하지 않는다.")
    @NullSource
    public void nullNotAllowedTest(String lottoNumbers) {
        assertThatThrownBy(() -> LottoTicket.createManualLottoTicket(lottoNumbers))
                .isInstanceOf(NullPointerException.class).hasMessage(NULL_ERROR_MESSAGE);
    }

    @ParameterizedTest(name = "빈값을 생성자의 매개변수로 허용하지 않는다.")
    @EmptySource
    public void emptyParameterTest(String lottoNumbers) {
        assertThatThrownBy(() -> LottoTicket.createManualLottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(EMPTY_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("중복된 숫자가 있는지 검사")
    public void duplicateNumberTest() {
        String lottoNumbers = "1,2,3,4,5,5";

        assertThatThrownBy(() -> {
            LottoTicket.createManualLottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(DUPLICATE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("숫자가 6개를 초과하면 예외발생 검사")
    public void numberCountGreaterThanSixThrowsExceptionTest() {
        String lottoNumbers = "1,2,3,4,5,6,7";

        assertThatThrownBy(() -> {
            LottoTicket.createManualLottoTicket(lottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("숫자가 6개미만이면 예외발생 검사")
    public void numberCountLessThanSixThrowsExceptionTest() {
        String lottoNumbers = "1,2,3,4,5";

        assertThatThrownBy(() -> {
            LottoTicket.createManualLottoTicket(lottoNumbers);
        }).isInstanceOf(RuntimeException.class).hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("자동으로 생성한 로또 티켓의 숫자가 잘 정렬 되있는지 검사")
    public void autoLottoTicketSortTest() {
        LottoTicket lottoTicket = LottoTicket.createAutoLottoTicket();

        int number = 0;
        for (LottoNumber lottoNumber : lottoTicket.getLottoNumbers()) {
            assertThat(lottoNumber.getNumber() > number).isTrue();
            number = lottoNumber.getNumber();
        }
    }

    @Test
    @DisplayName("수동으로 생성한 로또 티켓의 숫자가 잘 정렬 되있는지 검사")
    public void manualLottoTicketSortTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,5,10,15,20,45");

        int number = 0;
        for (LottoNumber lottoNumber : lottoTicket.getLottoNumbers()) {
            assertThat(lottoNumber.getNumber() > number).isTrue();
            number = lottoNumber.getNumber();
        }
    }

    @Test
    @DisplayName("1등 매칭 된다.")
    public void matchFirstPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket(lottoNumbers);

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE);
    }

    @Test
    @DisplayName("2등 매칭 된다.")
    public void matchSecondPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,2,3,4,5,7");

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SECOND_PLACE);
    }

    @Test
    @DisplayName("3등 매칭 된다.")
    public void matchThirdPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,2,3,4,5,8");

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD_PLACE);
    }

    @Test
    @DisplayName("4등 매칭 된다.")
    public void matchFourthPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,2,3,4,8,9");

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH_PLACE);
    }

    @Test
    @DisplayName("5등 매칭 된다.")
    public void matchFifthPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,2,3,8,9,10");

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH_PLACE);
    }

    @Test
    @DisplayName("6등 매칭 된다.")
    public void matchSixthPriceTest() {
        LottoTicket lottoTicket = LottoTicket.createManualLottoTicket("1,2,40,41,42,45");

        LottoRank lottoRank = lottoTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SIXTH_PLACE);
    }
}
