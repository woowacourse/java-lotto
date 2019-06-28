package lotto;

import java.util.List;

public class PreviousPurchaseDTO {
    private int amount = 0;
    private int autoCount = 0;
    private int manualCount = 0;
    private int rank1st = 0;
    private int rank2nd = 0;
    private int rank3rd = 0;
    private int rank4th = 0;
    private int rank5th = 0;
    private int rankMiss = 0;
    private int totalPrize = 0;
    private float profitRate = 0;
    private List<PreviousLottoDTO> lottoList;

    public int getAmount() {
        return amount;
    }

    public PreviousPurchaseDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public PreviousPurchaseDTO setAutoCount(int autoCount) {
        this.autoCount = autoCount;
        return this;
    }

    public int getManualCount() {
        return manualCount;
    }

    public PreviousPurchaseDTO setManualCount(int manualCount) {
        this.manualCount = manualCount;
        return this;
    }

    public int getRank1st() {
        return rank1st;
    }

    public PreviousPurchaseDTO setRank1st(int rank1st) {
        this.rank1st = rank1st;
        return this;
    }

    public int getRank2nd() {
        return rank2nd;
    }

    public PreviousPurchaseDTO setRank2nd(int rank2nd) {
        this.rank2nd = rank2nd;
        return this;
    }

    public int getRank3rd() {
        return rank3rd;
    }

    public PreviousPurchaseDTO setRank3rd(int rank3rd) {
        this.rank3rd = rank3rd;
        return this;
    }

    public int getRank4th() {
        return rank4th;
    }

    public PreviousPurchaseDTO setRank4th(int rank4th) {
        this.rank4th = rank4th;
        return this;
    }

    public int getRank5th() {
        return rank5th;
    }

    public PreviousPurchaseDTO setRank5th(int rank5th) {
        this.rank5th = rank5th;
        return this;
    }

    public int getRankMiss() {
        return rankMiss;
    }

    public PreviousPurchaseDTO setRankMiss(int rankMiss) {
        this.rankMiss = rankMiss;
        return this;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public PreviousPurchaseDTO setTotalPrize(int totalPrize) {
        this.totalPrize = totalPrize;
        return this;
    }

    public float getProfitRate() {
        return profitRate;
    }

    public PreviousPurchaseDTO setProfitRate(float profitRate) {
        this.profitRate = profitRate;
        return this;
    }

    public List<PreviousLottoDTO> getLottoList() {
        return lottoList;
    }

    public PreviousPurchaseDTO setLottoList(List<PreviousLottoDTO> lottoList) {
        this.lottoList = lottoList;
        return this;
    }
}
