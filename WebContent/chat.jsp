<%@page import="bitoflife.chatterbean.AliceBot"%>
<%@page import="demo.AliceBotMother"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GBK"%>
<html>
<head>
<title>chat</title>

<script type="text/javascript">	function createHttpRequest() {
		var httpRequest = null;
		if (window.XMLHttpRequest) {
			httpRequest = new XMLHttpRequest();

			if (httpRequest.overrideMimeType) {
				httpRequest.overrideMimeType("text/xml");
			}
			return httpRequest;
		}
		if (window.ActiveXObject) {
			try {

				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				try {
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (ex) {
					alert("");
				}
			}
		}
		return httpRequest;
	}

	function CallBot() {

		var userInput = document.getElementById("userInput");
		var userInputStr = userInput.value;

		var http_request = createHttpRequest();
		if (http_request == null) {
			return;
		}
		http_request.open("Post",
				"http://localhost:8080/alice-demo/WebBot?userInput="
						+ encodeURI(encodeURI(userInputStr)), true);
		http_request.send();
		http_request.onreadystatechange = function() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
					//alert(http_request.responseText);
					updateChatHistory(userInputStr, http_request.responseText);
				}
			}
		}
		userInput.value = "";
		userInput.focus();
	}

	function updateChatHistory(userInput, botResponse) {
		var chatHistoryArea = document.getElementById("chatHistoryArea");
		var str = "你   说 >>> " + userInput + "\n" + "机器人说 >>> " + botResponse
				+ "\n";
		chatHistoryArea.value += str;
		
		chatHistoryArea.scrollTop = chatHistoryArea.scrollHeight;
	}
	
	
	
	
	
</script>
</head>
<body background="image/background.jpg">

<p><br>
</p>
<p></p>
<p></p>
<center>
<table width="60%" border="1">
	<tbody>
		<tr>
			<td colspan="10" height="300"><textarea id="chatHistoryArea"
				name="chatHistoryArea" rows="20" style="width: 100%"></textarea></td>
		</tr>
		<tr>
			<td colspan="9"><input type="text" id="userInput"
				name="userInput" style="width: 100%" onKeydown="Javascript: if (event.keyCode==13) CallBot();"></td>
			<td width="10%"><input type="button" id="btnTest" value="测试"
				onClick="CallBot()" style="width: 100%"></td>
		</tr>
	</tbody>
</table>
</center>
</body>
</html>