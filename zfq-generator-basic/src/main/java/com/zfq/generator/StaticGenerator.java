package com.zfq.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author 张发强
 * @version 1.0
 */
public class StaticGenerator {
    public static void main(String[] args) {
        /*
            需要注意的是，我们在idea中如果是在大项目下，那么获得的路径就是大项目的路径
            比如： zfq-generator
            如果是小项目打开的，那么是
             zfq-generator-generator
         */
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        File projectFile = new File(projectPath);
        //这里有隐患，分隔符不同系统不一致
        String inputPath = new File(projectFile, "zfq-generotor-demo-projects" + File.separator + "acm-template").getAbsolutePath();
        String outputPath = projectPath;
        System.out.println(inputPath);
        copyFilesByRecursive(inputPath,outputPath);

    }

    /**
     * 实现拷贝文件（利用的hutool实现）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }
    public static void copyFilesByRecursive(String inputPath,String outputPath){
        //创建文件对象
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFilesByRecursive(inputFile,outputFile);
        } catch (IOException e) {
            System.out.println("文件复制失败！！！");
            e.printStackTrace();
        }
    }

    private static void copyFilesByRecursive(File inputFile, File outputFile) throws IOException {
        //如果是文件夹
        if (inputFile.isDirectory()){
            //建立输出的文件对象，同时创造这么一个文件夹
            File destOutputFile = new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            //获取所有文件项，包括目录
            File[] files = inputFile.listFiles();
            if (ArrayUtil.isEmpty(files)){
                return ;
            }
            for (File file : files) {
                copyFilesByRecursive(file,destOutputFile);
            }

        }else{
            //如果是文件
            //那么开始拼接路径
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(),destPath,StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
