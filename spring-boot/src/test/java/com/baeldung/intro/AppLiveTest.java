package com.baeldung.intro;

<<<<<<< HEAD:spring-boot/src/test/java/com/baeldung/intro/AppTest.java
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

=======
>>>>>>> eugenp/master:spring-boot/src/test/java/com/baeldung/intro/AppLiveTest.java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
<<<<<<< HEAD:spring-boot/src/test/java/com/baeldung/intro/AppTest.java
=======
import org.springframework.test.context.TestPropertySource;
>>>>>>> eugenp/master:spring-boot/src/test/java/com/baeldung/intro/AppLiveTest.java
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

<<<<<<< HEAD:spring-boot/src/test/java/com/baeldung/intro/AppTest.java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppTest {
=======
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = { "security.basic.enabled=false" })
public class AppLiveTest {
>>>>>>> eugenp/master:spring-boot/src/test/java/com/baeldung/intro/AppLiveTest.java

    @Autowired
    private MockMvc mvc;

    @Test
    public void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Index Page")));
    }

    @Test
    public void getLocal() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/local").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("/local")));
    }

}