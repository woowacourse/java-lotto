package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.model.exception.LottoException;
import lotto.view.exception.InvalidFormatException;
import lotto.view.exception.LottoFinishedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputTemplateTest {

    abstract class Mock {

        private int time = 0;

        protected void call() {
            time++;
        }

        protected int calledTime() {
            return time;
        }

        public void verifyCalledOnce() {
            assertThat(calledTime() == 1).isTrue();
        }

        public void verifyCalledTimes(int time) {
            assertThat(calledTime()).isEqualTo(time);
        }

        public void verifyIsNotCalled() {
            assertThat(calledTime()).isEqualTo(0);
        }
    }

    class MockSupplier extends Mock {

        public Object get() {
            call();
            return new Object();
        }

        public Object throwLottoException() {
            call();
            throw new LottoException() {
            };
        }

        public Object throwRuntimeException(String message) {
            call();
            throw new RuntimeException(message);
        }
    }

    class MockConsumer extends Mock {

        private List<Object> values = new ArrayList<>();

        public void accept(Object o) {
            values.add(o);
            call();
        }

        public void throwInvalidFormatException(Object o, String message) {
            values.add(o);
            call();
            throw new InvalidFormatException(message);
        }

        public void throwRuntimeException(Object o, String message) {
            values.add(o);
            call();
            throw new RuntimeException(message);
        }

        public void verifyCalledOnce(Class<?> cls) {
            verifyCalledOnce();
            assertThat(cls).isAssignableFrom(values.get(0).getClass());
        }

        public void verifyCalledOnce(Object value) {
            verifyCalledOnce();
            assertThat(values.get(0)).isEqualTo(value);
        }

        public void verifyCalledTimes(int time, Class<?> cls) {
            verifyCalledTimes(time);
            for (Object value : values) {
                assertThat(cls).isAssignableFrom(value.getClass());
            }
        }

        public void verifyCalledTimes(int time, Object... values) {
            verifyCalledTimes(time);
            assertThat(this.values).containsExactlyInAnyOrder(values);
        }
    }

    private static final String MESSAGE = "message";
    private static final String INPUT_TEXT = "text";
    private static final String REPLY_YES = "y";
    private static final String REPLY_NO = "n";

    private MockSupplier supplier;
    private MockConsumer consumer;
    private MockConsumer exceptionHandler;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        supplier = new MockSupplier();
        consumer = new MockConsumer();
        exceptionHandler = new MockConsumer();
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("정상적으로 Supplier 호출")
    void executeSupplierOnce() {
        InputTemplate inputTemplate = inputTemplateWithInputText("");

        assertThatCode(
            () -> inputTemplate.repeatablyExecute(supplier::get, exceptionHandler::accept))
            .doesNotThrowAnyException();

        supplier.verifyCalledOnce();
        exceptionHandler.verifyIsNotCalled();
    }

    @Test
    @DisplayName("LottoException 발생 시 Supplier 와 Consumer 호출")
    void executeExceptionHandler() {
        InputTemplate inputTemplate = inputTemplateWithInputText(REPLY_NO);

        assertThatCode(
            () -> inputTemplate
                .repeatablyExecute(supplier::throwLottoException, exceptionHandler::accept))
            .isInstanceOf(LottoFinishedException.class);

        supplier.verifyCalledOnce();
        exceptionHandler.verifyCalledOnce(LottoException.class);
    }

    @Test
    @DisplayName("LottoException 발생 시 원할 경우 반복해서 Supplier 와 Consumer 호출")
    void executeSupplierMultiple() {
        InputTemplate inputTemplate = inputTemplateWithInputText(REPLY_YES, REPLY_YES, REPLY_NO);

        assertThatCode(
            () -> inputTemplate
                .repeatablyExecute(supplier::throwLottoException, exceptionHandler::accept))
            .isInstanceOf(LottoFinishedException.class);

        supplier.verifyCalledTimes(3);
        exceptionHandler.verifyCalledTimes(3, LottoException.class);
    }

    @Test
    @DisplayName("LottoException 외의 예외 발생 시 반복하지 않고 Consumer 미호출")
    void notCatchOtherException() {
        InputTemplate inputTemplate = inputTemplateWithInputText(REPLY_YES, REPLY_YES, REPLY_NO);

        assertThatCode(
            () -> inputTemplate.repeatablyExecute(() -> supplier.throwRuntimeException(MESSAGE),
                exceptionHandler::accept))
            .isInstanceOf(RuntimeException.class)
            .hasMessage(MESSAGE);

        supplier.verifyCalledOnce();
        exceptionHandler.verifyIsNotCalled();
    }

    @Test
    @DisplayName("정상적인 문자열 입력")
    void inputOnce() {
        InputTemplate inputTemplate = inputTemplateWithInputText(INPUT_TEXT);

        assertThatCode(() -> inputTemplate
            .repeatablyInput(MESSAGE, consumer::accept, exceptionHandler::accept))
            .doesNotThrowAnyException();

        consumer.verifyCalledOnce(INPUT_TEXT);
        exceptionHandler.verifyIsNotCalled();
        assertThat(outputStream.toString()).contains(MESSAGE);
    }

    @Test
    @DisplayName("InvalidFormatException 예외 발생 시 다시 입력")
    void invalidFormatTextInput() {
        InputTemplate inputTemplate = inputTemplateWithInputText(INPUT_TEXT, REPLY_YES, INPUT_TEXT,
            REPLY_NO);

        assertThatCode(() -> inputTemplate
            .repeatablyInput(MESSAGE,
                text -> consumer.throwInvalidFormatException(text, MESSAGE),
                exceptionHandler::accept))
            .isInstanceOf(LottoFinishedException.class);

        consumer.verifyCalledTimes(2, INPUT_TEXT, INPUT_TEXT);
        exceptionHandler.verifyCalledTimes(2, InvalidFormatException.class);
        assertThat(outputStream.toString()).contains(MESSAGE);
    }

    @Test
    @DisplayName("InvalidFormatException 외의 예외는 미처리")
    void notCatchWithOutInvalidFormatException() {
        InputTemplate inputTemplate = inputTemplateWithInputText(INPUT_TEXT, REPLY_YES, INPUT_TEXT,
            REPLY_NO);

        assertThatCode(() -> inputTemplate
            .repeatablyInput(MESSAGE,
                text -> consumer.throwRuntimeException(text, MESSAGE),
                exceptionHandler::accept))
            .isInstanceOf(RuntimeException.class)
            .hasMessage(MESSAGE);

        consumer.verifyCalledOnce(INPUT_TEXT);
        exceptionHandler.verifyIsNotCalled();
        assertThat(outputStream.toString()).contains(MESSAGE);
    }

    private InputTemplate inputTemplateWithInputText(String... text) {
        String joinedText = Stream.of(text).collect(Collectors.joining("\n"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(joinedText.getBytes());
        return new InputTemplate(inputStream, outputStream);
    }
}
