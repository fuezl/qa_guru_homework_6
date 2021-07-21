package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.openqa.selenium.By.linkText;

public class GithubSteps {

    @Step("Открываем главную страницу Github")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").setValue(repository).submit();
    }

    @Step("Открываем репозиторий")
    public void goToRepository(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Переходим на вкладку {issues}")
    public void openTab(String issues) {
        $(".js-repo-nav").$(byText("Issues")).click();
    }

    @Step("Проверяем существование issue c номером {number}")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText(format("%s", number))).should(exist);
    }
}
