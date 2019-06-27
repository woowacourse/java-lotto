package lotto.dto;

import java.util.List;

public class PurchaseDto {
    private int budget;
    private int manualCount;
    private List<String> manualLottos;
    private int autoCount;
    private List<String> autoLottos;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getManualCount() {
        return manualCount;
    }

    public void setManualCount(int manualCount) {
        this.manualCount = manualCount;
    }

    public List<String> getManualLottos() {
        return manualLottos;
    }

    public void setManualLottos(List<String> manualLottos) {
        this.manualLottos = manualLottos;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public void setAutoCount(int autoCount) {
        this.autoCount = autoCount;
    }

    public List<String> getAutoLottos() {
        return autoLottos;
    }

    public void setAutoLottos(List<String> autoLottos) {
        this.autoLottos = autoLottos;
    }
}
