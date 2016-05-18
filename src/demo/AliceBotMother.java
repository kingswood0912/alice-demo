package demo;

import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import bitoflife.chatterbean.AliceBot;
import bitoflife.chatterbean.Context;
import bitoflife.chatterbean.parser.AliceBotParser;
import bitoflife.chatterbean.util.Searcher;

public class AliceBotMother {

	private ByteArrayOutputStream gossip;
	
	private String aimlPath;

	public void setUp(String aimlPath) {
		this.aimlPath = aimlPath;
		gossip = new ByteArrayOutputStream();
	}

	public String gossip() {
		return gossip.toString();
	}

	public AliceBot newInstance() throws Exception {
		Searcher searcher = new Searcher();
		AliceBotParser parser = new AliceBotParser();
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("context.xml");
		System.out.println("in is : " + in);
		AliceBot bot = parser.parse(
				this.getClass().getClassLoader()
						.getResourceAsStream("context.xml"),
				this.getClass().getClassLoader()
						.getResourceAsStream("splitters.xml"),
				this.getClass().getClassLoader()
						.getResourceAsStream("substitutions.xml"),
				searcher.search(aimlPath, ".*\\.aiml"));

		Context context = bot.getContext();
		context.outputStream(gossip);
		return bot;
	}
}
