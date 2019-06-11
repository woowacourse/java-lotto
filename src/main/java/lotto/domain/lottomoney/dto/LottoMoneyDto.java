package lotto.domain.lottomoney.dto;

public class LottoMoneyDto {
    private long numOfLotto;
    private long change;

    public LottoMoneyDto(long numOfLotto, long change) {
        this.numOfLotto = numOfLotto;
        this.change = change;
    }

    public long getNumOfLotto() {
        return numOfLotto;
    }

    public long getChange() {
        return change;
    }
}
