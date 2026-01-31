import java.nio.file.Path;
import java.time.LocalDateTime;

public class Cloning {
    public Path ruta;
    private gitRunner gR;

    public Cloning(Path ruta) {
        this.ruta = ruta;
        gR = new gitRunner();
    }

    public void runCloning(String urlRepo){
        if(urlRepo == ""){
            System.out.printf("Necesita forzosamente el HTTPS o SSH de un repositorio remoto");
            return;
        }

        try {
            gR.runCommand(ruta, "git", "clone", urlRepo);

            System.out.println("00: Mundo descargado con éxito :).");
        } catch (Exception e) {
            throw new RuntimeException("Z!: Error fatal. No se pudo clonar el repositorio del mundo. Inténtelo de nuevo. \n", e);
        }
    }
}
