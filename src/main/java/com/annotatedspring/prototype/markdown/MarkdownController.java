package com.annotatedspring.prototype.markdown;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarkdownController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/{filename}")
    public String pages(@PathVariable String filename) {
        return filename;
    }
}
