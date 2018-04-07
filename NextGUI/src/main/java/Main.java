
import java.util.Scanner;
import java.awt.Desktop;
import java.net.URI;

public class Main implements GuiDel {

    final static String authUrl = "https://home.nest.com/login/oauth2?client_id=85b1b461-5491-4ba2-ba7f-ae498c9e98ec&state=STATE";

    static NestSR nestMan;
    static NestInformation nestInformation;

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
        nestMan = new NestSR(code, TempSystem.AUTO);
        NestInformation ti = nestMan.getNestInfo();
        nestInformation = ti;
        Gui.displayNest(ti);
    }

    @Override
    public void increment() {
        nestInformation.target++;
        Gui.displayNest(nestInformation);
        nestMan.setTarget(nestInformation.code, nestInformation.target);
    }

    @Override
    public void decrement() {

    }

    public static void setTarget(String key, double temp) {
        nestMan.setTarget(key, temp);
    }

    public static NestInformation getNestInfo() {
        return nestMan.getNestInfo();
    }

    public static void main(String[] args){
        displayPage();
        Gui.startgui(new Main());
    }
}
