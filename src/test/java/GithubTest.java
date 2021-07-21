import org.junit.jupiter.api.Test;
import steps.GithubSteps;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static org.openqa.selenium.By.linkText;

public class GithubTest {

    String REPOSITORY = "eroshenkoam/allure-example";
    String TAB = "Issues";
    Integer ISSUE_NUMBER = 68;

    @Test
    void testRepositoryIssue() {
        //Arrange
        open("https://github.com");

        //Act
        $(".header-search-input").setValue("eroshenkoam/allure-example").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $(".js-repo-nav").$(byText("Issues")).click();

        //Assert
        $(withText("#68")).should(exist);
    }

    @Test
    void testRepositoryIssueWithLambdaSteps() {
        //Arrange
        step("Открываем главную страницу Github", () ->
                open("https://github.com"));

        //Act
        step(format("Ищем репозиторий %s", REPOSITORY), () ->
                $(".header-search-input").setValue(REPOSITORY).submit());
        step("Открываем репозиторий", () ->
                $(linkText("eroshenkoam/allure-example")).click());
        step(format("Переходим на вкладку %s", TAB), () ->
                $(".js-repo-nav").$(byText("Issues")).click());

        //Assert
        step(format("Проверяем существование issue c номером %s", ISSUE_NUMBER), () ->
                $(withText(format("%s", ISSUE_NUMBER))).should(exist));
    }

    GithubSteps githubSteps = new GithubSteps();

    @Test
    void testRepositoryIssueWithAnnotationSteps() {
        //Arrange
        githubSteps.openMainPage();

        //Act
        githubSteps.searchForRepository(REPOSITORY);
        githubSteps.goToRepository(REPOSITORY);
        githubSteps.openTab(TAB);

        //Assert
        githubSteps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
