package org.proxy.out.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	/**
	 * 此方法会在连接到服务器后被调用
	 */
	public void channelActive(ChannelHandlerContext ctx) {
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}

	/**
	 * 此方法会在接收到服务器数据后调用
	 */
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
		System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
		
	}
	  @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
	        ByteBuf buf = (ByteBuf) msg;
	        byte[] req = new byte[buf.readableBytes()];
	        buf.readBytes(req);
	        try {
	            String body = new String(req, "UTF-8");
	            System.out.println("client channel read msg:{}"+body);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }


	/**
	 * 捕捉到异常
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
