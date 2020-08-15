import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Main
{
    public static void main(String[] args) throws AWTException, IOException {

        System.out.println("Начало работы:");

        //вывод даты и времени с пк в консоль при начале работы
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        Date now = new Date();
        System.out.println(dateFormat.format(now));


        //for(int i = 0; i < 8; i++){
        ScreenCapture screen = new ScreenCapture();
        screen.start();
            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}