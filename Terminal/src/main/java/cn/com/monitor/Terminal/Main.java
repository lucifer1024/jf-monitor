package cn.com.monitor.Terminal;


import org.common.utils.transport.CustomMsg;


/**
 * 模拟终端发送信息
 * 
 * @author star
 *
 */
public class Main {
	public static void main(String[] args) {
		start();
		// LoginMsg loginMsg=new LoginMsg();
		// loginMsg.setPassword("yao");
		// loginMsg.setUserName("robin");
		// bootstrap.socketChannel.writeAndFlush(loginMsg);
		// while (true){
		// TimeUnit.SECONDS.sleep(3);
		// AskMsg askMsg=new AskMsg();
		// AskParams askParams=new AskParams();
		// askParams.setAuth("authToken");
		// askMsg.setParams(askParams);
		// bootstrap.socketChannel.writeAndFlush(askMsg);
		// }
	}

	private static void start() {
		try {
			String ip = "127.0.0.1";
			int port = 9999;
			TimeClient client = new TimeClient();
			client.connect(ip, port);
//			client.channel.writeAndFlush("你好");
			for(int i=0;i<10;i++){
				client.channel.writeAndFlush(new CustomMsg("终端信息 "+i));
				Thread.sleep(1000);
			}
			
//			client.channel.writeAndFlush(Unpooled.copiedBuffer("hello server", CharsetUtil.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
