package lotto.domain.ticket;

import lotto.domain.number.SerialLottoNumber;

import java.util.List;

public interface TicketsGenerator {
	List<SerialLottoNumber> create();
}
