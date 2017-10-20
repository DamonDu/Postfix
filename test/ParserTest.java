import org.junit.Assert;

import java.io.ByteArrayInputStream;

public class ParserTest {

    @org.junit.Test
    public void rest() throws Exception {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("+".getBytes());
            System.setIn(in);
            Parser parser = new Parser();
            Assert.assertEquals(0, parser.rest());

            in = new ByteArrayInputStream("1".getBytes());
            System.setIn(in);
            parser = new Parser();
            Assert.assertEquals(1, parser.rest());
        }
        catch (Exception e) {
            return;
        }
    }

    @org.junit.Test
    public void term() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Assert.assertEquals(0, verifyTerm());

        in = new ByteArrayInputStream("+".getBytes());
        System.setIn(in);
        Assert.assertEquals(1, verifyTerm());
    }

    @org.junit.Test
    public void match() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        Assert.assertEquals(0, verifyMatch('1'));

        in = new ByteArrayInputStream("+".getBytes());
        System.setIn(in);
        Assert.assertEquals(1, verifyMatch('1'));
    }

    public int verifyTerm() throws Exception {
        Parser parser = new Parser();
        try {
            parser.term();
            return 0;
        }
        catch (Exception e) {
            return 1;
        }
    }

    public int verifyMatch(int t) throws Exception {
        Parser parser = new Parser();
        try {
            parser.match(t);
            return 0;
        }
        catch (Exception e) {
            return 1;
        }
    }
}