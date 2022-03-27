import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import java.math.BigDecimal;
import java.util.*;

@PrepareForTest(Parser.class)
class ParserTest {

    @Test
    void parseCorrectXMLNotNull() {
        String fileNameOfIncorrectFile = "xml-file/eurofxref-daily.xml";
        Map<String, BigDecimal> map = Parser.parse(fileNameOfIncorrectFile);
        Assertions.assertNotNull(map);
    }

    @Test
    void parseCorrectXMLDictLen() {
        String fileNameOfCorrectFile = "xml-file/eurofxref-daily.xml";
        Map<String, BigDecimal> map = Parser.parse(fileNameOfCorrectFile);
        assert map != null;
        Assertions.assertTrue(map.size() > 0);
    }

    @Test
    void parseInvalidXMLLackOfOneAttr() {

        String fileNameOfIncorrectFile = "xml-file/eurofxref-daily-test-lack-of-one-rate.xml";
        Map<String, BigDecimal> map = Parser.parse(fileNameOfIncorrectFile);
        Assertions.assertNull(map);
    }

    @Test
    void parseInvalidXMLLackOfAttributes() {
        String fileNameOfIncorrectFile = "xml-file/eurofxref-daily-test-lack-of-attributes.xml";
        Map<String, BigDecimal> map = Parser.parse(fileNameOfIncorrectFile);
        Assertions.assertNull(map);
    }
}