package lotto.view;

import lotto.domain.number.LottoNumber;
import lotto.domain.number.SerialLottoNumber;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoTickets;
import lotto.domain.result.Rank;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}

	public static void printLottoTicketsCount(int manualTicketCount, int autoTicketCount) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("수동으로 ")
				.append(manualTicketCount)
				.append("장, 자동으로 ")
				.append(autoTicketCount)
				.append("장 구매했습니다.");

		System.out.println(stringBuilder.toString());
	}

	public static void printLottoTickets(LottoTickets lottoTickets) {
		for (SerialLottoNumber lottoTicket : lottoTickets.getLottoTickets()) {
			System.out.println(lottoTicket.getLottoNumbers()
					.stream()
					.map(LottoNumber::getLottoNumber)
					.map(lottoNumber -> Integer.toString(lottoNumber))
					.collect(Collectors.joining(", ")));
		}
	}

	public static void printLottoResult(LottoResult lottoResult) {
		Map<Rank, Integer> raceCount = lottoResult.getWinningRankCount();
		for (Map.Entry<Rank, Integer> entry : raceCount.entrySet()) {
			StringBuilder stringBuilder = new StringBuilder(entry.getKey().getInformation());
			stringBuilder.append(" - ")
					.append(entry.getValue())
					.append("개");
			System.out.println(stringBuilder.toString());
		}
	}
}
