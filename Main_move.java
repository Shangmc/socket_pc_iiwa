package application;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.controllerModel.Controller;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;

import application.SocketServerClient;
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
public class Main_move extends RoboticsAPIApplication {
	@Inject
	private LBR lbr7;
    @Inject
    private Controller controler;
    @Inject
    private Tool grip; 
    @Inject
    private ObjectFrame tcp;
    private String str=null;
    private String str_recv=null;
    private int port=30003;
    private int count_x=0;
    private int count_8=0;
    private int var=0;
    private double x_iphx;
    private double y_iphx;
    private double z_iphx;
    private double x_iph8;
    private double y_iph8;
    private double z_iph8;
    private ArrayList<SocketServerClient> arraysocket=new ArrayList<SocketServerClient>();
	@Override
	public void initialize() {
		controler=(Controller)getContext().getController("KUKA_Sunrise_Cabinet1");
		grip=createFromTemplate("Griper");
		grip.attachTo(lbr7.getFlange());
		tcp=grip.getFrame("/tcp");
		
		
		/****caculate the tray
		 * *****/
		/*for(int i=0;i<4;i++)
		{
			x_iphx[i][1]=0;
			y_iphx[i][1]=(i-1)*0;
			z_iphx[i][1]=0;
			x_iph8[i][1]=0;
			y_iph8[i][1]=(i-1)*0;
			z_iph8[i][1]=0;
		}*/
		
	}

	@Override
	public void run() {
		
		lbr7.move(ptp(getApplicationData().getFrame("/Beer/Start")).setJointVelocityRel(.5));
		while(true)
		{
		tcp.move(ptp(getApplicationData().getFrame("/Beer/P1")).setJointVelocityRel(.5));
		{
		SocketServerClient socket_server=new SocketServerClient(30003);
			str_recv=socket_server.SendRecvContent("1");
			System.gc();
		}
	   
			if(str_recv=="1")
				var=1;
			else if(str_recv=="2")
				var=2;
			else if(str_recv=="R")
				var=3;
			switch(var)
			{
			case 1:
				count_x+=1;
				
				x_iphx=0;
				y_iphx=(count_x-1)*0;
				z_iphx=0;
				lbr7.move(linRel(Transformation.ofDeg(x_iphx, y_iphx, z_iphx+20, 0, 0, 0),getApplicationData().getFrame("/P1")).setCartVelocity(.5));
				lbr7.move(linRel(Transformation.ofDeg(x_iphx, y_iphx, z_iphx, 0, 0, 0),getApplicationData().getFrame("/P1")).setCartVelocity(.5));
				if(count_x==4)
				  count_x=0;
				
				/***吸嘴***/
				lbr7.move(linRel(Transformation.ofDeg(x_iphx, y_iphx, z_iphx+20, 0, 0, 0),getApplicationData().getFrame("/P1")).setCartVelocity(.5));
				lbr7.move(lin(getApplicationData().getFrame("/P2")).setCartVelocity(.5));
				lbr7.move(lin(getApplicationData().getFrame("/P3")).setCartVelocity(.5));
				/****放料,机器人发送放料成功****/
				{
					SocketServerClient socket_client=new SocketServerClient("172.31.1.19",30003);
					str_recv=socket_client.SendRecvContent("1");
				}
				/****等待，机器人端打开，监听****/
				{
					SocketServerClient socket_server=new SocketServerClient(30003);
					str_recv=socket_server.SendRecvContent("1");
				}
				
				
				
				
			case 2:
				count_8+=1;
				
				
				
			case 3:
				
			}
			
			
		}
		
		
		
		
		
		
		}
	
	@Override
	public void dispose()
	{
	 super.dispose();
	}
	
		// your application execution starts here
		//lBR_iiwa_7_R800_1.move(ptpHome());
	}
