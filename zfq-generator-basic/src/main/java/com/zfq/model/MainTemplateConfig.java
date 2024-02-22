package com.zfq.model;

import lombok.Data;

/**
 * 模板配置类(带默认值)
 * @author 张发强
 * @version 1.0
 */
@Data
public class MainTemplateConfig {
    /**
     * 是否生成循环
     */
    private boolean loop = true;
    /**
     * 作者注释
     */
    private String author = "zfq";
    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}
