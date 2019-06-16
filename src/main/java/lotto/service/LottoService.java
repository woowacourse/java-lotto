package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dao.StatisticsDAO;
import lotto.dao.WinningLottoDAO;
import lotto.domain.*;
import lotto.dto.LottoDTO;
import lotto.dto.StatisticsDTO;
import lotto.dto.WinningLottoDTO;
import lotto.utils.Parser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {
    private final String round;
    private LottoPaper lottoPaper;
    private WinningLotto winningLotto;
    private WinningLottoDAO winningLottoDAO;
    private LottoDAO lottoDAO;
    private StatisticsDAO statisticsDAO;

    public LottoService(String round) {
        this.round = round;
        winningLottoDAO = new WinningLottoDAO();
        lottoDAO = new LottoDAO();
        statisticsDAO = new StatisticsDAO();
    }

    public void buyLotto(String money, String[] customLottoNumbers) {
        Money money1 = new Money(Integer.parseInt(money));
        LottoOMRCard lottoOMRCard = new LottoOMRCard();
        Stream.of(customLottoNumbers)
                .forEach(customLottoNumber -> lottoOMRCard.addCustomLotto(Parser.parsingLottoNumbers(customLottoNumber.split(","))));

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();
        lottoPaper = lottoVendingMachine.buyLotto(money1, lottoOMRCard);
    }

    public void enterWinningLotto(String winningBalls, String bonusBall) {
        Lotto Lotto = new CustomLottoGenerator(Parser.parsingLottoNumbers(winningBalls.split(","))).makeLotto();
        LottoNumber bonusNumber = LottoNumber.generateNumber(Integer.parseInt(bonusBall));

        winningLotto = new WinningLotto(Lotto, bonusNumber);
    }

    public List<Object> draw() throws SQLException {
        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));
        WinningLottoDTO winningLottoDTO = makeWinningLottoDTO(winningLotto);
        List<LottoDTO> lottoDTOS = makeLottoDTOs(lottoPaper);
        StatisticsDTO statisticsDTO = makeStatisticsDTO(statistics);

        insertResult(winningLottoDTO, lottoDTOS, statisticsDTO);
        return searchRound(round);
    }

    private List<LottoDTO> makeLottoDTOs(LottoPaper lottoPaper) {
        return lottoPaper.getLottos()
                .stream()
                .map(this::makeLottoDTO)
                .collect(Collectors.toList());
    }

    private LottoDTO makeLottoDTO(Lotto lotto) {
        return new LottoDTO(round,
                lotto.getLottoNumbers()
                        .stream()
                        .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                        .collect(Collectors.joining(","))
        );
    }

    private WinningLottoDTO makeWinningLottoDTO(WinningLotto winningLotto) {
        return new WinningLottoDTO(round,
                winningLotto.getWinningLotto()
                        .getLottoNumbers()
                        .stream()
                        .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                        .collect(Collectors.joining(",")),
                String.valueOf(winningLotto.getBonusNumber().getNumber())
        );
    }

    private StatisticsDTO makeStatisticsDTO(Statistics statistics) {
        String percentFormat = "%d%%";
        int percent = 100;

        return new StatisticsDTO(round,
                makeResult(statistics.ranksStatistics()),
                String.format(percentFormat, ((int) (statistics.returnOfRate() * percent)))
        );
    }

    private String makeResult(Map<Rank, Long> roundStatistics) {
        StringBuilder stringBuilder = new StringBuilder();
        roundStatistics.forEach((rank, count) -> stringBuilder.append(roundResult(rank, count)));

        return stringBuilder.toString();
    }

    private String roundResult(Rank rank, Long count) {
        String resultMessage = "%d개 일치 (%d원) - %d개\n";
        String resultSecondMessage = "%d개 일치, 보너스볼 일치 (%d원) - %d개\n";

        return String.format(rank != Rank.SECOND ? resultMessage : resultSecondMessage, rank.getCountOfMatch(), rank.getReward(), count);
    }

    private void insertResult(WinningLottoDTO winningLottoDTO, List<LottoDTO> lottoDTOS, StatisticsDTO statisticsDTO) throws SQLException {
        winningLottoDAO.insertWinningLotto(winningLottoDTO);
        lottoDAO.insertLottos(lottoDTOS);
        new StatisticsDAO().insertStatistics(statisticsDTO);
    }

    public List<Object> searchRound(String round) throws SQLException {
        List<Object> searchResult = new ArrayList<>();
        searchResult.add(winningLottoDAO.selectWinningLotto(round));
        searchResult.add(lottoDAO.selectRoundLotto(round));
        searchResult.add(statisticsDAO.selectStatistics(round));

        return searchResult;
    }
}
