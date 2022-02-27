package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    List<Lotto> lottos;

    public Lottos(int lottoAmount) {
        this.lottos = generateLottos(lottoAmount);
    }

    public static Lottos buyLottos(int lottoAmount) {
        return new Lottos(lottoAmount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int numberOfLottery() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> generateLottos(int lottoAmount) {

        lottos = new ArrayList<>();
        for (int i = 0; i < lottoAmount; ++i) {
            lottos.add(Lotto.generateLotto());
        }
        return lottos;
    }

    public List<Integer> compareAllLottosWithWinningLotto(Lotto winningLotto) {
        return lottos.stream()
                .map(lotto -> lotto.countDuplicatedNumber(winningLotto))
                .collect(Collectors.toList());
    }

    public List<Boolean> compareAllLottosWithBonusNumber(LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.isBonusNumberContain(bonusNumber))
                .collect(Collectors.toList());
    }
}
