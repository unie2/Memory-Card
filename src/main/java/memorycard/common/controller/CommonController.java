package memorycard.common.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CommonController {
    Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/main_page.do")
    public ModelAndView main_page(Map<String, Object> commandMap) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        log.debug("인터셉터 테스트");

        return mv;
    }
}
