package ds-group-2-suzume;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

/**
 *
 * @author HP
 */
public class mapObj {
    private String s1 = "./maps/";
    private String s2 = ".png";
    
    private String path;
    private String location;
    private int[][] pixels ;
    

    public mapObj(String location) {
        this.location=location;
        path = s1+location+s2;
        getimage();
        modifypixels();
    }

    @Override
    public String toString() {
        String ret = "Map for " + location;
        return ret;
    }

    public void getimage(){
        BufferedImage img = null;
        Raster raster = null;
        int height=0,width=0;
        try{
            File file = new File(path);
            img = ImageIO.read(file);
            raster=img.getData();
            height = img.getHeight();
            width = img.getWidth();
        }catch(IOException e){
            System.out.println(e);
        }
        int[][] imgArr = new int[width][height]; 
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                imgArr[i][j] = raster.getSample(i, j, 0);
            }
        }
        
        pixels = imgArr;
    }
    
    public  void printimage(){
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%4s",pixels[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void modifypixels(){
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 10; i++) {
                if (pixels[i][j]==0)continue;
                else if(pixels[i][j]==13)pixels[i][j]=1;
                else if(pixels[i][j]==55)pixels[i][j]=2;
                else if(pixels[i][j]==134)pixels[i][j]=3;
            }
        }
    }
    
    public void colorimage(){
        int height = 20,width =10;
        
        BufferedImage cimage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2g = cimage.createGraphics();
    }
    
    public int[][] getarray(){
        return pixels;
    }
    
    public void correction(){
        pixels[9][19]=1;
    }
}
