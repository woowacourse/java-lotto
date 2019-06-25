package lotto.dto;

import java.util.Arrays;

public class PurchaseDTO {
    private int purchaseAmount;
    private int manualCount;
    private String[] lottoNumbers;

    public PurchaseDTO(int purchaseAmount, int manualCount, String[] lottoNumbers) {
        this.purchaseAmount = purchaseAmount;
        this.manualCount = manualCount;
        this.lottoNumbers = lottoNumbers;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public void setManualCount(int manualCount) {
        this.manualCount = manualCount;
    }

    public String[] getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(String[] lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "purchaseAmount=" + purchaseAmount +
                ", manualCount=" + manualCount +
                ", lottoNumbers=" + Arrays.toString(lottoNumbers) +
                '}';
    }
}
