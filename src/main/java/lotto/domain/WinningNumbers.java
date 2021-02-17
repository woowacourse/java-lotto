package lotto.domain;

public class WinningNumbers {
    LottoTicket lottoTicket;

    public WinningNumbers(String lottoNumbersValue, String bonusBallValue) {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbersValue);
        LottoNumber bonusBall = new LottoNumber(bonusBallValue);

        if (lottoTicket.contains(bonusBall)) {
            throw new RuntimeException();
        }

        this.lottoTicket = lottoTicket;
    }

    public int countMatches(LottoTicket lottoTicket) {
        return (int) lottoTicket.getUnmodifiableList().stream()
            .filter(lottoNumber -> this.lottoTicket.contains(lottoNumber))
            .count();
    }

    public Rank getRank(LottoTicket lottoTicket) {
        return Rank.FIRST_PLACE;
    }

}
