public class LottoResult {
    public int getTotalPrizeAmount() {
        return LottoRank.FIRST.getPrizeAmount();
    }

    public int getCountByRank(LottoRank first) {
        return 1;
    }

    public void add(LottoRank rank) {

    }
}
