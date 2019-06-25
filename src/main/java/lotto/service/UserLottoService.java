package lotto.service;

import lotto.dao.UserLottoDao;
import lotto.domain.LottoCreator;
import lotto.domain.Ticket;
import lotto.domain.TicketCreator;
import lotto.domain.UserLottos;
import lotto.dto.UserLottoDto;

import java.util.ArrayList;
import java.util.List;

public class UserLottoService {
    private static final TicketCreator ticketCreator = new LottoCreator();
    private static final UserLottoDao dao = UserLottoDao.getDao();

    private UserLottoService() {

    }

    public static UserLottos userLottos(int lottoMoney, int manualCount, List<List<Integer>> numbers) {
        UserLottos userLottos = new UserLottos(generate(lottoMoney, manualCount, numbers));
        UserLottoDto dto = new UserLottoDto(userLottos);
        dao.insertUserLottos(dto);
        return userLottos;
    }

    private static List<Ticket> generate(List<List<Integer>> lottoNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        for (List<Integer> number : lottoNumbers) {
            tickets.add(ticketCreator.create(number));
        }
        return tickets;
    }

    private static List<Ticket> generate(int lottoMoney, int manualCount, List<List<Integer>> manualNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            tickets.add(ticketCreator.create(manualNumbers.get(i)));
        }
        return generateAuto(lottoMoney, tickets);
    }

    private static List<Ticket> generateAuto(int lottoMoney, List<Ticket> tickets) {
        int autoTicketCount = (lottoMoney / ticketCreator.unitPrice()) - tickets.size();
        for (int i = 0; i < autoTicketCount; i++) {
            tickets.add(ticketCreator.create());
        }
        return tickets;
    }

    public static UserLottos userLottos(int round) {
        return new UserLottos(generate(dao.selectUserLottos(round)));
    }

    public static UserLottos currentUserLottos() {
        return new UserLottos(generate(dao.currentUserLottos()));
    }
}
