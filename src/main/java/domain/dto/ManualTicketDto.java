package domain.dto;

import java.util.List;

public class ManualTicketDto {
	private final List<Integer> ticket;

	public ManualTicketDto(List<Integer> ticket) {
		this.ticket = List.copyOf(ticket);
	}

	public List<Integer> getTicket() {
		return ticket;
	}
}
