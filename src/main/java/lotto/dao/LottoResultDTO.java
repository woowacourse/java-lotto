package lotto.dao;

public class LottoResultDTO {
    private String lottoResult;
    private String earningAmount;
    private String earningRate;

    public String getLottoResult() {
        return lottoResult;
    }

    public void setLottoResult(String lottoResult) {
        this.lottoResult = lottoResult;
    }

    public String getEarningAmount() {
        return earningAmount;
    }

    public void setEarningAmount(String earningAmount) {
        this.earningAmount = earningAmount;
    }

    public String getEarningRate() {
        return earningRate;
    }

    public void setEarningRate(String earningRate) {
        this.earningRate = earningRate;
    }
}
