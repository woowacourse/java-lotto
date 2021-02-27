package domain.lotto;

import static lotto.domain.lotto.LottoTicket.LOTTO_TICKET_CREATE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.util.LottoMoney;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    LottoMoney lottoMoney;
    List<LottoLine> lottoLines;

    @BeforeEach
    void setUp() {
        lottoMoney = new LottoMoney(7_000);

        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
        );

        lottoLines = Arrays.asList(new LottoLine(lottoNumbers, true));
    }

    @Test
    @DisplayName("자동 로또 라인만 갖는 로또 티켓을 생성한다. (금액만 넣어준다.)")
    void testCreateAutoLottoLineLottoTicket() {
        assertThat(new LottoTicket(lottoMoney).getAutoLottoLineCount()).isEqualTo(7);
    }

    @Test
    @DisplayName("수동 로또 라인만 갖는 로또 티켓을 생성한다. (수동 로또 라인들을 넣어준다.)")
    void testCreateManualLottoLineLottoTicket() {
        LottoTicket lottoTicket = new LottoTicket(lottoLines);

        assertThat(lottoTicket.getManualLottoLineCount())
            .isEqualTo(1);
    }

    @Test
    @DisplayName("수동 로또 라인과 자동 로또 라인을 둘 다 갖는 로또 티켓을 생성한다.")
    void testCreateAutoManualLottoLineLottoTicket() {
        LottoTicket lottoTicket = new LottoTicket(lottoMoney, lottoLines);

        assertThat(lottoTicket.getAutoLottoLineCount()).isEqualTo(7);
        assertThat(lottoTicket.getManualLottoLineCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("구입 금액이 부족하고 입력받는 수동 로또 라인이 없으면 로또 티켓 생성시 예외가 발생한다.")
    void testCreateLottoTicketException() {
        assertThatThrownBy(() -> new LottoTicket(new LottoMoney(999), new ArrayList<>()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_TICKET_CREATE_ERROR);
    }

    @Test
    @DisplayName("로또 티켓과 당첨 로또 번호를 매칭시켜서 당첨 결과를 얻는다.")
    void testMatchLottoLines() {
        LottoTicket lottoTicket = new LottoTicket(lottoMoney, lottoLines);
        WinningLotto winningLotto = new WinningLotto(lottoLines.get(0), new LottoNumber(7));
        lottoTicket.matchLottoLines(winningLotto).getNumberOfRank(Rank.FIRST);
    }

}