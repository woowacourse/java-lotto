package lotto;

import lotto.controller.LottoController;
import lotto.domain.ticket.generator.RandomTicketGenerator;
import lotto.domain.ticket.generator.TicketGenerator;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.input.reader.ConsoleReader;
import lotto.view.input.reader.Reader;
import lotto.view.output.OutputView;

public class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    public final LottoController lottoController;
    public final LottoService lottoService;
    public final TicketGenerator ticketGenerator;
    public final OutputView outputView;
    public final InputView inputView;
    public final Reader reader;

    private AppConfig() {
        this.reader = initReader();
        this.inputView = initInputView(reader);
        this.outputView = initOutputView();
        this.ticketGenerator = initTicketGenerator();
        this.lottoService = initLottoService(ticketGenerator);
        this.lottoController = initLottoController(lottoService, inputView, outputView);
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private Reader initReader() {
        return new ConsoleReader();
    }

    private InputView initInputView(final Reader reader) {
        return new InputView(reader);
    }

    private OutputView initOutputView() {
        return new OutputView();
    }

    private TicketGenerator initTicketGenerator() {
        return new RandomTicketGenerator();
    }

    private LottoService initLottoService(final TicketGenerator ticketGenerator) {
        return new LottoService(ticketGenerator);
    }

    private LottoController initLottoController(final LottoService lottoService,
                                                final InputView inputView,
                                                final OutputView outputView) {
        return new LottoController(lottoService, inputView, outputView);
    }

}
