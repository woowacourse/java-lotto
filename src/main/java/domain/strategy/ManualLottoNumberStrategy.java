package domain.strategy;

import domain.LottoNumbers;
import domain.PurchaseCount;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoNumberStrategy implements LottoNumberStrategy {

    private final List<LottoNumbers> lottos;

    public ManualLottoNumberStrategy(List<List<Integer>> inputs) {
        this.lottos = inputs.stream()
                .map(LottoNumbers::convertToLottoNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<LottoNumbers> generate(PurchaseCount count) {
        validateSize(count);
        return lottos;
    }

    private void validateSize(PurchaseCount count) {
        if (lottos.size() != count.getManualCount()) {
            throw new IllegalArgumentException("입력 값이 구매하려는 로또의 수와 일치하지 않습니다");
        }
    }
}
