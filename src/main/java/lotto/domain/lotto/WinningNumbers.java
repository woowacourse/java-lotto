package lotto.domain.lotto;

public class WinningNumbers {

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(String lottoNumbersValue, String bonusBallValue) {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbersValue);
        LottoNumber bonusBall = LottoNumber.valueOf(bonusBallValue);

        if (lottoTicket.contains(bonusBall)) {
            throw new IllegalArgumentException("당첨번호와 보너스볼은 중복되지 않아야 합니다");
        }

        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    public Rank matchRank(LottoTicket lottoTicket) {
        return Rank.matchRank(
                countMatches(lottoTicket),
                lottoTicket.contains(bonusBall));
    }

    private int countMatches(LottoTicket lottoTicket) {
        return (int) lottoTicket.toUnmodifiableList().stream()
                .filter(this.lottoTicket::contains)
                .count();
    }
}
