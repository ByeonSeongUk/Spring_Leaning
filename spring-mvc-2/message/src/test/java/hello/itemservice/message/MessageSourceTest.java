package hello.itemservice.message;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    /**
     * 1. Basic
     */
    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    /**
     * 2. Not Found Code
     */
    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    /**
     * 3. DefaultMessage
     */
    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지",null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    /**
     * 4. ArgumentMessage
     */
    @Test
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    /**
     * 5. Location
     */
    @Test
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}