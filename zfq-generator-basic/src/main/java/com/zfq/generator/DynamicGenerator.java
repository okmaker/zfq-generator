package com.zfq.generator;

import com.zfq.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 张发强
 * @version 1.0
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        // 当前idea打开的窗口
        String projectPath = System.getProperty("user.dir") + File.separator + "zfq-generator-basic";
        String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate2.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        // 这次使用循环
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setAuthor("牛啤");
        mainTemplateConfig.setOutputText("求和结果：");
        deGenerate(inputPath, outputPath, mainTemplateConfig);
    }
    public static void deGenerate(String inputPath,String outputPath,Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 根据模板文件获取他的父目录
        File parentFile = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(parentFile);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);


        // 生成，当前指定的是在dexcode-generator-basic目录下
        Writer out = new FileWriter(outputPath);
        template.process(model,out);

        // 生成后关闭文件
        out.close();
    }
}
