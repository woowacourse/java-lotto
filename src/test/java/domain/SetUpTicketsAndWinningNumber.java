package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.strategy.CustomTicketingStrategy;

class SetUpTicketsAndWinningNumber {
	private Tickets tickets;
	private WinningNumber winningNumber;

	public void setUp() {
		int money = 14000;
		TicketCounter manualCount = new TicketCounter(money, 3);
		List<List<Integer>> manualNumbers = getManualTickets();

		List<List<Integer>> autoNumbers = new ArrayList<>();
		autoNumbers.add(Arrays.asList(1, 8, 11, 31, 41, 42));
		autoNumbers.add(Arrays.asList(13, 14, 16, 38, 42, 45));
		autoNumbers.add(Arrays.asList(7, 11, 30, 40, 42, 43));
		autoNumbers.add(Arrays.asList(2, 13, 22, 32, 38, 45));
		autoNumbers.add(Arrays.asList(23, 25, 33, 36, 39, 41));
		autoNumbers.add(Arrays.asList(1, 3, 5, 14, 22, 45));
		autoNumbers.add(Arrays.asList(5, 9, 38, 41, 43, 44));
		autoNumbers.add(Arrays.asList(2, 8, 9, 18, 19, 21));
		autoNumbers.add(Arrays.asList(13, 14, 18, 21, 23, 35));
		autoNumbers.add(Arrays.asList(17, 21, 29, 37, 42, 45));
		autoNumbers.add(Arrays.asList(3, 8, 27, 30, 35, 44));

		CustomTicketingStrategy customLottoGenerator = new CustomTicketingStrategy();
		customLottoGenerator.initNumbers(autoNumbers);

		tickets = TicketMachine.generateTickets(manualCount, manualNumbers, customLottoGenerator);

		List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		int bonusBall = 7;
		winningNumber = new WinningNumber(winningNumbers, bonusBall);
	}

	private List<List<Integer>> getManualTickets() {
		List<List<Integer>> manualTickets = new ArrayList<>();
		manualTickets.add(Arrays.asList(8, 21, 23, 41, 42, 43));
		manualTickets.add(Arrays.asList(3, 5, 11, 16, 32, 38));
		manualTickets.add(Arrays.asList(7, 11, 16, 35, 36, 44));

		return manualTickets;
	}

	public Tickets getTickets() {
		return tickets;
	}

	public WinningNumber getWinningNumber() {
		return winningNumber;
	}
}
