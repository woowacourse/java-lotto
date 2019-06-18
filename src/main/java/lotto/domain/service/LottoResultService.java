package lotto.domain.service;

import lotto.domain.dao.LottoResultDao;
import lotto.domain.dto.RankingDTO;
import lotto.domain.dto.ResultDTO;
import lotto.domain.lotto.Result;
import lotto.domain.paymentinfo.Payment;

import java.sql.SQLDataException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class LottoResultService {
    private LottoResultService() {
    }

    public static class LottoResultServiceHolder {
        private static final LottoResultService INSTANCE = new LottoResultService();
    }

    public static LottoResultService getInstance() {
        return LottoResultServiceHolder.INSTANCE;
    }

    public List<ResultDTO> selectAllLottoResult() throws SQLDataException {
        List<ResultDTO> lottoGames = LottoResultDao.getInstance().selectAllLottoResult();

        lottoGames.forEach(resultDTO -> {
            resultDTO.setTotalWinningMoney(new Result(resultDTO.getLottoScore()).calculateTotalWinningMoney());
            resultDTO.setEarningRate(resultDTO.getTotalWinningMoney() / resultDTO.getPayment());
        });
        return lottoGames;
    }

    public List<RankingDTO> createUserRanking(List<ResultDTO> lottoGames) {
        Map<String, Long> ranking = lottoGames.stream()
                .collect(groupingBy(ResultDTO::getName,
                        summingLong(ResultDTO::getTotalWinningMoney)));

        return ranking.keySet().stream()
                .map(key -> new RankingDTO(key, ranking.get(key))).sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    public int insertLottoResult(ResultDTO resultDTO) throws SQLDataException {
        return LottoResultDao.getInstance().insertLottoResult(resultDTO);
    }

    public ResultDTO selectLottoResult(int round) throws SQLDataException {
        ResultDTO resultDTO = LottoResultDao.getInstance().selectLottoResult(round);
        Result result = new Result(resultDTO.getLottoScore());
        resultDTO.setTotalWinningMoney(result.calculateTotalWinningMoney());
        resultDTO.setEarningRate(result.calculateEarningsRate(new Payment(resultDTO.getPayment())));
        return resultDTO;
    }
}
