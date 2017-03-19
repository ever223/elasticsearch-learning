package com.xiaogan.elasticsearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
