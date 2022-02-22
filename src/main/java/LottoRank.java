public enum LottoRank {
    FIRST(20_0000_0000);

    private int prizeAmount;

    LottoRank(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
}
