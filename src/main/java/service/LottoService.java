package service;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.enums.Prize;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final Map<Prize, Integer> prizeResult = new HashMap<>();

    public AnswerLotto getAnswerLotto(List<Integer> selectedNumbers, int bonus) {
        Lotto answerLotto = new Lotto(selectedNumbers);
        return new AnswerLotto(answerLotto, bonus);
    }

    private void calculatePrize(AnswerLotto answerLotto, Lottos lottos) {
        // 몇 개 맞았는지 세야되고
        // 보너스 맞았는지 세야되고
        // 그걸로 Prize 가져와야됨
        int matchedNumberCount = 0;
        boolean isBonusMatched = false;
        for (Lotto lotto : lottos.getLottos()) {
            int matchedNumberCount = lotto.getHitCountFrom(answerLotto.getLotto());
            boolean isBonusMatched = lotto.has(answerLotto.getBonusNumber());

        }
    }
}
