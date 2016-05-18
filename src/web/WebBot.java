package web;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitoflife.chatterbean.AliceBot;
import demo.AliceBotMother;

/**
 * Servlet implementation class WebBot
 */
public class WebBot extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static AliceBot bot = null;
	
		
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
    /**
     * Default constructor. 
     */
    public WebBot() {
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		System.out.println("哈哈哈");
		
		if(null == bot){
			System.out.println("WebBot initializing..");
			String realPath = request.getRealPath("/aiml");
			System.out.println("realPath is : " + realPath);
			AliceBotMother mother = new AliceBotMother();		  
		    mother.setUp(realPath);
		    try {
				bot = mother.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Alice Bot is loaded..");
		}
		
		
		String userInput = "";
		if(null != request.getParameter("userInput")){
			userInput = (String)request.getParameter("userInput");
			userInput = URLDecoder.decode(userInput, "UTF-8");
			/*byte[] b = userInput.getBytes("ISO-8859-1");
			userInput = new String(b, "utf-8");*/
			//userInput = URLDecoder.decode(userInput);
		}
		
		System.out.println("user input is : " + userInput);
		
		String botResponse = bot.respond(userInput);
		botResponse = botResponse.trim();
		/*byte[] b = botResponse.getBytes("ISO-8859-1");
		botResponse = new String(b, "utf-8");*/
		
		System.out.println("Bot response is : " + botResponse);
		
		response.getWriter().write(botResponse);
	}
	
}
