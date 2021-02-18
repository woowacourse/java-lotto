package lotto.domain;

public class WinningNumbers {

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusBall;

    public WinningNumbers(String lottoNumbersValue, String bonusBallValue) {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbersValue);
        LottoNumber bonusBall = new LottoNumber(bonusBallValue);

        if (lottoTicket.contains(bonusBall)) {
            throw new RuntimeException();
        }

        this.lottoTicket = lottoTicket;
        this.bonusBall = bonusBall;
    }

    public Rank getRank(LottoTicket lottoTicket) {
        return Rank.getInstance(countMatches(lottoTicket), lottoTicket.contains(bonusBall));
    }

    //Todo :  private 수정해야함
    public int countMatches(LottoTicket lottoTicket) {
        return (int) lottoTicket.toUnmodifiableList().stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

}
