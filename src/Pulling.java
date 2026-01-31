import java.nio.file.Path;
import java.time.LocalDateTime;

public class Pulling {
    public Path ruta;
    private gitRunner gR;

    public Pulling(Path ruta) {
        this.ruta = ruta;
        gR = new gitRunner();
    }

    public void runPulling(){
        if(!gR.existeRepositorio(ruta)){
            System.out.println("404!: No existe un repositorio en el mundo seleccionado. Cree uno o asegúrese de que sea el mundo correcto.");
            return;
        }

        try {
            gR.runCommand(ruta, "git", "fetch", "origin");
            gR.runCommand(ruta, "git", "reset", "--hard", "origin/main");
            gR.runCommand(ruta, "git", "clean", "-fd");
            gR.runCommand(ruta, "git", "pull");

            System.out.println("00: Cambios descargados con éxito :).");
        } catch (Exception e) {
            throw new RuntimeException("Z!: Error fatal. No se pudieron descargar los cambios al repositorio del mundo. Inténtelo de nuevo. \n", e);
        }
    }
}
