import java.io.IOException;
import java.util.Properties;

public class configTest {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(configTest.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
       String initTankCounts=(String) props.get("initTankCounts");
        System.out.println(11111);
        System.out.println(initTankCounts);
    }
}
