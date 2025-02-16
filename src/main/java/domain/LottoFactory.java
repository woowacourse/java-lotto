package domain;

import static util.constant.Values.LOTTO_MAX_NUM;
import static util.constant.Values.LOTTO_MIN_NUM;
import static util.constant.Values.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private final Money money;
    private final List<Lotto> lottos;

    public LottoFactory(Money money) {
        this.money = money;
        this.lottos = createLottos(money);
    }

    public List<Lotto> createLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();

        int totalLotto = money.calculateTotalLotto();
        for (int i = 0; i < totalLotto; i++) {
            lottos.add(new Lotto(createNumbers()));
        }
        return lottos;
    }

    private List<Integer> createNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(LOTTO_MIN_NUM, LOTTO_MAX_NUM)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(numbers);

        return numbers.subList(0, LOTTO_SIZE);
    }

    public String createResult() {
        String result = "";
        for (Lotto lotto : lottos) {
            result += lotto.toString() + "\n";
        }
        return result;
    }

    public Map<LottoRank, Integer> countLottos(WinningLotto winningLotto) {
        Map<LottoRank, Integer> result = LottoRank.createLottoRankCounter();

        for (Lotto lotto : lottos) {
            LottoRank matchedLotto = lotto.compareLotto(winningLotto);
            result.put(matchedLotto, result.get(matchedLotto) + 1);
        }
        return result;
    }

    public int getLottoSize() {
        return lottos.size();
    }
}
