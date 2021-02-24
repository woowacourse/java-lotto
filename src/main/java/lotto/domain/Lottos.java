package lotto.domain;

import java.util.ArrayList;
import java.util.Map;

public class Lottos {
    public static final String NUMBER_TYPE_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "[ERROR] 입력하신 금액으로 구입할 수 없습니다";
    private static int manualCount;
    private static int randomCount;
    private final ArrayList<Lotto> lottoGroup = new ArrayList<>();

    public Lottos(String inputTotalMoney, String inputManualCount) {
        Money money = new Money(inputTotalMoney);
        manualCount = validateManualCount(inputManualCount, money.count());
        randomCount = money.count() - manualCount;
        generateLottoGroup(randomCount);
    }

    public static String findResult(Map<Rank, Integer> countByRank) {
        Money.findEarning(countByRank);
        return Money.findEarningRate();
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

    private void generateLottoGroup(int count) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        for (int i = 0; i < count; i++) {
            Lotto generatedLotto = new Lotto(lottoGenerator.generateLottoNums());
            lottoGroup.add(generatedLotto);
        }
    }

    public ArrayList<Lotto> getLottoGroup() {
        return this.lottoGroup;
    }

    public void addManualLotto(Lotto lotto) {
        lottoGroup.add(lotto);
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getRandomCount() {
        return randomCount;
    }
}