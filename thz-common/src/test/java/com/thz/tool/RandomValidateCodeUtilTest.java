package com.thz.tool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandomValidateCodeUtilTest {
    public static void main(String[] args) {
        String randomCode = RandomValidateCodeUtil.getRandomCode();
        System.out.println(randomCode);
        BufferedImage imageFromCode = RandomValidateCodeUtil.getImageFromCode(randomCode);
        try {
            Path path=Paths.get("d:/test01.jpg");
            if(Files.exists(path)) Files.delete(path);
            File file = path.toFile();
            ImageIO.write(imageFromCode,"jpg",file);
            System.out.println("成功保存到："+file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("保存失败");
            e.printStackTrace();
        }
    }
}