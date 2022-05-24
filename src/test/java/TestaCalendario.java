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

    /**
     * teste V(1), V(6)
     * @throws IOException
     */
    @Test
    public void testarImpressaoAnoRecebidoPorParametro() throws IOException {
        Path path =  Paths.get("src\\test\\resources", "2022.txt");

        String ano2022 = Files.readString(path);
        Calendario.mostrarCalendario("2022");
        String obtido = saidaConsole.toString();

        assertEquals("Conteúdo do arquivo não confere com o conteúdo do console",
                ano2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    /**
     * teste I(2)
     * @throws IOException
     */
    @Test
    public void testarImpressaoQuantidadeDeParametrosMaiorQueDois() throws IOException {
        Path path =  Paths.get("src\\test\\resources", "janeiro2022.txt");

        String janeiro2022 = Files.readString(path);
        Calendario.mostrarCalendario("1","2022", "12");
        String obtido = saidaConsole.toString();

        assertEquals("Conteúdo do arquivo não confere com o conteúdo do console",
                janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }

    /**
     * teste I(3)
     * @throws IOException
     */
    @Test
    public void testarAnoNaoInteiro() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("@"));
        assertEquals("mostrarCalendario: @: ano inválido.", excecao.getMessage());
    }

    /**
     * teste I(4)
     * @throws IOException
     */
    @Test
    public void testarAnoMenorQueUm() throws IOException {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("-5"));
        assertEquals("mostrarCalendario: -5: ano inválido.", excecao.getMessage());
    }

    /**
     * I(7)
     */
    @Test
    public void testarAnoMaiorQueNoveMilNovecentosENoventaENove() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("10000"));
        assertEquals("mostrarCalendario: 10000: ano inválido.", excecao.getMessage());
    }

    /**
     * I(7)
     */
    @Test
    public void anoEMesNaoInteiro() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("asd", "##"));
        assertEquals("mostrarCalendario: asd: mês inválido.", excecao.getMessage());
    }

    /**
     * I(8)
     */
    @Test
    public void mesNaoInteiroAnoMenorQueUm() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("asd", "-4"));
        assertEquals("mostrarCalendario: asd: mês inválido.", excecao.getMessage());
    }

    /**
     * I(9)
     */
    @Test
    public void mesNaoInteiroEAnoMaiorQueNoveMilNovecentosENoventaENove() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("asd", "10000"));
        assertEquals("mostrarCalendario: asd: mês inválido.", excecao.getMessage());
    }

    /**
     * I(10)
     */
    @Test
    public void mesNaoInteiroEAnoValido() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("asd", "2022"));
        assertEquals("mostrarCalendario: asd: mês inválido.", excecao.getMessage());
    }

    /**
     * I(11)
     */
    @Test
    public void mesMenorQueUmEAnoNaoInteiro() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("-1", "asd"));
        assertEquals("mostrarCalendario: -1: mês inválido.", excecao.getMessage());
    }

    /**
     * I(12)
     */
    @Test
    public void mesMenorQueUmEAnoMenorQueUm() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("-1", "-1"));
        assertEquals("mostrarCalendario: -1: mês inválido.", excecao.getMessage());
    }

    /**
     * I(13)
     */
    @Test
    public void mesMenorQueUmEAnoMaiorQueNoveMilNovecentosENoventaENove() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("-1", "10000"));
        assertEquals("mostrarCalendario: -1: mês inválido.", excecao.getMessage());
    }

    /**
     * I(14)
     */
    @Test
    public void mesMenorQueUmEAnoValido() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("-1", "2022"));
        assertEquals("mostrarCalendario: -1: mês inválido.", excecao.getMessage());
    }

    /**
     * I(15)
     */
    @Test
    public void mesMaiorQueDozeEAnoNaoInteiro() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("13", "asd"));
        assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
    }

    /**
     * I(16)
     */
    @Test
    public void mesMaiorQueDozeEAnoMenorQueUm() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("13", "-1"));
        assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
    }

    /**
     * I(17)
     */
    @Test
    public void mesMaiorQueDozeEAnoMaiorQueNoveMilNovecentosENoventaENove() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("13", "10000"));
        assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
    }

    /**
     * I(18)
     */
    @Test
    public void mesMaiorQueDozeEAnoValido() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("13", "202"));
        assertEquals("mostrarCalendario: 13: mês inválido.", excecao.getMessage());
    }

    /**
     * I(19)
     */
    @Test
    public void mesValidoEAnoNaoInteiro() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("05", "asd"));
        assertEquals("mostrarCalendario: asd: ano inválido.", excecao.getMessage());
    }

    /**
     * I(20)
     */
    @Test
    public void mesValidoEAnoMenorQueUm() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("05", "-1"));
        assertEquals("mostrarCalendario: -1: ano inválido.", excecao.getMessage());
    }

    /**
     * I(21)
     */
    @Test
    public void mesValidoEAnoMaiorQueNoveMilNovecentosENoventaENove() {
        Throwable excecao = assertThrows(NumberFormatException.class,
                () -> Calendario.mostrarCalendario("05", "10000"));
        assertEquals("mostrarCalendario: 10000: ano inválido.", excecao.getMessage());
    }

    /**
     * teste V(22)
     * @throws IOException
     */
    @Test
    public void mesEAnoValidos() throws IOException {
        Path path =  Paths.get("src\\test\\resources", "janeiro2022.txt");

        String janeiro2022 = Files.readString(path);
        Calendario.mostrarCalendario("1","2022");
        String obtido = saidaConsole.toString();

        assertEquals("Conteúdo do arquivo não confere com o conteúdo do console",
                janeiro2022.replaceAll("\\s+", ""), obtido.replaceAll("\\s+", ""));
    }
}
