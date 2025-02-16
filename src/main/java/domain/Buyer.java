package domain;

import generator.RandomGenerator;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Buyer {

    private Money money;
    private List<Lotto> lottos = new ArrayList<>();
    private RandomGenerator randomGenerator;

    // 생성자에 전략을 주입받을 수 있게 수정
    public Buyer(Money money, RandomGenerator randomGenerator) {
        this.money = money;
        this.randomGenerator = randomGenerator;
        createLottos();
    }

    public int getLottoSize() {
        return lottos.size();
    }

    // 로또 생성 시 전략에 따라 번호를 생성
    public void createLottos() {
        int totalLotto = money.calculateTotalLotto();
        for (int i = 0; i < totalLotto; i++) {
            lottos.add(new Lotto(randomGenerator));
        }
    }

    // 로또 결과 출력
    public String createResult() {
        StringBuilder result = new StringBuilder();
        for (Lotto lotto : lottos) {
            result.append(lotto.toString()).append("\n");
        }
        return result.toString();
    }

    // 로또 결과 계산
    public Map<LottoMatch, Integer> countLottos(WinningLotto winningLotto) {
        Map<LottoMatch, Integer> result = new LinkedHashMap<>();
        for (LottoMatch lottoMatch : LottoMatch.values()) {
            result.put(lottoMatch, 0);
        }

        for (Lotto lotto : lottos) {
            LottoMatch matchedLotto = lotto.compareLotto(winningLotto);
            result.put(matchedLotto, result.get(matchedLotto) + 1);
        }
        return result;
    }
}
