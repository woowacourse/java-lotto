package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NonPlayerCharacterTest {

    @Test
    void translateToMoney() {
        int purchaseAmount = 1000;
        assertThat(NonPlayerCharacter.translateToMoney(purchaseAmount)).isEqualTo(new Money(purchaseAmount));
    }

    @Test
    void translateToLottoNumbersBasket() {
        List<Set<Integer>> numbersBasket = new ArrayList<>(Collections.singletonList(Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toSet())));
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>(Collections.singletonList(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(Collectors.toSet())));

        assertThat(NonPlayerCharacter.translateToLottoNumbersBasket(numbersBasket)).isEqualTo(lottoNumbersBasket);
    }

    @Test
    void judgeResult() {
        Lotto winnningLotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6).map(LottoNumber::new).collect(Collectors.toSet()));
        Lottos lottosManual = new Lottos(Collections.singletonList(winnningLotto));
        Lottos lottosAutomatic = new Lottos(Collections.singletonList(new Lotto(Stream.of(2, 3, 4, 5, 6, 7).map(LottoNumber::new).collect(Collectors.toSet()))));
        int bonusNumber = 9;
        Money purchasAmount = new Money(1000);
        Map<Rank, Integer> ranks = new HashMap<>();
        ranks.put(Rank.FIRST, 1);
        ranks.put(Rank.THIRD, 1);
        WinningRanks winningRanks = new WinningRanks(ranks);

        Result result = NonPlayerCharacter.judgeResult(lottosManual, lottosAutomatic, winnningLotto, bonusNumber, purchasAmount);
        assertThat(result.getWinningRanks()).isEqualTo(winningRanks);
    }

    @Test
    void declareWinningLotto() {
        Lotto winningLotto = new Lotto(Stream.of(1,2,3,4,5,6).map(LottoNumber::new).collect(Collectors.toSet()));
        assertThat(NonPlayerCharacter.declareWinningLotto(Arrays.asList(1,2,3,4,5,6))).isEqualTo(winningLotto);
    }
}