package domain.dto;

import java.util.Collections;
import java.util.List;

public class ManualTicketsDto {
	private final List<ManualTicketDto> manualTickets;

	public ManualTicketsDto(List<ManualTicketDto> manualTickets) {
		this.manualTickets = List.copyOf(manualTickets);
	}

	public int size() {
		return manualTickets.size();
	}

	public List<ManualTicketDto> getManualTickets() {
		return manualTickets;
	}
}
