package lotto.service;

import lotto.ConnectionFactory;
import lotto.dao.LottoDao;
import lotto.dao.ResultDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.buyer.Budget;
import lotto.domain.buyer.LottoContainer;
import lotto.domain.buyer.NoMoneyException;
import lotto.domain.lotto.Lotto;
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

    private Budget makeBuyer(int budget) {
        return new Budget(budget);
    }

    public PurchaseDto buyLotto(PurchaseDto purchaseInfo) throws SQLException {
        System.out.println("buyLotto");
        Budget budget = makeBuyer(purchaseInfo.getBudget());
        System.out.println("buyLotto");
        validateAffordability(budget, purchaseInfo.getManualCount());
        System.out.println("buyLotto");
        LottoContainer lottoContainer = makeManualLotto(purchaseInfo.getManualLottos());
        System.out.println("buyLotto");
        budget.pay(purchaseInfo.getManualCount());
        System.out.println("buyLotto");
        List<Lotto> autoLottos = makeAutoLotto(budget);
        System.out.println("buyLotto");
        lottoContainer.addLotto(autoLottos);
        System.out.println("buyLotto");
        purchaseInfo.setAutoLottos(autoLottos.stream().map(Lotto::toString).collect(Collectors.toList()));
        System.out.println("buyLotto");
        registerLotto(lottoContainer);
        System.out.println("buyLotto");
        return purchaseInfo;
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

    private List<Lotto> makeAutoLotto(Budget budget) {
        List<Lotto> autoLottos = new ArrayList<>();
        while (budget.canBuyLotto()) {
            budget.pay();
            autoLottos.add(Lotto.of());
        }
        return autoLottos;
    }

    private void registerLotto(LottoContainer lottoContainer) throws SQLException {
        List<LottoDto> dtos = lottoContainer.createLottoDto();
        for (LottoDto dto : dtos) {
            lottoDao.addLotto(dto);
        }
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