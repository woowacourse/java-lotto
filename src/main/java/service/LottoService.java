package service;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.Money;
import domain.enums.Prize;
import dto.OutputPurchasedLottosDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    public AnswerLotto getAnswerLotto(List<Integer> selectedNumbers, int bonus) {
        Lotto answerLotto = new Lotto(selectedNumbers);
        return new AnswerLotto(answerLotto, bonus);
    }

    public List<OutputPurchasedLottosDto> getOutputLottosDtos(List<Lotto> lottos) {
        return lottos.stream().map(lotto -> new OutputPurchasedLottosDto(lotto.getNumbers())).toList();
    }

    public Map<Prize, Integer> calculatePrize(AnswerLotto answerLotto, Lottos lottos) {
        final Map<Prize, Integer> prizeResult = new LinkedHashMap<>();

        for (Prize prize : Prize.values()) {
            prizeResult.put(prize, 0);
        }

        for (Lotto lotto : lottos.getLottos()) {
            int matchedCount = lotto.getHitCountFrom(answerLotto.getLotto());
            boolean isBonusMatched = lotto.has(answerLotto.getBonusNumber());
            Prize foundPrize = Prize.getPrizeOf(matchedCount, isBonusMatched);
            updatePrizeResult(prizeResult, foundPrize);
        }

        return prizeResult;
    }

    public double calculateRateOfReturn(Map<Prize, Integer> prizeResult) {
        int totalPrizeCount = 0;
        int totalEarnedMoney = 0;
        for (Map.Entry<Prize, Integer> entry : prizeResult.entrySet()) {
            Prize prize = entry.getKey();
            int prizeCount = entry.getValue();

            totalPrizeCount += prizeCount;
            totalEarnedMoney += prize.getPrizeMoney() * prizeCount;
        }

        int usedMoney = totalPrizeCount * Money.LOTTO_PRICE;
        double rateOfReturn = (double) totalEarnedMoney / usedMoney;
        return Math.floor(rateOfReturn * 100) / 100.0;
    }

    private void updatePrizeResult(Map<Prize, Integer> prizeResult, Prize foundPrize) {
        Integer prizeCount = prizeResult.getOrDefault(foundPrize, 0);
        prizeResult.put(foundPrize, prizeCount + 1);
    }
}
