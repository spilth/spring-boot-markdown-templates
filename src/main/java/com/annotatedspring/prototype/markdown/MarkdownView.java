package com.annotatedspring.prototype.markdown;

import org.pegdown.PegDownProcessor;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MarkdownView extends AbstractTemplateView {
    @Override
    protected void renderMergedTemplateModel(
            Map<String, Object> model,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        PrintWriter writer = response.getWriter();
        writer.append("<html><body>");
        writer.append(getHtmlFromMarkdown());
        writer.append("</body></html>");
    }

    private String getHtmlFromMarkdown() throws URISyntaxException, IOException {
        String templatePath = "templates/" + getUrl();
        URL templateUrl = MarkdownView.class.getClassLoader().getResource(templatePath);
        Path path = Paths.get(templateUrl.toURI());

        String markdown = new String(Files.readAllBytes(path));

        return new PegDownProcessor().markdownToHtml(markdown);
    }
}
