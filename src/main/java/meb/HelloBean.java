package meb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class HelloBean implements Hello {
    @Override
    public String greetings(String name) {
        return "Hello, " + name;
    }
}
