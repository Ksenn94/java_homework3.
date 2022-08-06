package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Pagecheck {
    //add default configuration for all tests
    @BeforeAll
    static void configure(){
        //Configuration.baseUrl = "https://github.com/";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void findpage(){
        //open the github website
        open("https://github.com/");
        //locate the search and search for 'selenide' value
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        // open the page selenide - find the list of found objects, identify the 1st, select a link
        $$(".repo-list").first().$("a").click();
        //check that the needed page is opened
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        //navigate to wiki section
        $("#wiki-tab").click();
        // check that the needed page is opened
        $(".markdown-body").shouldHave(text("Welcome to the selenide wiki!"));
        //check that softAssertions page is among the list of pages
        $(".markdown-body").shouldHave(text("Soft assertions"));
        //open Soft Assertions page
        //$(".markdown-body").$("li", 6).$("a").click();
        $(byLinkText("Soft assertions")).click();
        //check that there's an example for JUnit5
        $(".markdown-body").shouldHave(text("Using JUnit5"));

    }
}
