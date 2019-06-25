package lotto.service;

import lotto.dao.LottoResultDao;
import lotto.domain.dto.RankingDto;
import lotto.domain.dto.ResultDto;
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

    public List<ResultDto> selectAllLottoResult() throws SQLDataException {
        List<ResultDto> lottoGames = LottoResultDao.getInstance().selectAllLottoResult();

        lottoGames.forEach(resultDTO -> {
            resultDTO.setTotalWinningMoney(new Result(resultDTO.getLottoScore()).calculateTotalWinningMoney());
            resultDTO.setEarningRate(resultDTO.getTotalWinningMoney() / resultDTO.getPayment());
        });
        return lottoGames;
    }

    public List<RankingDto> createUserRanking(List<ResultDto> lottoGames) {
        Map<String, Long> ranking = lottoGames.stream()
                .collect(groupingBy(ResultDto::getName,
                        summingLong(ResultDto::getTotalWinningMoney)));

        return ranking.keySet().stream()
                .map(key -> new RankingDto(key, ranking.get(key))).sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    public int insertLottoResult(ResultDto resultDTO) throws SQLDataException {
        return LottoResultDao.getInstance().insertLottoResult(resultDTO);
    }

    public ResultDto selectLottoResult(int round) throws SQLDataException {
        ResultDto resultDTO = LottoResultDao.getInstance().selectLottoResult(round);
        Result result = new Result(resultDTO.getLottoScore());
        resultDTO.setTotalWinningMoney(result.calculateTotalWinningMoney());
        resultDTO.setEarningRate(result.calculateEarningsRate(new Payment(resultDTO.getPayment())));
        return resultDTO;
    }
}
