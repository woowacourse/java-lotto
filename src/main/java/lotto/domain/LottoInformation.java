package lotto.domain;

public class LottoInformation {
    private final int lottoTimes; //로또회차
    private final String lottoNumbers;
    private final String winningNumbers;
    private final int winningBonusBall;

    public LottoInformation(int lottoTimes, String lottoNumbers, String winningNumbers, int winningBonusBall) {
        this.lottoTimes = lottoTimes;
        this.lottoNumbers = lottoNumbers;
        this.winningNumbers = winningNumbers;
        this.winningBonusBall = winningBonusBall;
    }


}
