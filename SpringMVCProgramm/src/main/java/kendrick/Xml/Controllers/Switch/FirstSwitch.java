package kendrick.Xml.Controllers.Switch;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/greetings")//All url should start with /greetings to get the methods
public class FirstSwitch {
    @GetMapping("/hello")
    public String sayHello(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Name : " + name + " " + surname);
        return "first/hello_world";
    }

    @GetMapping("/goodbye")
    public String sayBye(@RequestParam("name") String name, @RequestParam("surname") String surname){
        System.out.println("Name : " + name + "  " + surname);

        return "first/goodbye";
    }

    @GetMapping("/Exit")
    public String Exit(@RequestParam(value = "name",required = false )String name,
                       @RequestParam(value = "surname", required = false) String surname){
        System.out.println("Name : " + name + " " + surname);
        return "first/Exit";
    }

    @GetMapping("/calculator")
    public String calculator(Model model,
                             @RequestParam("a")int a,
                             @RequestParam("b")int b,
                             @RequestParam("action")String action){
        switch (action) {
            case "multiplication":{
                int result = a * b;
                model.addAttribute("result",result);
                break;
            }
            case "subtraction":{
                int result = a - b;
                model.addAttribute("result",result);
                break;
            }
            case "division":{
                if(b == 0){
                    model.addAttribute("result","Parameter b is 0 which division is not acceptable!");
                    break;
                }
                int result = a / b;
                model.addAttribute("result",result);
                break;
            }
            case "addition":{
                int result = a + b;
                model.addAttribute("result",result);
                break;
            }
            default:{
                String result = "Wrong parameter was sent!";
                model.addAttribute("result",result);
            }
        }
        return "first/calculator";
    }
}
