package lotto.service;

import lotto.dao.LottoWinningDAO;
import lotto.domain.WinningLotto;
import lotto.domain.vo.LottoResult_VO;
import lotto.dto.ResultLottoDTO;

import java.util.List;

public class LottoWinningService {

    public static int insertResult(LottoResult_VO lottoResult_vo, WinningLotto winningLotto){
        ResultLottoDTO lottoDTO = new ResultLottoDTO();
        lottoDTO.setWinningLotto(winningLotto.getWinningNumbers().toString());
        lottoDTO.setRank(lottoResult_vo.getRank());
        lottoDTO.setPrize(lottoResult_vo.getPrize());
        lottoDTO.setIncomeRate(lottoResult_vo.dividendRate());

        return LottoWinningDAO.getInstance().addResult(lottoDTO);
    }

    public static List<String> findByResultRound(int round){
        return LottoWinningDAO.getInstance().findByResultRound(round);
    }
}
