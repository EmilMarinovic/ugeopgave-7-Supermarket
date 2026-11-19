
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        KasseApparat kasse = new KasseApparat();
        kasse.loadData();
        Robot robot = new Robot();
        Collection<Vare> robotKurv = robot.fyldIKurv(kasse.getAlleVarer());
        for (Vare vare : robotKurv) {
            kasse.scanVare(vare.getEAN());
        }
        kasse.udskrivKassebon();
    }
}
