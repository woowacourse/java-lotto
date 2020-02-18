package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static final int START_LOTTO_NO = 1;
	private static final int END_LOTTO_NO = 45;
	private static final int TICKET_FROM_INDEX = 0;
	private static final int TICKET_TO_INDEX = 6;

	private static List<Integer> lottoBox = new ArrayList<>();

	static {
		for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
			lottoBox.add(count);
		}
	}

	public static Lotto create() {
		List<Integer> ticket = new ArrayList<>();
		ticket.addAll(lottoBox);
		Collections.shuffle(ticket);
		return new Lotto(ticket.subList(TICKET_FROM_INDEX, TICKET_TO_INDEX));
	}
}
