import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import paranavai.calendario.Calendario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThrows;

public class TestaCalendario {
    private final ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    @Before
    public void init(){
        System.setOut(new PrintStream(saidaConsole));
    }

    @After
    public void end(){
        System.setOut(new PrintStream(saidaOriginal));
    }

    @Test
    public void testarImpressaoAnoRecebidoPorParametro() throws IOException {
        Path path =  Paths.get("src\\test\\resources", "2022.txt");

        String ano2022 = Files.readString(path);
        Calendario.mostrarCalendario("2022");
        String obtido = saidaConsole.toString();

        assertEquals("Conteúdo do arquivo não confere com o conteúdo do console",
                ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    @Test
    public void testarImpressaoQuantidadeDeParametrosmaiorQueDois() throws IOException {
        Path path =  Paths.get("src\\test\\resources", "janeiro2022.txt");

        String janeiro2022 = Files.readString(path);
        Calendario.mostrarCalendario("1","2022");
        String obtido = saidaConsole.toString();

        assertEquals("Conteúdo do arquivo não confere com o conteúdo do console",
                janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    @Test
    public void testarAnoNaoInteiro() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("@"));
        assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
    }

}
