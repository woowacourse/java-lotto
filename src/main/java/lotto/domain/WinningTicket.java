package lotto.domain;

public class WinningTicket extends LottoTicket {
    private final LottoBall bonusBall;

    public WinningTicket(String inputTicketNumber,int bonusBall) {
        super(inputTicketNumber);
        this.bonusBall = new LottoBall(bonusBall);
    }

    public long hitLottoBall(LottoTicket lottoTicket){
        return lottoTicket.getLottoTicket()
                .stream()
                .filter(this.lottoTicket::contains)
                .count();
    }

    public boolean hitBonusBall(LottoTicket lottoTicket){
        return lottoTicket.getLottoTicket()
                .stream()
                .anyMatch(lottoBall -> lottoBall.getLottoBall() == this.bonusBall.getLottoBall());
    }
}
