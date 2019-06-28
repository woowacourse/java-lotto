package lottery;

import domain.*;
import domain.lottonumber.LottoNumber;
import repository.LottoInmemoryRepository;

import java.util.List;

class LotteryService {
    static StatisticsDTO startLottery(List<Integer> winningNumbers, int bonusNumber) {
        LottoNumber bonusLottoNumber = LottoNumber.valueOf(bonusNumber);
        WinningLotto winningLotto = LottoFactory.getWinningLotto(winningNumbers, bonusLottoNumber);

        IssuedLottos issuedLottos = LottoInmemoryRepository.getIssuedLottos();
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);

        return LotteryAssembler.createStatisticsDTO(statistics);
    }
}
