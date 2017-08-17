package org.proxy.out;



import org.common.utils.transport.CustomEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ProxyOutClient {
	public SocketChannel channel;

	public void connect(String ip, int port) {
		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new CustomEncoder());
//					ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
					ch.pipeline().addLast(new ProxyOutClientHandler());
				}
			});
			ChannelFuture f = b.connect(ip, port).sync();
			if (f.isSuccess()) {
				channel = (SocketChannel) f.channel();
			}
			// f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// group.shutdownGracefully();
		}

	}
}
