import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest {
    @Test
    public void testLoadImage(){
        try {
            BufferedImage tankImg= ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
