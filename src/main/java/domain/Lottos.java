package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Validator;

public class Lottos {

    private static final int START_INDEX = 0;

    private final List<Lotto> lottos;

    public Lottos(int lottoAmount) {
        this.lottos = generateLottos(lottoAmount);
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos buyLottos(int lottoAmount) {
        return new Lottos(lottoAmount);
    }

    public int numberOfLottery() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<Integer> compareAllLottosWithWinningLotto(Lotto winningLotto) {
        Validator.checkArgumentIsNull(winningLotto);
        return lottos.stream()
                .map(lotto -> lotto.countDuplicatedNumber(winningLotto))
                .collect(Collectors.toList());
    }

    public List<Boolean> compareAllLottosWithBonusNumber(LottoNumber bonusNumber) {
        Validator.checkArgumentIsNull(bonusNumber);
        return lottos.stream()
                .map(lotto -> lotto.isBonusNumberContain(bonusNumber))
                .collect(Collectors.toList());
    }

    private List<Lotto> generateLottos(int lottoAmount) {
        return IntStream.range(START_INDEX, lottoAmount)
                .boxed()
                .map(i -> Lotto.generateLotto())
                .collect(Collectors.toList());
    }
}
