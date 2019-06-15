package lotto.controller;

import lotto.ConnectionFactory;
import lotto.dao.LottoDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinningResult;
import lotto.domain.buyer.Budget;
import lotto.domain.buyer.LottoContainer;
import lotto.domain.buyer.NoMoneyException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNo;
import lotto.domain.lotto.LottoType;
import lotto.domain.lotto.WinningLotto;
import lotto.dto.LottoDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningLottoDto;
import lotto.utils.LottoNoParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    private static final String NO_MONEY_ERROR_MSG = "구입 금액으로 원하는 장 수의 로또를 살 수 없습니다.";

    private static LottoDao lottoDao;
    private static WinningLottoDao winningLottoDao;
    private static ResultDao resultDao;

    static {
        Connection con = ConnectionFactory.connect();
        lottoDao = new LottoDao(con);
        winningLottoDao = new WinningLottoDao(con);
        resultDao = new ResultDao(con);
    }

    private Budget budget;
    private LottoContainer lottoContainer = new LottoContainer();
    private WinningLotto winningLotto;

    public void makeBuyer(int budget) {
        this.budget = new Budget(budget);
    }

    void makeManualLotto(String[] rawLottoInputs) {
        List<Lotto> lottos = Arrays.stream(rawLottoInputs)
                .map(LottoNoParser::parseToLottoNos)
                .map(Lotto::of).collect(Collectors.toList());
        budget.pay(rawLottoInputs.length);
        lottoContainer.addLotto(lottos);
    }

    public void validataeAffordability(int countOfManualLotto) {
        if (!budget.canBuyLotto(countOfManualLotto))
            throw new NoMoneyException(NO_MONEY_ERROR_MSG);
    }

    public void makeManualLotto(List<Lotto> lottos) {
        budget.pay(lottos.size());
        lottoContainer.addLotto(lottos);
    }

    public void makeAutoLotto() {
        while (budget.canBuyLotto()) {
            budget.pay();
            lottoContainer.addLotto(Lotto.of());
        }
    }

    public List<String> showLottoInfos() {
        List<String> lottoInfos = new ArrayList<>();
        lottoInfos.add("수동으로 " + lottoContainer.getCountOfLottoMatch(LottoType.MANUAL) + "개, "
                + "자동으로 " + lottoContainer.getCountOfLottoMatch(LottoType.AUTOMATIC) + "개를 구매하셨습니다.");
        lottoInfos.addAll(lottoContainer.showLottos());

        return lottoInfos;
    }

    public int calculateMaxManualCount(int budget) {
        return budget / Lotto.LOTTO_PRICE;
    }

    public List<LottoDto> createLottoDtos() {
        return lottoContainer.createLottoDto();
    }

    public boolean makeWinningLotto(List<LottoNo> winningLottoNo, LottoNo bonusNo) {
        winningLotto = WinningLotto.of(winningLottoNo, bonusNo);
        return true;
    }

    public WinningResult checkWinningLotto() {
        return lottoContainer.createResult(winningLotto);
    }

    public void registerLotto() throws SQLException {
        List<LottoDto> dtos = lottoContainer.createLottoDto();
        for (LottoDto dto : dtos) {
            lottoDao.addLotto(dto);
        }
    }

    public void registerWinningLotto() throws SQLException {
        winningLottoDao.addWinningLotto(winningLotto.createWinningLottoDto());
    }

    public void registerResult() throws SQLException {
        List<Lotto> lotto = lottoDao.getLottosInThisRound().stream()
                .map(lottoDto -> LottoNoParser.parseToLottoNos(lottoDto.getLottoNo()))
                .map(Lotto::of).collect(Collectors.toList());

        resultDao.addResult(new WinningResult(lotto, winningLotto).createResultDto());
    }

    public List<LottoDto> getPurchasedLotto(int round) throws SQLException {
        return lottoDao.getLottos(round);
    }

    public WinningLottoDto getWinningLotto(int round) throws SQLException {
        return winningLottoDao.getWinningLotto(round);
    }

    public int getMaxRound() throws SQLException {
        return resultDao.findRoundNo();
    }

    public ResultDto getResult(int round) throws SQLException {
        return resultDao.getResult(round);
    }
}