package lotto.service;

import lotto.ConnectionFactory;
import lotto.dao.LottoDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinningResult;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNo;
import lotto.domain.lotto.WinningLotto;
import lotto.utils.LottoNoParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoService {
    private static WinningLottoDao winningLottoDao;
    private static LottoDao lottoDao;
    private static ResultDao resultDao;

    static {
        Connection con = ConnectionFactory.connect();
        winningLottoDao = new WinningLottoDao(con);
        lottoDao = new LottoDao(con);
        resultDao = new ResultDao(con);
    }

    public void makeWinningLotto(String winningLottoNo, int bonusNo) throws SQLException {
        System.out.println(winningLottoNo);
        System.out.println(bonusNo);
        WinningLotto winningLotto = WinningLotto.of(LottoNoParser.parseToLottoNos(winningLottoNo), LottoNo.of(bonusNo));
        registerWinningLotto(winningLotto);
        registerResult(winningLotto);
    }

    private void registerWinningLotto(WinningLotto winningLotto) throws SQLException {
        winningLottoDao.addWinningLotto(winningLotto.createWinningLottoDto());
    }

    private void registerResult(WinningLotto winningLotto) throws SQLException {
        List<Lotto> lotto = lottoDao.getLottosInThisRound().stream()
                .map(lottoDto -> LottoNoParser.parseToLottoNos(lottoDto.getLottoNo()))
                .map(Lotto::of).collect(Collectors.toList());
        resultDao.addResult(new WinningResult(lotto, winningLotto).createResultDto());
    }
}