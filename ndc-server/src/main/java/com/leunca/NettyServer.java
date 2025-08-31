package com.leunca;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

public class NettyServer {
    private static final int PORT = 8081;
    
    public static void main(String[] args) throws Exception {
        // Configure thread pools
        EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
        EventLoopGroup workerGroup = new EpollEventLoopGroup();
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                   .channel(EpollServerSocketChannel.class)
                   .option(ChannelOption.SO_BACKLOG, 100000)
                   .childOption(ChannelOption.TCP_NODELAY, true)
                   .childOption(ChannelOption.SO_KEEPALIVE, true)
                   .childHandler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) {
                         ch.pipeline()
                           // Add these two handlers first
                           .addLast(new LineBasedFrameDecoder(8192))  // Handle line endings
                           .addLast(new StringDecoder(CharsetUtil.UTF_8))  // Convert bytes to String
                           .addLast(new StringEncoder(CharsetUtil.UTF_8))  // Convert String to bytes                           
                           .addLast(new LoggingHandler(LogLevel.INFO))  // Debug raw traffic
                           .addLast(new SimpleChannelInboundHandler<String>() {
                               @Override
                               protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                            	   String client = getClientAddressAndPort(ctx.channel()).toString();
                            	   String response = "Message received from " + client + " : '" + msg + "'\r\n";
                                   ctx.writeAndFlush(response);                                   
                               }
                               
                               @Override
                               public void channelActive(ChannelHandlerContext ctx) {      
                                   String client = getClientAddressAndPort(ctx.channel()).toString();                                   
                                   String statusMessage = client + " connected\r\n";                                   
                                   ctx.writeAndFlush(statusMessage);
                           
                               }

                               @Override
                               public void channelInactive(ChannelHandlerContext ctx) {      
                                   String client = getClientAddressAndPort(ctx.channel()).toString();                                   
                                   String statusMessage = client + " disconnected\r\n";                                   
                                   System.out.println(statusMessage);
                               }
                               
                               record AddressAndPort(String address, int port){
                            	    @Override
                            	    public String toString() {
                            	    	if (port >= 0)
                            	            return address + ":" + port;
                            	    	return address;
                            	    }
                               }

                               private AddressAndPort getClientAddressAndPort(Channel channel) {
                                   SocketAddress remoteAddr = channel.remoteAddress();
                                   if (remoteAddr instanceof InetSocketAddress) {
                                       InetSocketAddress inetAddr = (InetSocketAddress) remoteAddr;
                                       return new AddressAndPort(
                                           inetAddr.getAddress().getHostAddress(),
                                           inetAddr.getPort()
                                       );
                                   }
                                   return new AddressAndPort(remoteAddr.toString(), -1);
                               }                            
                               
                               @Override
                               public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                   System.err.println("Client " + getClientAddressAndPort(ctx.channel()).toString() + " error:");
                                   cause.printStackTrace();
                                   ctx.close();
                               }
                           });
                       }
                   });
            
            // Start server
            ChannelFuture future = bootstrap.bind(PORT).sync();
            System.out.println("Server started on port " + PORT);
            
            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down...");
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }));
            
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}