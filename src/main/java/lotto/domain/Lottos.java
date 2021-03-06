package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    public static final String NUMBER_TYPE_ERROR = "숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "입력하신 금액으로 구입할 수 없습니다";
    private final List<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(Money money, String inputManualCount) {
        int manualCount = validateManualCount(inputManualCount, money.count());
        int randomCount = money.count() - manualCount;
        lottoGroup.addAll(generateLottoGroup(randomCount));
    }

    private int validateManualCount(String inputManual, int totalCount) {
        int manualCount = validateType(inputManual);
        validateRange(manualCount, totalCount);
        return manualCount;
    }

    private int validateType(String inputManual) {
        try {
            return Integer.parseInt(inputManual);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateRange(int manualCount, int totalCount) {
        if (manualCount < 0 || manualCount > totalCount) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    private List<Lotto> generateLottoGroup(int count) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            generatedLottos.add(new Lotto(lottoGenerator.generateLottoNums()));
        }
        return generatedLottos;
    }

    public Result drawLotto(WinningLotto winningLotto) {
        List<Rank> wins = new ArrayList<>();
        for (Lotto lotto : lottoGroup) {
            Rank rank = winningLotto.findRank(lotto);
            wins.add(rank);
        }
        return new Result(wins);
    }

    public ArrayList<ArrayList<Integer>> changeFormat() {
        ArrayList<ArrayList<Integer>> displayFormat = new ArrayList<>();
        for (Lotto lotto : lottoGroup) {
            displayFormat.add(lotto.changeToList());
        }
        return displayFormat;
    }

    public void generateManualLotto(List<String> manualNumberInput) {
        for(String manualNumber: manualNumberInput) {
            lottoGroup.add(Lotto.from(manualNumber));
        }
    }
}