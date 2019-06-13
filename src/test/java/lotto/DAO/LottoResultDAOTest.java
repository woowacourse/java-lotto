package lotto.dao;

import lottogame.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDAOTest {

    @Test
    void 라운드_출력_테스트() {
        assertThat(LottoResultDAO.getInstance().findPresentRound()).isEqualTo(1);
    }

    @Test
    void 당첨_결과_테스트() {
        LottoTickets lottoTickets = LottoTickets.generate();
        lottoTickets.addManualLotto("1,2,3,4,5,6");
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create("1,2,3,4,5,6"), 7);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        Map<Rank,Integer> result = lottoResult.getResult();
        LottoResultDAO.getInstance().updateLottoResult(1,result);
    }

    @Test
    void 새로운_라운드_생성_테스트() {
        LottoResultDAO.getInstance().insertNewLottoRound();
    }
}
