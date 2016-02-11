import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class ChangeMachine {
  public static void main(String[] args) {}

  public static float testQuarter(userInput) {
    Integer quarters = 0;
    while (userInput % .25 ) {
      quarters++;
    }
    return quarters;
  }

}
