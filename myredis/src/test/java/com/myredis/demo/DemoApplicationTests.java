package com.myredis.demo;

import com.myredis.demo.bean.RoncooUserLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	// 192.168.3.47   12345
	//139.199.226.140 4123
	public static final String addr = "139.199.226.140";
	public static final int PORT = 4123;
	@Autowired
	//private RoncooUserLogMongoDao roncooUserLogMongoDao;

	@Test
	public void contextLoads() {
		System.out.println("客户端启动...");
		System.out.println("当接收到服务器端字符为 \"OK\" 的时候, 客户端将终止\n");

		while (true) {
			Socket socket = null;
			try{
				socket = new Socket(addr,PORT);
				DataInputStream input = new DataInputStream(socket.getInputStream());
				//像服务端发送数据
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF("zasdasdasd");
				System.out.println("接受服务器发送回来的数据");
				String ret = input.readUTF();
				System.out.println("服务器端返回过来的是: " + ret);
				// 如接收到 "OK" 则断开连接
				if ("OK".equals(ret)) {
					System.out.println("客户端将关闭连接");
					Thread.sleep(500);
					break;
				}
				out.close();
				input.close();

			}catch (Exception e){
				System.out.println("客服端异常");
			}finally {
				if(socket!=null){
					try {
						socket.close();
					} catch (IOException e) {
						socket = null;
						System.out.println("客服端异常");
						e.printStackTrace();
					}
				}
			}
		}

	}

}
