package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import util.Validator;

public class Lottos {

    private static final int START_INDEX = 0;

    private final List<Lotto> members;

    public Lottos(LottoNumberGenerator lottoNumberGenerator, int lottoAmount) {
        this.members = generateLottos(lottoNumberGenerator, lottoAmount);
    }

    public static Lottos buyLottos(LottoNumberGenerator lottoNumberGenerator, int lottoAmount) {
        Validator.checkArgumentIsNull(lottoNumberGenerator);
        return new Lottos(lottoNumberGenerator, lottoAmount);
    }

    public int numberOfLottery() {
        return members.size();
    }

    public List<Lotto> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public List<Integer> compareAllLottosWithWinningLotto(Lotto winningLotto) {
        Validator.checkArgumentIsNull(winningLotto);
        return members.stream()
                .map(lotto -> lotto.countDuplicatedNumber(winningLotto))
                .collect(Collectors.toList());
    }

    public List<Boolean> checkAllLottosContainNumber(LottoNumber number) {
        Validator.checkArgumentIsNull(number);
        return members.stream()
                .map(lotto -> lotto.contains(number))
                .collect(Collectors.toList());
    }

    private List<Lotto> generateLottos(LottoNumberGenerator lottoNumberGenerator, int lottoAmount) {
        return IntStream.range(START_INDEX, lottoAmount)
                .boxed()
                .map(i -> Lotto.generateLotto(lottoNumberGenerator))
                .collect(Collectors.toList());
    }
}
