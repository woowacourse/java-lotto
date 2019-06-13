package lotto.application.lottoresult;

import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

public class LottoResultService {
    public static void saveWinningLotto(WinningLottoDTO winningLottoDto) {
        int latestRoundNum = fetchCurrentRound();

        WinningLottoDAO winningLottoDAO = WinningLottoDAO.getInstance();
        winningLottoDAO.saveWinningLotto(latestRoundNum, winningLottoDto);
    }

    public static int fetchCurrentRound() {
        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        return lottoResultDAO.getLatestRoundNum();
    }

    public static WinningLottoDTO getWinningLottoDto(WinningLotto winningLotto) {
        return LottoResultAssembler.getWinningLottoDto(winningLotto);
    }
}
