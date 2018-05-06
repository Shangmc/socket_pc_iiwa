package application;


import javax.inject.Inject;

import app.CallBack;
import app.TcpConnect;
import app.TcpConnect.READ_TYPE;

import com.kuka.common.ThreadUtil;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.task.ITaskLogger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application lifecycle. The application will terminate automatically after 
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the 
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an 
 * exception is thrown during initialization or run. 
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the 
 * {@link RoboticsAPITask#dispose()} method.</b> 
 * 
 * @see UseRoboticsAPIContext
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class Socket_test extends RoboticsAPIApplication {
	@Inject
	private LBR lBR7;
    private Socket socket;
//	private TcpConnect Server;
    public  String str_server="";
	public String str="come from robot\r\n";
	//private String Read;
	@Inject 
	ITaskLogger log;
	
	
	@Override
	public void initialize() {
		// initialize your application here
		
		/*CallBack listener = new CallBack(){
			@Override
			public void ReceiveContent(Socket sock,String text) {
				System.out.println("Receive text: " + text + ", len:" + text.length());
				Read = text;
			}
			
		};*/
		/*CallBack listenerB = new CallBack(){
			@Override
			public void ReceiveContent(Socket sock,String text) {
				System.out.println("Receive text: " + text + ", len:" + text.length());
				Read = text;
			}
		
		};*/
		
		/*Server = new TcpConnect("172.168.1.19",59152, listener);
		//Server= new TcpConnect(59152,listener);
		Server.setReadType(READ_TYPE.READ_LINE);
		Server.start();
         System.out.println("hello world");		
		//BeltServer = new TcpConnect(30003, listenerB);
		//BeltServer.setReadType(READ_TYPE.READ_LINE);
		//BeltServer.start();
		*/

		
		
		
	}

	@Override
	public void run() {
		//ThreadUtil.milliSleep(50);
		System.out.println("first");
		try {
			 socket = new Socket("172.31.1.19",30003);
			//bIsServer = true;
			System.out.println("sucess");
			
			OutputStream outputstream=socket.getOutputStream();
			Writer writer=new OutputStreamWriter(outputstream);
			InputStream inputstream=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(inputstream));
			ThreadUtil.milliSleep(1000);
			
			//
			
			//
			
			writer.write(str);
			writer.flush();
			//ThreadUtil.milliSleep(1000);
			str_server=br.readLine();
			getLogger().info("the content is "+str_server);
		//	socket.close();
			//socket.shutdownOutput();
			
			//ThreadUtil.milliSleep(1000);
		//	while((str_server=br.readLine())!=null)
			//{
				
			//}
				
			//System.out.println("the recv str is "+str_server);
			//socket.shutdownOutput();
			//socket.shutdownInput();
			socket.close();
			
		} catch(IOException e) {
			System.out.println("Can not connect to host : "+e);
		}finally
		{
			if(socket!=null)
				try{
					socket.close();
				}catch(IOException e)
				{
					e.printStackTrace();
				}
		}
		
	
		
		
		//ThreadUtil.milliSleep(1000);
      //  Read= null;
		
		/*log.info("Send T");

		Server.sendContent("T");

		while(true) {
			
			if(Read != null) {
			
				System.out.println("received " + Read);
				
				log.info("received T +" + Read);
				
				break;
			}
			
			ThreadUtil.milliSleep(1000);
			
			
		}*/
		
		//Server.stopConnect();
		
	}
}