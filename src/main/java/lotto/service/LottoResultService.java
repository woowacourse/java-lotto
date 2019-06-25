package lotto.service;

import lotto.dao.LottoResultDao;
import lotto.domain.LottoResult;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.dto.LottoResultDto;

public class LottoResultService {
    private static final LottoResultDao dao = LottoResultDao.getDao();

    private LottoResultService() {

    }

    public static LottoResult insertCurrentLottoResult() {
        UserLottos userLottos = UserLottoService.currentUserLottos();
        WinningLotto winningLotto = WinningLottoService.currentWinnigLotto();
        LottoResult lottoResult = userLottos.result(winningLotto);
        LottoResultDto dto = new LottoResultDto(lottoResult);
        dao.insertResult(dto);
        return lottoResult;
    }

    public static LottoResult selectLottoResult(int round) {
        return new LottoResult(dao.selectResult(round));
    }
}
