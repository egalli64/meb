package meb.s04;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class HelloBean {
    public String greetings(String name) {
        return "Hello, " + name;
    }
}
