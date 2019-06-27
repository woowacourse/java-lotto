package lotto.service;

import lotto.dao.LottoResultDao;
import lotto.domain.lotto.Result;
import lotto.domain.paymentinfo.Payment;
import lotto.service.dto.RankingDto;
import lotto.service.dto.ResultDto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class LottoResultService {
    private static final LottoResultDao LOTTO_RESULT_DAO = LottoResultDao.getInstance();

    private LottoResultService() {
    }

    private static class LottoResultServiceHolder {
        private static final LottoResultService INSTANCE = new LottoResultService();
    }

    public static LottoResultService getInstance() {
        return LottoResultServiceHolder.INSTANCE;
    }

    public List<ResultDto> selectAllLottoResult() {
        List<ResultDto> lottoGames = LOTTO_RESULT_DAO.selectAllLottoResult();

        lottoGames.forEach(resultDto -> {
            resultDto.setTotalWinningMoney(new Result(resultDto.getLottoScore()).calculateTotalWinningMoney());
            resultDto.setEarningRate(resultDto.getTotalWinningMoney() / resultDto.getPayment());
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

    public int insertLottoResult(ResultDto resultDto) {
        return LOTTO_RESULT_DAO.insertLottoResult(resultDto);
    }

    public ResultDto selectLottoResult(int round) {
        ResultDto resultDto = LOTTO_RESULT_DAO.selectLottoResult(round);
        Result result = new Result(resultDto.getLottoScore());
        resultDto.setTotalWinningMoney(result.calculateTotalWinningMoney());
        resultDto.setEarningRate(result.calculateEarningsRate(new Payment(resultDto.getPayment())));
        return resultDto;
    }
}
