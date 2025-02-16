package domain.lotto;


import static domain.lotto.Lotto.LOTTO_PRICE;

import domain.MatchDto;
import domain.numbergenerator.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos issueByMoney(int money) {
        int quantity = money / LOTTO_PRICE;
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

        List<Lotto> generatedLottos = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Lotto issuedLotto = Lotto.issueByNumberGenerator(randomNumberGenerator);
            generatedLottos.add(issuedLotto);
        }

        return new Lottos(generatedLottos);
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }

    public List<MatchDto> getMatchDtos(List<Integer> winningNumbers, int bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.getMatchDto(winningNumbers, bonusNumber))
                .toList();
    }
}
