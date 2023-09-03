package ch.swaechter.reposly.web;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Controller
public class IndexController {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/", produces = MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Welcome from Reposly!";
    }
}
