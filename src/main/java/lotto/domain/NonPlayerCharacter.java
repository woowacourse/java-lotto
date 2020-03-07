package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class NonPlayerCharacter {

    public static Money translateToMoney(int purchaseAmount) {
        return new Money(purchaseAmount);
    }

    public static List<Set<LottoNumber>> translateToLottoNumbersBasket(List<Set<Integer>> numbersBasket) {
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>();
        for (Set<Integer> numbers : numbersBasket) {
            Set<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::new).collect(Collectors.toSet());
            lottoNumbersBasket.add(lottoNumbers);
        }
        return lottoNumbersBasket;
    }

    public static Result judgeResult(Lottos lottosManual, Lottos lottosAutomatic, Lotto winningLotto, int bonusNumber, Money purchaseAmount) {
        Lottos lottos = lottosManual.concat(lottosAutomatic);
        WinningRanks winningRanks = lottos.produceWinningRanks(winningLotto, new LottoNumber(bonusNumber));
        return new Result(winningRanks, purchaseAmount);
    }

    public static Lotto declareWinningLotto(List<Integer> winningNumbers) {
        return new Lotto(winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
    }
}
