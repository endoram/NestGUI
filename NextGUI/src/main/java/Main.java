
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class Main implements GuiDel {

    final static String authUrl = "https://home.nest.com/login/oauth2?client_id=85b1b461-5491-4ba2-ba7f-ae498c9e98ec&state=STATE";

    private static void displayPage() {
        try {
            URI uri = new URI(authUrl);
            if (Desktop.isDesktopSupported()) {
                System.out.println("Desktop supported");
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Please visit " + authUrl + " in your web browser.");
            }
        } catch (Throwable err) {
            System.out.println("Error opening web page: " + err);
        }
    }

    private static String getCode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the code on the website?");
        return sc.next();
    }

    @Override
    public void codeEnter(String code) {
        new NestSR(code, TempSystem.AUTO);
    }

    @Override
    public void increment() {

    }

    @Override
    public void decrement() {

    }

    public static void main(String[] args){
        displayPage();
        Gui.startgui(new Main());
    }
}
