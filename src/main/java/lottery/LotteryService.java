package lottery;

import domain.*;
import domain.lottonumber.LottoNumber;
import repository.IssuedLottoDao;
import repository.LottoInmemoryRepository;
import repository.StatisticsDao;

import java.util.List;

class LotteryService {
    static StatisticsDTO startLottery(List<Integer> winningNumbers, int bonusNumber) {
        StatisticsDao statisticsDao = StatisticsDao.getInstance();
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);
        WinningLotto winningLotto = LottoFactory.getWinningLotto(winningNumbers, bonusLottoNumber);

        int trial = statisticsDao.fetchLastTrial();
        IssuedLottos issuedLottos = IssuedLottoDao.fetchIssuedLottosInTrialOf(trial);
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);

        statisticsDao.save(statistics, winningLotto, statistics.calculateEarningRates());
        return LotteryAssembler.createStatisticsDTO(statistics);
    }
}
