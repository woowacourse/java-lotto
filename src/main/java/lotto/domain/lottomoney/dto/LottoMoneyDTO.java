package lotto.domain.lottomoney.dto;

public class LottoMoneyDTO {
    private long numOfLotto;
    private long change;

    public LottoMoneyDTO(long numOfLotto, long change) {
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
