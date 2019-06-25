package lotto.dto;

public class LottoTicketDTO {
    private String lottoTicket;

    public LottoTicketDTO() {

    }

    public LottoTicketDTO(String lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public String getLottoTicket() {
        return lottoTicket;
    }

    public void setLottoTicket(String lottoTicket) {
        this.lottoTicket = lottoTicket;
    }
}
