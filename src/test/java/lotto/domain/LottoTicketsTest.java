package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Prize;
import lotto.domain.result.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    private List<List<String>> lottoNumberStrings;
    private LottoTickets lottoTickets;
    private List<String> winningNumbers;

    @BeforeEach
    void init() {
        lottoNumberStrings = Arrays.asList(
                Arrays.asList("1", "2", "3", "4", "5", "7"),
                Arrays.asList("1", "2", "3", "4", "5", "8")
        );

        lottoTickets = lottoNumberStrings.stream().map(LottoTicket::manual)
                .collect(collectingAndThen(toList(), LottoTickets::new));

        winningNumbers = Arrays.asList("1", "2", "3", "4", "5", "7");
    }

    @Test
    @DisplayName("당첨 티켓 분류")
    void checkWinningTicket() {
        LottoTicket winningTicket = LottoTicket.manual(winningNumbers);
        LottoNumber bonusNumber = LottoNumber.valueOf(8);
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        LottoResult lottoResult = lottoTickets.checkPrizes(winningLotto);
        assertThat(lottoResult.lottoResult().get(0)).isEqualTo(Prize.FIRST_PRIZE);
        assertThat(lottoResult.lottoResult().get(1)).isEqualTo(Prize.SECOND_PRIZE);
    }

}
