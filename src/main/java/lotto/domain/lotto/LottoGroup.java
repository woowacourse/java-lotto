package lotto.domain.lotto;

import lotto.domain.number.LottoNumber;
import lotto.domain.rank.RankFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoGroup {

    private final List<LottoNumbers> lottos;

    public LottoGroup(List<LottoNumbers> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int getCount() {
        return lottos.size();
    }

    public List<LottoNumbers> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public LottoGroup add(LottoGroup lottoGroup) {
        return new LottoGroup(
                Stream.concat(
                        lottos.stream(),
                        lottoGroup.lottos.stream()
                ).collect(toList())
        );
    }

    public Map<RankFactory, Long> calculateLottoResult(LottoNumbers winningLottoNumbers,
                                                       LottoNumber bonusNumber) {
        return lottos.stream()
                .map(lottoNumber -> getRank(lottoNumber, winningLottoNumbers, bonusNumber))
                .filter(rank -> !rank.equals(RankFactory.FAIL))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private RankFactory getRank(LottoNumbers lottoNumbers,
                                LottoNumbers winningLottoNumbers,
                                LottoNumber bonusNumber) {
        return RankFactory.getRank(
                winningLottoNumbers.getMatchCount(lottoNumbers),
                lottoNumbers.contains(bonusNumber)
        );
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), lottos.stream()
                .map(LottoNumbers::toString).collect(toList()));
    }


}