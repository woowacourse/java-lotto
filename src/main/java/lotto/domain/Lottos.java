package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    public static final String NUMBER_TYPE_ERROR = "숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "입력하신 금액으로 구입할 수 없습니다";
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(final Money money, final String inputManualCount) {
        final int manualCount = validateManualCount(inputManualCount, money.count());
        final int randomCount = money.count() - manualCount;
        lottoGroup.addAll(generateLottoGroup(randomCount));
    }

    private int validateManualCount(final String inputManual, final int totalCount) {
        final int manualCount = validateType(inputManual);
        validateRange(manualCount, totalCount);
        return manualCount;
    }

    private int validateType(final String inputManual) {
        try {
            return Integer.parseInt(inputManual);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateRange(final int manualCount, final int totalCount) {
        if (manualCount < 0 || manualCount > totalCount) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private List<Lotto> generateLottoGroup(final int count) {
        final LottoGenerator lottoGenerator = new LottoGenerator();
        final List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generatedLottos.add(new Lotto(lottoGenerator.generateLottoNums()));
        }
        return generatedLottos;
    }

    public Result drawLotto(final WinningLotto winningLotto) {
        final List<Rank> wins = new ArrayList<>();
        for (final Lotto lotto : lottoGroup) {
            final Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
        return new Result(wins);
    }

    public ArrayList<ArrayList<Integer>> changeFormat() {
        final ArrayList<ArrayList<Integer>> displayFormat = new ArrayList<>();
        for (final Lotto lotto : lottoGroup) {
            displayFormat.add(lotto.changeToList());
        }
        return displayFormat;
    }

    public void generateManualLotto(final List<String> manualNumberInput) {
        for (final String manualNumber : manualNumberInput) {
            lottoGroup.add(Lotto.from(manualNumber));
        }
    }
}