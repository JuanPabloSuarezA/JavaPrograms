
package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArchivoNIO {

    private final Path archivo;
    private final String FINLINEA;

    public ArchivoNIO(String nombreArchivo) throws IOException {
        archivo = Paths.get(nombreArchivo);
        FINLINEA = System.getProperty("line.separator");

        if (!Files.exists(archivo)) {
            Files.createFile(archivo);
        }
    }

    public void agregarFila(String fila) throws IOException {
        List<String> listaLineas = new ArrayList<>();
        listaLineas.add(fila);
        Files.write(archivo, listaLineas, StandardOpenOption.APPEND);
    }
    
    public void reset() throws IOException{
        Files.delete(archivo);
        Files.createFile(archivo);
    }
    
    public void borrarFilaIDColegio(int idColegio) throws IOException{
        int i, limite;
        String contenido, lineasContenido[];
        List<String> listaLineas = new ArrayList<>();
        contenido =  new String (Files.readAllBytes(archivo));
        lineasContenido =  contenido.split(FINLINEA);
        limite = lineasContenido.length;
                
        for (i = 0; i < limite; i++) {
            if (!lineasContenido[i].startsWith(idColegio + "|")) {
                listaLineas.add(lineasContenido[i]);
            }
        }
        reset();
        Files.write(archivo, listaLineas, StandardOpenOption.APPEND);
    }
    
    public void reemplazarFilaIDColegio(int idColegio, String fila) throws IOException{
        int i, limite;
        String contenido, lineasContenido[];
        List<String> listaLineas = new ArrayList<>();
        contenido =  new String (Files.readAllBytes(archivo));
        lineasContenido =  contenido.split(FINLINEA);
        limite = lineasContenido.length;
                
        for (i = 0; i < limite; i++) {
            if (lineasContenido[i].startsWith(idColegio + "|")) {
                listaLineas.add(fila);
            }
            else{
                listaLineas.add(lineasContenido[i]);
            } 
        }
        reset();
        Files.write(archivo, listaLineas, StandardOpenOption.APPEND);
    }

    public List<String[]> obtenerContenidoArchivo() throws IOException {
        int i, limite;
        String contenido, lineasContenido[];
        List<String[]> retornado = new ArrayList<>();

        if (Files.size(archivo) > 0) {
            contenido = new String(Files.readAllBytes(archivo));
            lineasContenido = contenido.split(FINLINEA);
            limite = lineasContenido.length;
            for (i = 0; i < limite; i++) {
                retornado.add(lineasContenido[i].split("\\|"));
            }
        } 
        return retornado;
    }

}// Fin de clase
