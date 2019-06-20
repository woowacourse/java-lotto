package lotto.application.lottomoney;

import lotto.application.lottoresult.LottoResultDAO;
import lotto.domain.lottomoney.Cash;
import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottomoney.dto.LottoMoneyDTO;

public class LottoMoneyService {
    private static LottoMoneyService lottoMoneyService = null;

    private LottoMoneyService() {
    }

    public static LottoMoneyService getInstance() {
        if (lottoMoneyService == null) {
            lottoMoneyService = new LottoMoneyService();
        }
        return lottoMoneyService;
    }

    public LottoMoneyDTO makeLottoMoneyDto(String purchasePrice) {
        Cash cash = new Cash(Long.parseLong(purchasePrice));
        MoneyForLotto moneyForLotto = new MoneyForLotto(cash);
        LottoMoneyDTO lottoMoneyDto = LottoMoneyAssembler.makeLottoMoneyDto(moneyForLotto);

        createRound();
        return lottoMoneyDto;
    }

    private void createRound() {
        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        lottoResultDAO.createNextRound();
    }
}
