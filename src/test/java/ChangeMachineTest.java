import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeMachineTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Change Maker");
  }
  @Test
  public void rockPaperScissors() {

    goTo("http://localhost:4567/");
    fill("userinput").with(.85);
    submit(".btn");
    assertThat(pageSource()).contains("dimes");
  }

  @Test
  public void checkQuarter_inputGivesQuarters_3quaters() {
    ChangeMachine testChangeMachine = new ChangeMachine();
    assertEquals("Your change is 3 quarters 0 dimes 0 nickels and 0 pennies", testChangeMachine.makeChange(.75));
  }

  @Test
  public void checkNumberOverOne_inputGivesError_error() {
    ChangeMachine testChangeMachine = new ChangeMachine();
    assertEquals("Please input a new number between .01 and .99", testChangeMachine.makeChange(1));
  }
}
