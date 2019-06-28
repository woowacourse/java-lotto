package lotto.service;

import lotto.dao.LottoResultDao;
import lotto.domain.LottoResult;
import lotto.dto.LottoResultDto;

public class LottoResultService {
    private static final LottoResultDao dao = LottoResultDao.getDao();

    private LottoResultService() {

    }

    public static LottoResult insertLottoResult(int round) {
        LottoResult lottoResult = new LottoResult(dao.selectResult(round).getResults());
        LottoResultDto dto = new LottoResultDto(lottoResult);
        dao.insertResult(round, dto);
        return lottoResult;
    }

    public static LottoResult selectLottoResult(int round) {
        LottoResultDto dto = dao.selectResult(round);
        return new LottoResult(dto.getResults());
    }
}
