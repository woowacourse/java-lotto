package lotto.service;

import lotto.dao.LottoDao;
import lotto.domain.LottoOMRCard;
import lotto.domain.LottoPaper;
import lotto.domain.LottoVendingMachine;
import lotto.domain.Money;
import lotto.dto.LottoPaperDto;
import lotto.utils.NumberUtil;

import java.sql.SQLException;
import java.util.stream.Stream;

public class BuyingService {
    private LottoDao lottoDao;

    public BuyingService() {
        lottoDao = new LottoDao();
    }

    public void buyLotto(String round, String insertMoney, String[] customsLottoNumbers) throws SQLException {
        Money money = new Money(Integer.parseInt(insertMoney));
        LottoOMRCard lottoOMRCard = new LottoOMRCard();

        if (customsLottoNumbers != null) {
            Stream.of(customsLottoNumbers)
                    .map(customsLottoNumber -> NumberUtil.parsing(customsLottoNumber.split(",")))
                    .forEachOrdered(lottoOMRCard::addCustomLotto);
        }

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();
        LottoPaper lottoPaper = lottoVendingMachine.buyLotto(money, lottoOMRCard);
        LottoPaperDto lottoPaperDto = new LottoPaperDto(round, lottoPaper);

        lottoDao.insertLottos(lottoPaperDto.getLottoDtos());
    }
}
