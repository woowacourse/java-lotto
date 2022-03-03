package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.generator.LottoGenerator;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;
    private final Budget budget;

    public LottoMachine(Budget budget, LottoGenerator lottoGenerator) {
        this.budget = budget;
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> issueAutoLottos() {
        List<Lotto> issuedLottos = generateAffordableLotto();
        budget.payLottos(issuedLottos.size());
        return issuedLottos;
    }

    private List<Lotto> generateAffordableLotto() {
        return IntStream.range(0, budget.getAffordableLottoCount())
                .mapToObj(i -> lottoGenerator.createLotto())
                .collect(Collectors.toList());
    }

    public List<Lotto> issueManualLottos(List<List<LottoNumber>> lists) {
        List<Lotto> manualLottos = createManualLotto(lists);
        budget.payLottos(manualLottos.size());
        return manualLottos;
    }

    private List<Lotto> createManualLotto(List<List<LottoNumber>> lists) {
        return lists.stream()
                .map(lottoNumbers -> Lotto.of(lottoNumbers))
                .collect(Collectors.toList());
    }
}
