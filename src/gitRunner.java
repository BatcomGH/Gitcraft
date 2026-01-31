import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class gitRunner {
    private ProcessBuilder pr;

    public gitRunner(){}

    public void runCommand(Path ruta, String... comando) throws IOException, InterruptedException {
        pr = new ProcessBuilder(comando);
        pr.directory(ruta.toFile());
        pr.redirectErrorStream(true);

        Process p = pr.start();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }

        int exitCode = p.waitFor();
        if(exitCode != 0){
            throw new RuntimeException("20!: Error de ejecucion con el comando "+ String.join(" ", comando) +". Salida: " + exitCode);
        }
    }

    public boolean existeRepositorio(Path ruta){
        Path gitPath = ruta.resolve(".git");
        return Files.exists(gitPath) && (Files.isDirectory(gitPath) || Files.isRegularFile(gitPath));
    }
}
