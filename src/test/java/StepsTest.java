import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StepsTest {

    @Step("Открываем главную страницу")
    public void openGitHub() {
        open("https://github.com");
    }
    @Step("Ищем репозиторий eroshenkoam/allure-example")
    public void searchRepo() {
        $(".header-search-input").setValue("eroshenkoam/allure-example").pressEnter();
    }
    @Step("Переходим в репозиторий eroshenkoam/allure-example")
    public void openRepo() {
        $$(".repo-list-item").findBy(text("eroshenkoam/allure-example")).$("a").click();
    }
    @Step("Открываем таб Issue")
    public void openIssue() {
        $("#issues-tab").click();
    }
    @Step("Проверяем наличие Issue с номером 68")
    public void checkIssueWithNumber() {
        $$(".js-navigation-container").findBy(text("#68")).should(Condition.visible);
    }
    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
    }

    @Test
    public void stepsTest(){
        openGitHub();
        searchRepo();
        openRepo();
        openIssue();
        checkIssueWithNumber();
    }

    @AfterTest
    public void saveSources(){
        attachPageSource();
    }


}
