package verus.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import verus.common.MockConsumer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import verus.exception.ApplicationFinishedException;
import verus.exception.InvalidFormatException;

public class InputTemplateTest {

    private static final String MESSAGE = "message";
    private static final String INPUT_TEXT = "text";
    private static final String REPLY_YES = "y";
    private static final String REPLY_NO = "n";

    private MockConsumer consumer;
    private MockConsumer exceptionHandler;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        consumer = new MockConsumer();
        exceptionHandler = new MockConsumer();
        outputStream = new ByteArrayOutputStream();
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
            .isInstanceOf(ApplicationFinishedException.class);

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
            .hasMessageContaining(MESSAGE);

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
