package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonPlayerCharacter {

    public Money translateToMoney(int purchaseAmount) {
        return new Money(purchaseAmount);
    }

    public List<Set<LottoNumber>> translateToLottoNumbersBasket(List<Set<Integer>> numbersBasket) {
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>();
        for (Set<Integer> numbers : numbersBasket) {
            Set<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toSet());
            lottoNumbersBasket.add(lottoNumbers);
        }
        return lottoNumbersBasket;
    }

    public Result judgeResult(List<Lotto> lottosManual, List<Lotto> lottosAutomatic, Lotto winningLotto, int bonusNumber, Money purchaseAmount) {
        Lottos lottos = new Lottos(Stream.concat(lottosManual.stream(), lottosAutomatic.stream()).collect(Collectors.toList()));
        WinningRanks winningRanks = lottos.produceWinningRanks(winningLotto, new LottoNumber(bonusNumber));
        return new Result(winningRanks, purchaseAmount);
    }

    public Lotto declareWinningLotto(List<Integer> winningNumbers) {
        return new Lotto(winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
    }
}
