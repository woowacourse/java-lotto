package lotto.domain;

import lotto.domain.generator.LottoRandomGenerator;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.Lottos;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.matchkind.WinningKind;
import lotto.domain.purchaseamount.ManualPurchaseCount;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.InputLottoDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private final TotalPurchaseAmount totalPurchaseAmount;
    private final ManualPurchaseCount manualPurchaseCount;

    public LottoMachine(final int totalPurchaseAmount, final List<InputLottoDto> manualLottos) {
        this.totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmount, LOTTO_PRICE);
        final int totalPurchaseCount = totalPurchaseAmount / LOTTO_PRICE;
        this.lottos = new Lottos(initializeLottos(totalPurchaseCount, convertToLotto(manualLottos)));
        this.manualPurchaseCount = new ManualPurchaseCount(manualLottos.size(), totalPurchaseCount);
    }

    private List<Lotto> convertToLotto(final List<InputLottoDto> manualLottos) {
        return manualLottos.stream()
                .map(InputLottoDto::getNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    private List<Lotto> initializeLottos(final int totalPurchaseAmount, final List<Lotto> manualLottos) {
        final LottoRandomGenerator lottoRandomGenerator = new LottoRandomGenerator();
        return lottoRandomGenerator.generateLottosExceptDefaultLottos(totalPurchaseAmount, manualLottos);
    }

    public int getCountOfAutoLottoNumbers() {
        return totalPurchaseAmount.getTotalPurchaseCount(LOTTO_PRICE) - manualPurchaseCount.getValue();
    }

    public int getCountOfManualLottoNumbers() {
        return manualPurchaseCount.getValue();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public WinningResult getMatchResult(final List<Integer> winningLottoNumbers, final int bonusNumber) {
        final WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        final Map<WinningKind, Integer> winningNumberByMatchKind = lottos.match(winningNumbers);
        return new WinningResult(winningNumberByMatchKind, totalPurchaseAmount);
    }
}
