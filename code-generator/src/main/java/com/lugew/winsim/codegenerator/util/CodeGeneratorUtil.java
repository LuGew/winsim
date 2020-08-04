package com.lugew.winsim.codegenerator.util;


import com.lugew.winsim.codegenerator.core.BasicCodeGenerator;
import com.lugew.winsim.codegenerator.core.CodeGenerator;
import com.lugew.winsim.codegenerator.core.TemplateProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuGew
 * @since 2020/6/1
 */
public class CodeGeneratorUtil {
    public static void main(String[] args) {
        CodeGenerator codeGenerator =
                new BasicCodeGenerator("ProjectUser",
                        System.getProperty("user.dir") + "/management/src/main/java/com/newsense/reviewonline/management/"
                );

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("packageName", "com.newsense.reviewonline.management");
        parameters.put("className", "ProjectUser");
        parameters.put("author", "LuGew");
        parameters.put("datetime", LocalDateTime.now());

        List<TemplateProperty> templateProperties = new ArrayList<TemplateProperty>() {{
            add(new TemplateProperty("mapper", parameters));
            add(new TemplateProperty("service", parameters));
            add(new TemplateProperty("controller", parameters));
        }};
        codeGenerator.generate(templateProperties);
    }
}
