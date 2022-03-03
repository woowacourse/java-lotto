package lotto.controller.dto;

import java.util.Collections;
import java.util.List;

public class PurchaseInfoDto {

    private final int money;
    private final int manualCount;
    private final List<List<Integer>> manualNumbers;

    public PurchaseInfoDto(int money, int manualCount, List<List<Integer>> manualNumbers) {
        this.money = money;
        this.manualCount = manualCount;
        this.manualNumbers = manualNumbers;
    }

    public static PurchaseInfoDto valueOf(int money, int manualCount, List<List<Integer>> manualNumbers) {
        return new PurchaseInfoDto(money, manualCount, manualNumbers);
    }

    public int getMoney() {
        return money;
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<List<Integer>> getManualNumbers() {
        return Collections.unmodifiableList(manualNumbers);
    }
}
