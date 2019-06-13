package lotto.application.lottoresult;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.WinningLottoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoDAOTest {
    WinningLottoDAO winningLottoDAO = null;

    @BeforeEach
    void setUp() {
        winningLottoDAO = WinningLottoDAO.getInstance();
    }

    @Test
    void winningLotto_save하기() {
        List<LottoNumber> winningTicketNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        LottoTicket winningTicket = new LottoTicket(winningTicketNumbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);
        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);
        WinningLottoDto winningLottoDto = LottoResultService.getWinningLottoDto(winningLotto);

        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        int latestRoundNum = lottoResultDAO.getLatestRoundNum();

        winningLottoDAO.saveWinningLotto(latestRoundNum, winningLottoDto);

        assertThat(winningLottoDAO.fetchWinningLotto(latestRoundNum)).isInstanceOf(WinningLottoDto.class);

        winningLottoDAO.deleteWinningLotto(latestRoundNum);
    }
}