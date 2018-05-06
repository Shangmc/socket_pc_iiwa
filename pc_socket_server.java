package hellokitty;
import java.lang.Runtime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;
import java.lang.Thread.State;
import java.lang.InterruptedException;
import java.lang.Runnable;
import java.lang.management.ThreadInfo;
import hellokitty.car;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
//import java.util.concurrent.ExcecutorService;


public class hellomyworld {
	 private static String str="come from server\r\n";
	private static String server_info=null;
	static int i=0;
	public static void main(String[] args) throws InterruptedException
	{
		try {
			
			int port=30003;
			
			InetAddress bindAddress=InetAddress.getByName("172.31.1.19");
			
			ServerSocket serverSocket=new ServerSocket(port);
		
			
			final Socket connection=serverSocket.accept();
			Writer writer=new OutputStreamWriter(connection.getOutputStream());
			i=str.length();
			//while(true)
//			for(int i=0;i<9;i++)
//			{
//				
//				Thread.sleep(1000);
//			writer.write(str);
	//		writer.flush();
//				
//			}
			
			
		    InputStream inputstream=connection.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(inputstream));
			Thread.sleep(1000);
		//	while(true)
			 server_info=br.readLine();
		     System.out.println("the content is "+server_info);
			//{
		       // Thread.sleep(1000);
				writer.write(str);
				writer.flush();
				Thread.sleep(1000);
			//	if((server_info=br.readLine())==null)
				//{
				//break;
				//}
			
		    //server_info=br.readLine();
		     //System.out.println("recv is "+server_info);
////				connection.close();
		//}
		     //Thread.sleep(1000);
			
			
			connection.close();
			serverSocket.close();
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}finally
		{
//			try {
//				serverSocket.close();
//			}catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		
	}

	
}
 
}
 
