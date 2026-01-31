import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path ruta = routing();

        if(!Files.exists(ruta)){
            System.out.println("23!: No se encontró la carpeta de mundos guardados");
            return;
        }

        int opc = menu();
        if(opc != 1){
            ruta = elegirArchivo(ruta);
            System.out.printf("Ruta seleccionada: %s\n", ruta.toString());
        }

        String url;
        switch(opc){
            case 1:
                Cloning clonarRepo = new Cloning(ruta);
                System.out.println("Ingrese el HTTPS del repositorio remoto: ");
                url = entradaConsola();
                clonarRepo.runCloning(url);
                break;
            case 2:
                Pushing pushRepo = new Pushing(ruta);
                pushRepo.runPushing();
                break;
            case 3:
                Pulling pullRepo = new Pulling(ruta);
                System.out.println("ADVERTENCIA (!): Esta acción eliminará todo cambio hecho en su mundo que no se haya subido. Para continuar presione S, para cancelar presione n.");
                String cont = entradaConsola();
                if(cont.equals("S")){
                    pullRepo.runPulling();
                }
                break;
            case 4:
                Remote crearRemoto = new Remote(ruta);
                System.out.println("Ingrese el HTTPS del repositorio remoto: ");
                url = entradaConsola();
                crearRemoto.runRemote(url);
                break;
        }
    }

    public static Path routing(){
        //Obtener la raiz de la carpeta de saves en minecraft
        String os = System.getProperty("os.name").toLowerCase();
        Path saves = null;

        if(os.contains("win")){
            saves = Path.of(System.getProperty("user.home"), "AppData", "Roaming", ".minecraft", "saves");
        }else if(os.contains("mac")){
            //A1
        }else{
            saves = Path.of(System.getProperty("user.home"), ".minecraft", "saves");
        }

        return saves;
    }

    public static int menu() throws IOException {
        System.out.println("Elija la accion que desea ejecutar:");
        System.out.println(" \t1. Clonar mundo.");
        System.out.println(" \t2. Subir cambios.");
        System.out.println(" \t3. Descargar cambios.");
        System.out.println(" \t4. Crear repositorio de mundo.");

        int opc = Integer.parseInt(entradaConsola());

        return opc;
    }

    public static Path elegirArchivo(Path ruta) throws IOException {
        System.out.println("Elija el número de la carpeta del mundo:");
        List<Path> carpetas = Files.list(ruta).filter(Files::isDirectory).toList();

        for (int i = 0; i < carpetas.size(); i++) {
            System.out.println((i + 1) + ". " + carpetas.get(i).toString());
        }

        int carpetaID = Integer.parseInt(entradaConsola()) - 1;
        return carpetas.get(carpetaID);
    }

    public static String entradaConsola() throws IOException {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

            String entrada = buffer.readLine();
            return entrada;
        }catch(Exception e){
            System.out.println("17!: Error al leer la entrada de la consola.\n"+e.getMessage());
        }

        return null;
    }
}