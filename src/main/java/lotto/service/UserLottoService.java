package lotto.service;

import lotto.dao.UserLottoDao;
import lotto.domain.LottoCreator;
import lotto.domain.UserLottos;
import lotto.dto.UserLottoDto;

public class UserLottoService {
    private static final UserLottoDao dao = UserLottoDao.getDao();

    private UserLottoService() {

    }

    public static UserLottos userLottos(UserLottoTranslator translator) {
        UserLottos userLottos = LottoCreator.getLottoCreator().createUserLotto(translator.getLottoMoney(), translator.getManualCount(), translator.getManualNumbers());
        UserLottoDto dto = new UserLottoDto(userLottos);
        dao.insertUserLottos(dto);
        return userLottos;
    }

    public static UserLottos userLottos(UserLottoDto dto) {
        UserLottos userLottos = LottoCreator.getLottoCreator().createUserLotto(dto.getLottoMoney(), dto.getManualCount(), dto.getNumbers());
        dao.insertUserLottos(dto);
        return userLottos;
    }

    public static UserLottos userLottos(int round) {
        UserLottoDto dto = dao.selectUserLottos(round);
        return LottoCreator.getLottoCreator().createUserLotto(dto.getNumbers());
    }

    public static UserLottos currentUserLottos() {
        UserLottoDto dto = dao.currentUserLottos();
        return LottoCreator.getLottoCreator().createUserLotto(dto.getNumbers());
    }
}
