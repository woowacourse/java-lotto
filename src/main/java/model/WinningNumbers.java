package model;

import java.util.List;

public class WinningNumbers extends LottoNumbers {

    private final LottoNumber bonusBall;

    private WinningNumbers(List<LottoNumber> lottoNumbers, LottoNumber bonusBall) {
        super(lottoNumbers);
        this.bonusBall = bonusBall;
    }

    public static WinningNumbers of(List<LottoNumber> lottoNumbers, LottoNumber bonusBall) {
        return new WinningNumbers(lottoNumbers, bonusBall);
    }

    public int countContaining(LottoTicket lottoTicket) {
        return (int) lottoNumbers.stream()
                .filter(lottoTicket::contains)
                .count();
    }

    public boolean containBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.contains(bonusBall);
    }
}
