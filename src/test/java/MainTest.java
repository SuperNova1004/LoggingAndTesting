import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled // Отключаем тест, чтобы он не выполнялся при запуске всех тестов (выдало 20 секунд 375 милисекунд)
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS) // Проверяем, что выполнение не превышает 22 секунд
    public void main_Test() throws Exception {
        Main.main(new String[0]); // Запускаем метод main
    }
}
