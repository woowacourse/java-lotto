package lotto.service;

import lotto.domain.*;
import lotto.persistence.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lotto.service.ServiceUtils.parseInts;

public class LottoService {
    public static void saveResult(int lottoBuyingMoney,
                                  int numOfCustomLottos, String winningLotto, List<String> customLottos) throws SQLException {
        // 유저의 입력으로부터 로또를 생성하고, 당첨로또와 비교해 통계를 생성한다.
        WinningLotto winLotto = createWinningLotto(winningLotto);

        LottoCount lottoCount = new LottoCount(new LottoBuyingMoney(lottoBuyingMoney), numOfCustomLottos);
        Lottos lottos = createLottos(lottoCount, customLottos);
        List<Rank> ranks = lottos.match(winLotto);
        WinningStatistics winStat = new WinningStatistics(ranks);

        try (Connection con = Connector.getConnection()) {
            RoundDAO roundDao = new RoundDAO(con);
            ResultDAO resultDao = new ResultDAO(con);
            LottoDAO lottoDao = new LottoDAO(con);
            WinningLottoDAO winningLottoDao = new WinningLottoDAO(con);

            // 로또, 통계 등을 DB에 저장한다.
            roundDao.addRound(winStat.getPrize().getValue(), winStat.getInterestRate(new LottoBuyingMoney(lottoBuyingMoney)));
            int thisRoundId = roundDao.getLatestRoundId();
            resultDao.addResult(thisRoundId, winStat.getStatistics());
            winningLottoDao.addWinningLotto(thisRoundId, winLotto);
            lottoDao.addLotto(thisRoundId, lottos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static Lottos createLottos(LottoCount lottoCount, List<String> customLottos) {
        List<List<Integer>> lottos = new ArrayList<>();
        for (String customLotto : customLottos) {
            lottos.add(ServiceUtils.parseInts(customLotto));
        }
        return LottoVendingMachine.getLottos(lottoCount, lottos);
    }

    public static WinningLotto createWinningLotto(String winningLotto) {
        List<Integer> integers = parseInts(winningLotto);
        return new WinningLotto(integers.subList(0, 6), integers.get(6));
    }
}
