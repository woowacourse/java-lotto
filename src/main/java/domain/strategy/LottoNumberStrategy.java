package domain.strategy;

import domain.LottoNumbers;
import domain.PurchaseCount;

import java.util.List;

public interface LottoNumberStrategy {
    List<LottoNumbers> generate(PurchaseCount count);
}
