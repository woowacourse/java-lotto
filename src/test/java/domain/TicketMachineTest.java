package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.dto.ManualTicketDto;
import domain.dto.ManualTicketsDto;
import domain.strategy.AutoStrategy;

public class TicketMachineTest {

	@DisplayName("개수에 맞게 로또 티켓들이 생성되는지 테스트")
	@Test
	void generate() {
		int money = 14000;
		TicketCounter ticketCounter = new TicketCounter(money, 3);
		List<ManualTicketDto> manualTickets = new ArrayList<>();
		manualTickets.add(new ManualTicketDto(Arrays.asList(1, 8, 11, 31, 41, 42)));
		manualTickets.add(new ManualTicketDto(Arrays.asList(13, 14, 16, 38, 42, 45)));
		manualTickets.add(new ManualTicketDto(Arrays.asList(7, 11, 30, 40, 42, 43)));

		ManualTicketsDto manualTicketsDto = new ManualTicketsDto(manualTickets);

		Tickets tickets = TicketMachine.buyTickets(ticketCounter, manualTicketsDto, new AutoStrategy());

		assertThat(tickets.size()).isEqualTo(14);
	}
}
