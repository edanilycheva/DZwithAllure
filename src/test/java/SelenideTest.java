import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    public void issueSearchTest(){
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true).savePageSource(true));

        open("https://github.com");
        $(".header-search-input").setValue("eroshenkoam/allure-example").pressEnter();
        $$(".repo-list-item").findBy(text("eroshenkoam/allure-example")).$("a").click();
        $("#issues-tab").click();
        $$(".js-navigation-container").findBy(text("#68")).should(Condition.visible);
    }

}
