package org.proxy.core;

import org.common.utils.transport.CustomMsg;
import org.proxy.in.ProxyInServer;
import org.proxy.out.ProxyOutClient;
import org.proxy.utils.cache.CacheUtils;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	Runnable in = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				startProxyIn();
			}
    		
    	};
    	
    	Runnable out = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				startProxyOut();
			}
    		
    	};
    	 new Thread(in).start();
    	 new Thread(out).start();
    	
    	
    }
    private static void startProxyIn() {
		// 启动 netty server task 用于接收终端数据
		int port = 9999;
		try {
			new ProxyInServer().bind(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("server main end");
	}
    private static void startProxyOut() {
		try {
			String ip = "127.0.0.1";
			int port = 8888;
			ProxyOutClient client = new ProxyOutClient();
			client.connect(ip, port);
			while(true){
				//读缓存数据
				String msg = CacheUtils.poll();
				client.channel.writeAndFlush(new CustomMsg(msg));
				//Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
