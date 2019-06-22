package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.StatisticsDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.dto.LottoPaperDto;
import lotto.dto.StatisticsDto;
import lotto.dto.WinningLottoDto;
import lotto.utils.NumberUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LottoService {

    private WinningLottoDao winningLottoDAO;
    private LottoDao lottoDAO;
    private StatisticsDao statisticsDAO;

    public LottoService() {
        winningLottoDAO = new WinningLottoDao();
        lottoDAO = new LottoDao();
        statisticsDAO = new StatisticsDao();
    }

    public void enterWinningLotto(String round, String winningBalls, String bonusBall) throws SQLException {
        Lotto Lotto = new CustomLottoGenerator(NumberUtil.parsing(winningBalls.split(","))).makeLotto();
        LottoNumber bonusNumber = LottoNumber.generateNumber(Integer.parseInt(bonusBall));

        WinningLotto winningLotto = new WinningLotto(Lotto, bonusNumber);
        WinningLottoDto winningLottoDto = new WinningLottoDto(round, winningLotto);
        winningLottoDAO.insertWinningLotto(winningLottoDto);
    }

    public void draw(String round) throws SQLException {
        LottoPaper lottoPaper = new LottoPaper(lottoDAO.selectRoundLotto(round));
        WinningLotto winningLotto = winningLottoDAO.selectWinningLotto(round);
        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));

        StatisticsDto statisticsDTO = new StatisticsDto(round, statistics);
        statisticsDAO.insertStatistics(statisticsDTO);
    }

    public Map<String, Object> searchRound(String round) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        LottoPaper lottoPaper = new LottoPaper(lottoDAO.selectRoundLotto(round));
        LottoPaperDto lottoPaperDto = new LottoPaperDto(round, lottoPaper);
        WinningLottoDto winningLottoDto = new WinningLottoDto(round, winningLottoDAO.selectWinningLotto(round));

        model.put("lottos", lottoPaperDto.getLottoDtos());
        model.put("winningLotto", winningLottoDto);
        model.put("statistics", statisticsDAO.selectStatistics(round));

        return model;
    }
}
