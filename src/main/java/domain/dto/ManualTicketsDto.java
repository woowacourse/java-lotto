package domain.dto;

import java.util.List;

public class ManualTicketsDto {
	private final List<List<Integer>> manualTickets;

	public ManualTicketsDto(List<List<Integer>> manualTickets) {
		this.manualTickets = manualTickets;
	}

	public int size() {
		return manualTickets.size();
	}

	public List<List<Integer>> getManualTickets() {
		return manualTickets;
	}
}
