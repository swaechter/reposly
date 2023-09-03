package ch.swaechter.reposly.web;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Reposly",
        description = "A repository web service.",
        version = Globals.REPOSLY_VERSION,
        contact = @Contact(name = "Simon Wächter", email = "waechter.simon@gmail.com")
    )
)
public class Application {

    public static void main(String[] arguments) {
        Micronaut.build(arguments).mainClass(Application.class).banner(false).start();
    }
}
