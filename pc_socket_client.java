package socket;
import java.lang.Runtime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;
import java.lang.Thread.State;
import java.lang.InterruptedException;
import java.lang.Runnable;
import java.lang.management.ThreadInfo;
//import hellokitty.car;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
public class hello {
	
	
	//import java.util.concurrent.ExcecutorService;

	    private static String server_info="hello";
	
		 private static String str="come from pc client\r\n";
		static int i=0;
		public static void main(String[] args) throws InterruptedException
		{
			try {
				
				int port=30003;
				
			//	InetAddress bindAddress=InetAddress.getByName("172.31.1.21");
				
				Socket clientSocket=new Socket("172.31.1.147",30003);
			
				
				i=str.length();
				
			//	
				InputStream inputstream=clientSocket.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(inputstream));
				OutputStream outputstream=clientSocket.getOutputStream();
				//final Socket connection=serverSocket.accept();
				Writer writer=new OutputStreamWriter(outputstream);
			//	while(true)
				//{
				   Thread.sleep(1000);
				    writer.write(str);	
					writer.flush();
//					
					//if((server_info=br.readLine())==null)
					//{
					//	break;
					//}
//					System.out.println("recv is "+server_info);
				  
					server_info=br.readLine();
//					}
					System.out.println("the content is "+server_info);
					Thread.sleep(1000);
                   
//					//clientSocket.close();
			//	}
				System.out.println("next");
				
			//	for(int i=0;i<9999;i++)
//				{
					
				//	writer.write(str);
				//	writer.flush();
//				}
				clientSocket.close();
				//serverSocket.close();
				
				
			}catch(IOException e)
			{
				e.printStackTrace();
			}finally
			{
//				try {
//					serverSocket.close();
//				}catch(IOException e)
//				{
//					e.printStackTrace();
//				}
//			}
//			
		}

		
	}
	 
	}
	 



