package gogame.server.lobby.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Klasa odpowiadajaca za test klasy Parser
 * @see Parser
 * @author marcin
 *
 */
public class ParserTest {

	@Test
	public void test() {
		Connector connector = Mockito.mock(Connector.class);
		Parser parser = new Parser(connector);
		
		parser.interpret("SIZE 19");
		parser.interpret("TYPE MULTI");
		parser.interpret("FIND_GAME");
	}

}
