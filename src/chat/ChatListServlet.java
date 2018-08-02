package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		
		if(fromID == null || fromID.equals("") || toID == null || toID.equals("") || listType == null || listType.equals("")) {
			response.getWriter().write("");
		}
		/*else if(listType.equals("ten")) {
			response.getWriter().write(getTen(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8")));
		}*/
		else {
			try {
				response.getWriter().write(getID(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8"), listType)); /*listType은 chatID > ? 에서 ?의 역할. 변수명 잘못 지음 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*public String getTen(String fromID, String toID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\" : [");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100); //최근 100개까지 읽어옴
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\" : \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\" : \"" + chatList.get(chatList.size() -1).getChatID() + "\"}");
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}*/
	
	public String getID(String fromID, String toID, String chatID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\" : [");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		if(chatList.size() == 0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\" : \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\" : \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() - 1) result.append(",");
		}
		result.append("], \"last\" : \"" + chatList.get(chatList.size() -1).getChatID() + "\"}");
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}

	

}
