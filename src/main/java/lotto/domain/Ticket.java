package lotto.domain;

public interface Ticket {

    LottoNumbers ticketNumbers();

    boolean contains(LottoNumber number);
}
