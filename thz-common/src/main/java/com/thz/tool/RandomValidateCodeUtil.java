package com.thz.tool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author haien
 * @Description 获取图片型随机验证码
 * @Date 2019/2/9
 **/
public class RandomValidateCodeUtil {
    //随机验证码原材料
    private static String randString="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //图片宽
    private static int width=95;
    //高
    private static int height=40;
    //干扰线数量
    private static int interLine=6;
    //随机数生成器（整个类共用，避免数据相同）
    private static Random random = new Random();

    /**
     * @Author haien
     * @Description 绘制图片验证码，返回BufferedImage对象
     * @Date 2019/2/9
     * @Param [backColor]
     * @return java.awt.image.BufferedImage
     **/
    public static BufferedImage getImageFromCode(String randCode){
        //创建不透明BufferedImage对象
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //获取画布
        Graphics g=image.getGraphics();
        Random random=new Random();
        //画着色矩形
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        //绘制干扰线
        int x= random.nextInt(4),y=0;
        int x1=width-random.nextInt(4),y1=0;
        for(int i=0;i<interLine;i++){
            g.setColor(getRandomColor(100,200)); //比较浅色
            y=random.nextInt(height- random.nextInt(4));
            y1= random.nextInt(height-random.nextInt(4));
            g.drawLine(x,y,x1,y1);
        }
        //写上验证码
        int fsize=(int)(height*0.8); //字体大小为图片高度的80%
        int fx=10,fy=fsize;
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,fsize));
        for(int i=0;i<randCode.length();i++){ //一个一个字符写
            fy=(int)(Math.random()*0.8+0.8*height); //字符高低随机
            g.setColor(getRandomColor(40,90)); //比较深色
            g.drawString(randCode.charAt(i)+" ",fx,fy);
            fx+=(width/randCode.length())*(Math.random()*0.3+0.8); //依据宽度浮动
        }
        //扭曲图片
        shearX(g,width,height);
        shearY(g,width,height);
        //添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);//噪点数量
        for (int i = 0; i < area; i++) {
            int xxx = random.nextInt(width);
            int yyy = random.nextInt(height);
            int rgb = random.nextInt(interLine);
            image.setRGB(xxx, yyy, rgb);
        }
        //封笔
        g.dispose();
        return image;
    }

    /**
     * @Author haien
     * @Description 获取四位随机验证码
     * @Date 2019/2/9
     * @Param [random]
     * @return java.lang.String
     **/
    public static String getRandomCode(){
        Random random=new Random();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<4;i++)
            stringBuilder.append(String.valueOf(randString.charAt(
                                    random.nextInt(randString.length()))));
        return stringBuilder.toString();
    }

    /**
     * @Author haien
     * @Description 根据自费颜色和背景颜色计算随机颜色
     * @Date 2019/2/9
     * @Param [fc, bc]
     * @return java.awt.Color
     **/
    private static Color getRandomColor(int fc,int bc){
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * @Author haien
     * @Description 扭曲图片
     * @Date 2019/2/9
     * @Param [g, w, h, color]
     * @return void
     **/
    private static void shearX(Graphics g,int w,int h){ //水平平移
        Random random=new Random();
        int period=5;
        boolean borderGap=true;
        int frames=1;
        int phase=random.nextInt(2);

        //一小块一小块平移实现扭曲
        for(int i=0;i<h;i++){
            double dx=(double) (period >> 1)* Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase)/ (double) frames);
            g.copyArea(0,i,w,1,(int)dx,0);
            if(borderGap){
                g.setColor(getRandomColor(90,156));
                g.drawLine((int)dx,i,0,i);
                g.drawLine((int)dx+w,i,w,i);
            }
        }
    }
    private static void shearY(Graphics g, int w1, int h1) {
        int period = 10;
        boolean borderGap = true;
        int frames = 20;
        int phase = random.nextInt(2);

        for (int i = 0; i < w1; i++) {
            double dy = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) dy);
            if (borderGap) {
                g.setColor(getRandomColor(80,99));
                g.drawLine(i, (int) dy, i, 0);
                g.drawLine(i, (int) dy + h1, i, h1);
            }
        }
    }
}




























