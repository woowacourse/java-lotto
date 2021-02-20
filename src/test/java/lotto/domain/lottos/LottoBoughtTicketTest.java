package lotto.domain.lottos;

import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class LottoBoughtTicketTest {

    private LottoWinner lottoWinner;

    @BeforeEach
    void setUp() {
        LottoTicket lottoWinnerTicket = new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );
        LottoBonusNumber lottoBonusNumber = LottoBonusNumber.of("7", lottoWinnerTicket);

        lottoWinner = new LottoWinner(lottoWinnerTicket, lottoBonusNumber);
    }

    @Test
    @DisplayName("구매한 로또 티켓 생성된다.")
    public void createBoughtTicketTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );

        assertThat(lottoBoughtTicket).isInstanceOf(LottoBoughtTicket.class);
    }

    @Test
    @DisplayName("1등 매칭 된다.")
    public void matchFirstPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIRST_PLACE);
    }

    @Test
    @DisplayName("2등 매칭 된다.")
    public void matchSecondPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(7)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SECOND_PLACE);
    }

    @Test
    @DisplayName("3등 매칭 된다.")
    public void matchThirdPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(8)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.THIRD_PLACE);
    }

    @Test
    @DisplayName("4등 매칭 된다.")
    public void matchFourthPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(8),
                        new LottoNumber(9)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FOURTH_PLACE);
    }

    @Test
    @DisplayName("5등 매칭 된다.")
    public void matchFifthPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(8),
                        new LottoNumber(9),
                        new LottoNumber(10)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.FIFTH_PLACE);
    }

    @Test
    @DisplayName("6등 매칭 된다.")
    public void matchSixthPriceTest() {
        LottoBoughtTicket lottoBoughtTicket = new LottoBoughtTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(11),
                        new LottoNumber(22),
                        new LottoNumber(33),
                        new LottoNumber(45)
                )
        );

        LottoRank lottoRank = lottoBoughtTicket.getRank(lottoWinner);

        assertThat(lottoRank).isEqualTo(LottoRank.SIXTH_PLACE);
    }
}