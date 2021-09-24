import java.net.*;
import java.io.*;
class Server
{
	//constructor

	ServerSocket server;
	Socket socket;
	BufferedReader br;
	PrintWriter out;

	public Server()
	{
		try{
		server = new ServerSocket(7777);
		System.out.println("Server is ready to accept the connection");
		System.out.println("waiting....");
		socket = server.accept();

		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		startReading();
		startWriting();
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}

	public void startReading()
	{
		//thread using here  which is used for the taking the data from the user and also 
		Runnable r1=()->{

			System.out.println("Start reading");

			while(true)
			{
				try{
				String msg = br.readLine();
				if(msg.equals("exit"))
				{
					System.out.println("client terminated the chat");
					break;
				}
				System.out.println("Client : "+msg);
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}

		};
		new Thread(r1).start();
	}

	public void startWriting()
	{
		Runnable r2=()->{

			while(true)
			{
				try
				{
					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					String content = br1.readLine();
					out.println(content);
					out.flush();
				}catch(Exception e2)
				{
					e2.printStackTrace();
				}
			}
		};
		new Thread(r2).start();
	}
	// main method

	public static void main(String[] args) {
		System.out.println("this is the server running...");
		new Server();
	}
}