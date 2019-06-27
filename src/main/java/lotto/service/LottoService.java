package lotto.service;

import lotto.model.Lotto;
import lotto.model.dao.LottoDAO;
import lotto.model.dao.ResultDAO;
import lotto.model.dao.RoundInfoDAO;
import lotto.model.dto.ResultDTO;

import java.util.List;

public class LottoService {
        public static void insertResult(ResultDTO resultDTO) {
                int roundId = RoundInfoDAO.getInstance().selectRoundId();
                ResultDAO.getInstance().insertResult(roundId, resultDTO);
        }

        public static void insertLottos(List<Lotto> lottos) {
                int roundId = RoundInfoDAO.getInstance().selectRoundId();
                for(Lotto lotto : lottos){
                        LottoDAO.getInstance().insertLotto(roundId, lotto.toString());
                }
        }
}
