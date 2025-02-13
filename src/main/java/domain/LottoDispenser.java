package domain;

import exception.LottoException;
import java.util.ArrayList;
import java.util.List;
import utility.StringUtility;

public class LottoDispenser {

    private final int LOTTO_MONEY_UNIT = 1000;
    private final List<Lotto> lottos;
    private String INVALID_BUY_MONEY = "유효하지 않은 구매 금액입니다.";

    public LottoDispenser(String buyMoney) {
        validateLottoDispenser(buyMoney);
        int lottoCount = Integer.parseInt(buyMoney) / LOTTO_MONEY_UNIT;
        lottos = generateLottos(lottoCount);
    }

    private void validateLottoDispenser(String buyMoney) {
        if(buyMoney == null || !StringUtility.isNumber(buyMoney)){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        int buyMoneyNumber = Integer.parseInt(buyMoney);
        if(buyMoneyNumber == 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
        if(buyMoneyNumber % LOTTO_MONEY_UNIT != 0){
            throw new LottoException(INVALID_BUY_MONEY);
        }
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(LottoRandomGenerator.generateNumbers()));
        }
        return lottos;
    }
}
