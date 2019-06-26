package lotto.dao;

import lottogame.domain.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDaoTest {

    @Test
    void 라운드_출력_테스트() {
        assertThat(LottoResultDao.getInstance().findPresentRound()).isEqualTo(1);
    }

    @Test
    void 당첨_결과_테스트() {
        LottoTickets lottoTickets = LottoTickets.generate();
        lottoTickets.addManualLotto("1,2,3,4,5,6");
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"), 7);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        Map<Rank,Integer> result = lottoResult.getResult();
        LottoResultDao.getInstance().updateLottoResult(1,result);
    }

    @Test
    void 새로운_라운드_생성_테스트() {
        LottoResultDao.getInstance().insertNewLottoRound();
    }

    @Test
    void 해당_회차에_대한_result_결과_테스트() {
        Map<Rank,Integer> test = new HashMap<>();
        test.put(Rank.FIRST,0);
        test.put(Rank.SECOND,0);
        test.put(Rank.THIRD,0);
        test.put(Rank.FOURTH,0);
        test.put(Rank.FIFTH,0);
        assertThat(LottoResultDao.getInstance().findLottoResultByRound(14)).isEqualTo(test);
    }
}
