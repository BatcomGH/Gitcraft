import java.nio.file.Path;
import java.time.LocalDateTime;

public class Pushing {
    public Path ruta;
    private gitRunner gR;

    public Pushing(Path ruta) {
        this.ruta = ruta;
        gR = new gitRunner();
    }

    public void runPushing(){
        if(!gR.existeRepositorio(ruta)){
            System.out.println("404!: No existe un repositorio en el mundo seleccionado. Cree uno o asegúrese de que sea el mundo correcto.");
            return;
        }

        try {
            gR.runCommand(ruta, "git", "add", ".");
            gR.runCommand(ruta, "git", "commit", "-m", String.join(" ", "Cambio subido el", LocalDateTime.now().toString()));
            gR.runCommand(ruta, "git", "push", "-u", "origin", "main");

            System.out.println("00: Cambios subidos con éxito :).");
        } catch (Exception e) {
            throw new RuntimeException("Z!: Error fatal. No se pudieron subir los cambios al repositorio del mundo. Inténtelo de nuevo. \n", e);
        }
    }
}
