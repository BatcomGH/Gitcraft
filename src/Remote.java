import java.nio.file.*;
import java.nio.file.Path;
import java.time.LocalDateTime;


public class Remote {
    public Path ruta;
    private gitRunner gR;

    public Remote(Path ruta) {
        this.ruta = ruta;
        gR = new gitRunner();
    }

    public void runRemote(String urlRepo){
        if(urlRepo == ""){
            System.out.printf("Necesita forzosamente el HTTPS o SSH de un repositorio remoto");
            return;
        }
        if(gR.existeRepositorio(ruta)){
            System.out.printf("Esta carpeta ya tiene un repositorio remoto");
        }

        try {
            gR.runCommand(ruta, "git", "init");
            gR.runCommand(ruta, "git", "add", ".");
            gR.runCommand(ruta, "git", "commit", "-m", String.join(" ", "Creado el", LocalDateTime.now().toString()));
            gR.runCommand(ruta, "git", "branch", "-M", "main");
            gR.runCommand(ruta, "git", "remote", "add", "origin", urlRepo);
            gR.runCommand(ruta, "git", "push", "-u", "origin", "main");

            System.out.println("00: Mundo subido con éxito :).");
        } catch (Exception e) {
            throw new RuntimeException("Z!: Error fatal. Elimine el archivo o carpeta .git manualmente de la carpeta de su mundo de minecraft. \n", e);
        }
    }

}
