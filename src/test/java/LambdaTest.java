import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaTest {
    @Test
    public void lambdaTest(){

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий eroshenkoam/allure-example", () -> {
            $(".header-search-input").setValue("eroshenkoam/allure-example").pressEnter();
        });
        step("Переходим в репозиторий eroshenkoam/allure-example", () -> {
            $$(".repo-list-item").findBy(text("eroshenkoam/allure-example")).$("a").click();
        });
        step("Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером 68", () -> {
            $$(".js-navigation-container").findBy(text("#68")).should(Condition.visible);
        });

    }
    @AfterTest
    public void afterTest(){
        Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
    }



}
