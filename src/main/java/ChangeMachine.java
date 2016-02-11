import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class ChangeMachine {
  public static void main(String[] args) {
    staticFileLocation("/public");

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/results.vtl");

      String input = request.queryParams("userinput");
      double inputDoubble = Double.parseDouble(input);
      String results = makeChange(inputDoubble);

      model.put("results", results);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

  public static String makeChange(double userInput) {
    double currentChange = userInput;
    Integer quarters = 0;
    Integer dimes = 0;
    Integer nickels = 0;
    Integer pennies = 0;
    String error = "Please input a new number between .01 and .99";

    if (userInput <= 0 || userInput >=1) {
      return error;
    }

    while (currentChange >= .25) {
      currentChange -= .25;
      quarters ++;
    }
    while (currentChange >= .10) {
      currentChange -= .10;
      dimes ++;
    }
    while (currentChange >= .05) {
      currentChange -= .05;
      nickels ++;
    }
    while (currentChange >= 0) {
      currentChange -= .01;
      pennies ++;
    }
  return String.format("Your change is %s quarters %s dimes %s nickels and %s pennies", quarters, dimes, nickels, pennies);
  }

}
