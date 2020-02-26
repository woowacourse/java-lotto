package domain.lottonumbers;


public class LottoTicket extends LottoNumbers {

    public LottoTicket(LottoTicketDto lottoticketDto) {
        super(lottoticketDto.getLottoNumbers());
    }
}