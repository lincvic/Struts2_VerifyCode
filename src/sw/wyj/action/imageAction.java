package sw.wyj.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * Created by 王一疆 on 2017/9/20
 */
public class imageAction extends ActionSupport {
    /**
     * 获取随机的颜色值，r,g,b的取值在Low到High之间
     * @paramL左区间
     * @paramR右区间
     * @return 返回随机颜色值
     */
    private static Color interLine(int Low, int High){
        if(Low > 255)
            Low = 255;
        if(High > 255)
            High = 255;
        if(Low < 0)
            Low = 0;
        if(High < 0)
            High = 0;
        int interval = High - Low;
        int r = Low + (int)(Math.random() * interval);
        int g = Low + (int)(Math.random() * interval);
        int b = Low + (int)(Math.random() * interval);
        return new Color(r, g, b);
    }
    private ByteArrayInputStream inputStream;


    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    @Override
    public String execute() throws Exception {
        BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);
        Graphics g = bfi.getGraphics();
        g.fillRect(0, 0, 80, 25);

        //验证码字符范围
        char[] ch = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random r = new Random();
        int index;//保存随机数字
        StringBuffer sb = new StringBuffer(); //保存字符串
        //验证码为四位数字
        for(int i=0; i<4; i++){
            index = r.nextInt(ch.length);//产生随机数
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            Font font = new Font("宋体", 30, 20);
            g.setFont(font);
            g.drawString(ch[index]+"", (i*20)+2, 23);
            sb.append(ch[index]);
        }

        // 添加噪点
        int area = (int) (0.02 * 80 * 25);
        for(int i=0; i<area; ++i){
            int x = (int)(Math.random() * 80);
            int y = (int)(Math.random() * 25);
            bfi.setRGB(x, y, (int)(Math.random() * 255));
        }
        //设置验证码中的干扰线
//        for (int i = 0; i < 6; i++) {
//            //随机获取干扰线的起点和终点
//            int xstart = (int)(Math.random() * 80);
//            int ystart = (int)(Math.random() * 25);
//            int xend = (int)(Math.random() * 80);
//            int yend = (int)(Math.random() * 25);
//            g.setColor(interLine(1, 255));
//            g.drawLine(xstart, ystart, xend, yend);
//        }

       ActionContext.getContext().getSession().put("checkCode", sb.toString());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bfi, "JPG", outputStream);  //写到输出流
        ByteArrayInputStream input = new ByteArrayInputStream(outputStream.toByteArray());
        this.setInputStream(input);
        return SUCCESS;
    }
}
