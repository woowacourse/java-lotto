package service;

import constants.LottoConstants;
import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.enums.Prize;
import dto.OutputLottosDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final Map<Prize, Integer> prizeResult = new LinkedHashMap<>();

    public LottoService() {
        for (Prize prize : Prize.values()) {
            prizeResult.put(prize, 0);
        }
    }

    public List<OutputLottosDto> getLottosDtos(List<Lotto> lottos) {
        List<OutputLottosDto> outputLottosDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            outputLottosDtos.add(new OutputLottosDto(lotto.getNumbers()));
        }

        return outputLottosDtos;
    }

    public AnswerLotto getAnswerLotto(List<Integer> selectedNumbers, int bonus) {
        Lotto answerLotto = new Lotto(selectedNumbers);
        return new AnswerLotto(answerLotto, bonus);
    }

    public Map<Prize, Integer> getPrizeResult() {
        return Collections.unmodifiableMap(prizeResult);
    }

    public void calculatePrize(AnswerLotto answerLotto, Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchedCount = lotto.getHitCountFrom(answerLotto.getLotto());
            boolean isBonusMatched = lotto.has(answerLotto.getBonusNumber());
            Prize foundPrize = Prize.getPrizeOf(matchedCount, isBonusMatched);
            updatePrizeResult(foundPrize);
        }
    }

    public double calculateRateOfReturn() {
        int totalPrizeCount = 0;
        int totalEarnedMoney = 0;
        for (Map.Entry<Prize, Integer> entry : prizeResult.entrySet()) {
            Prize prize = entry.getKey();
            int prizeCount = entry.getValue();

            totalPrizeCount += prizeCount;
            totalEarnedMoney += prize.getPrizeMoney() * prizeCount;
        }

        int usedMoney = totalPrizeCount * LottoConstants.LOTTO_PRICE;
        double rateOfReturn = (double) totalEarnedMoney / usedMoney;
        return Math.floor(rateOfReturn * 100) / 100.0;
    }


    private void updatePrizeResult(Prize foundPrize) {
        Integer prizeCount = prizeResult.getOrDefault(foundPrize, 0);
        prizeResult.put(foundPrize, prizeCount + 1);
    }
}
