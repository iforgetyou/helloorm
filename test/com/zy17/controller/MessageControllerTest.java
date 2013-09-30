package com.zy17.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-30
 * Time: 上午10:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration("file:war/WEB-INF/mvc-dispatcher-servlet.xml")
@ContextConfiguration("file:war/WEB-INF/applicationContext.xml")
public class MessageControllerTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    MessageController controller;

    @Test
    public void testGetPersons() throws Exception {
        ResultActions result = standaloneSetup(controller).build()
                .perform(get("/message/person")
                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    public void testCreatePerson() throws Exception {

    }
}
