package verus.controller;

import static org.assertj.core.api.Assertions.assertThatCode;

import verus.common.MockConsumer;
import verus.common.MockRunnable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.model.exception.LottoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import verus.exception.ApplicationFinishedException;

public class RunnableTemplateTest {

    private static final String MESSAGE = "message";
    private static final String REPLY_YES = "y";
    private static final String REPLY_NO = "n";

    private MockRunnable runnable;
    private MockConsumer exceptionHandler;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        runnable = new MockRunnable();
        exceptionHandler = new MockConsumer();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("정상적으로 Supplier 호출")
    void executeSupplierOnce() {
        RunnableTemplate runnableTemplate = runnableTemplateWithInputText("");

        assertThatCode(
            () -> runnableTemplate
                .repeatablyRun(runnable::run, exceptionHandler::accept, LottoException.class))
            .doesNotThrowAnyException();

        runnable.verifyCalledOnce();
        exceptionHandler.verifyIsNotCalled();
    }

    @Test
    @DisplayName("LottoException 발생 시 Supplier 와 Consumer 호출")
    void executeExceptionHandler() {
        RunnableTemplate runnableTemplate = runnableTemplateWithInputText(REPLY_NO);

        assertThatCode(
            () -> runnableTemplate
                .repeatablyRun(runnable::throwLottoException, exceptionHandler::accept,
                    LottoException.class))
            .isInstanceOf(ApplicationFinishedException.class);

        runnable.verifyCalledOnce();
        exceptionHandler.verifyCalledOnce(LottoException.class);
    }

    @Test
    @DisplayName("LottoException 발생 시 원할 경우 반복해서 Supplier 와 Consumer 호출")
    void executeSupplierMultiple() {
        RunnableTemplate runnableTemplate = runnableTemplateWithInputText(REPLY_YES, REPLY_YES, REPLY_NO);

        assertThatCode(
            () -> runnableTemplate
                .repeatablyRun(runnable::throwLottoException, exceptionHandler::accept,
                    LottoException.class))
            .isInstanceOf(ApplicationFinishedException.class);

        runnable.verifyCalledTimes(3);
        exceptionHandler.verifyCalledTimes(3, LottoException.class);
    }

    @Test
    @DisplayName("LottoException 외의 예외 발생 시 반복하지 않고 Consumer 미호출")
    void notCatchOtherException() {
        RunnableTemplate runnableTemplate = runnableTemplateWithInputText(REPLY_YES, REPLY_YES, REPLY_NO);

        assertThatCode(
            () -> runnableTemplate.repeatablyRun(() -> runnable.throwRuntimeException(MESSAGE),
                exceptionHandler::accept, LottoException.class))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining(MESSAGE);

        runnable.verifyCalledOnce();
        exceptionHandler.verifyIsNotCalled();
    }

    private RunnableTemplate runnableTemplateWithInputText(String... text) {
        String joinedText = Stream.of(text).collect(Collectors.joining("\n"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(joinedText.getBytes());
        return new RunnableTemplate(inputStream, outputStream);
    }

}
