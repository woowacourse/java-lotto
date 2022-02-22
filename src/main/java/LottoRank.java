public enum LottoRank {
    FIRST(2_000_000_000), SECOND(30_000_000), THIRD(1_500_000);

    private int prizeAmount;

    LottoRank(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }
}
