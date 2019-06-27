package lotto.service;

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
import lotto.domain.lotto.WinningLotto;
import lotto.dto.LottoDto;
import lotto.dto.PurchaseDto;
import lotto.dto.ResultDto;
import lotto.dto.WinningLottoDto;
import lotto.utils.LottoNoParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private WinningLotto winningLotto;

    private Budget makeBuyer(int budget) {
        return new Budget(budget);
    }

    private LottoContainer makeManualLotto(List<String> rawLottoInputs) {
        LottoContainer lottoContainer = new LottoContainer();
        List<Lotto> lottos = rawLottoInputs.stream()
                .map(LottoNoParser::parseToLottoNos)
                .map(Lotto::of).collect(Collectors.toList());
        lottoContainer.addLotto(lottos);
        return lottoContainer;
    }

    private void validateAffordability(Budget budget, int countOfManualLotto) {
        if (!budget.canBuyLotto(countOfManualLotto))
            throw new NoMoneyException(NO_MONEY_ERROR_MSG);
    }


    public PurchaseDto buyLotto(PurchaseDto purchaseInfo) throws SQLException {
        Budget budget = makeBuyer(purchaseInfo.getBudget());
        validateAffordability(budget, purchaseInfo.getManualCount());
        LottoContainer lottoContainer = makeManualLotto(purchaseInfo.getManualLottos());
        budget.pay(purchaseInfo.getManualCount());
        List<Lotto> autoLottos = makeAutoLotto(budget);
        lottoContainer.addLotto(autoLottos);
        purchaseInfo.setAutoLottos(autoLottos.stream().map(Lotto::toString).collect(Collectors.toList()));
        registerLotto(lottoContainer);
        return purchaseInfo;
    }


    private List<Lotto> makeAutoLotto(Budget budget) {
        List<Lotto> autoLottos = new ArrayList<>();
        while (budget.canBuyLotto()) {
            budget.pay();
            autoLottos.add(Lotto.of());
        }
        return autoLottos;
    }

    public boolean makeWinningLotto(List<LottoNo> winningLottoNo, LottoNo bonusNo) {
        winningLotto = WinningLotto.of(winningLottoNo, bonusNo);
        return true;
    }

    //
//    public WinningResult checkWinningLotto() {
//        return lottoContainer.createResult(winningLotto);
//    }
//
    private void registerLotto(LottoContainer lottoContainer) throws SQLException {
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