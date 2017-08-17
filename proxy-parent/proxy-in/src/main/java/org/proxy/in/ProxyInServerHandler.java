package org.proxy.in;

import org.common.utils.transport.CustomMsg;
import org.proxy.utils.cache.CacheUtils;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProxyInServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exceptionCaught ");
		cause.printStackTrace();
		ctx.close();
	}

	/*@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead : " + msg);
		
		 * ByteBuf buf = (ByteBuf) msg; byte[] req = new
		 * byte[buf.readableBytes()]; buf.readBytes(req); String body = new
		 * String(req, "UTF-8"); System.out.println("the time server receive:" +
		 * body); // 接收到数据 String resp = " 返回数据：" + System.currentTimeMillis();
		 * ByteBuf respBuf = Unpooled.copiedBuffer(resp.getBytes("UTF-8"));
		 * ctx.write(respBuf);
		 
	}*/

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// System.out.println("channelReadComplete ");
		ctx.flush();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof CustomMsg) {
			CustomMsg customMsg = (CustomMsg) msg;
			//TODO 把数据放在队列中 
			System.out.println("ProxyInServer:" + ctx.channel().remoteAddress() + " send " + customMsg.getBody());
			CacheUtils.push(customMsg.getBody());
		} else {
			ByteBuf buf = (ByteBuf) msg;
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String body = new String(req, "UTF-8");
			System.out.println("the time server receive:" + body);
		}

	}

}
