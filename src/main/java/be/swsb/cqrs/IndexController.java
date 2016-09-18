package be.swsb.cqrs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * @return index as the name of the (thymeleaf) template,
     * which Spring Boot’s autoconfigured view resolver will map to src/main/resources/templates/index.html
     */
    @RequestMapping("/")
    public String index() { return "index"; }
}
