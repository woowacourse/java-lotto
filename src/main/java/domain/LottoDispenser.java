package domain;

import domain.enums.WinningCase;
import exception.LottoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.print.DocFlavor.STRING;
import utility.StringUtility;

public class LottoDispenser {

    private final int LOTTO_MONEY_UNIT = 1000;
    private final String INVALID_BUY_MONEY = "유효하지 않은 구매 금액입니다.";
    private final String BUY_LOTTO_AMOUNT_FORMAT = "%d개를 구매했습니다.\n";
    private final List<Lotto> lottos;
    private final int buyMoney;

    public LottoDispenser(String buyMoneyInput) {
        validateLottoDispenser(buyMoneyInput);
        this.buyMoney = Integer.parseInt(buyMoneyInput);
        int lottoCount = Integer.parseInt(buyMoneyInput) / LOTTO_MONEY_UNIT;
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


    public HashMap<WinningCase,Integer> winningCalculate(WinningNumber winningNumber, BonusNumber bonusNumber) {
        HashMap<WinningCase,Integer> winningResult = new HashMap<>();
        for(Lotto lotto : lottos){
            int sameCount = lotto.compare(winningNumber, bonusNumber);
            boolean isBonus = lotto.compareBonusNumber(bonusNumber);
            WinningCase winningCase = WinningCase.getWinningCase(sameCount, isBonus);
            winningResult.put(winningCase,winningResult.getOrDefault(winningCase,0)+1);
        }

        return winningResult;
    }

    public long calculateEarnMoney(HashMap<WinningCase, Integer> winningCalculateResult) {
        long earnMoneySum = 0;
        for (Entry<WinningCase, Integer> winningCaseIntegerEntry : winningCalculateResult.entrySet()) {
            long earnMoney = winningCaseIntegerEntry.getKey()
                    .calculateEarnMoney(winningCaseIntegerEntry.getValue());
            earnMoneySum += earnMoney;
        }
        return earnMoneySum;
    }

    public double calculateEarnMoneyRatio(long earnMoney) {
        return  ((double) (earnMoney / buyMoney) * 100) / 100;
    }

    public String formattingBuyLottoResult() {
        StringBuilder stringBuilder = new StringBuilder(String.format(BUY_LOTTO_AMOUNT_FORMAT,lottos.size()));
        for(Lotto lotto : lottos){
            stringBuilder.append(lotto.formatNumbers())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
