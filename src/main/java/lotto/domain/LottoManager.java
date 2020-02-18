package lotto.domain;

import java.util.List;

public class LottoManager {

	public static long compareTicket(List<Integer> ticket, List<Integer> winNumber) {
		return ticket.stream()
			.filter(x -> winNumber.contains(x))
			.count();
	}
}
