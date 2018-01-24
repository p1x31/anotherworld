package server;

import java.io.IOException;
import java.util.Random;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import Client.PlayerChar;
import NetworkClasses.LoginRequest;
import NetworkClasses.LoginResponse;
import NetworkClasses.Message;

public class MainServer {

	private int tcpPort;
	private int udpPort;
	public static Server server;
	private Kryo kryo;
	public static float randomFloatNumber;

	static MainServerListener listener = new MainServerListener();

	public MainServer(int tcpPort, int udpPort) {
		this.tcpPort = tcpPort;
		this.udpPort = udpPort;
		server = new Server();

		kryo = server.getKryo();
		registerKryoClasses();
		Random rand = new Random(1);
	}

	public void startServer() {
		Log.info("Starting Server");

		server.start();
		try {
			server.bind(tcpPort, udpPort);
			server.addListener(listener);
			update();
		} catch (IOException e) {
			Log.info("Port already used");

			e.printStackTrace();
		}
	}

	// Try changing this to non static and see where this effects our game
	public static void stopServer() {
		Log.info("Server stopped");

		server.stop();
	}

	public void update() {
		while (true) {

		}
	}

	private void registerKryoClasses() {
		kryo.register(LoginRequest.class);
		kryo.register(LoginResponse.class);
		kryo.register(Message.class);
		kryo.register(PlayerChar.class);
		kryo.register(org.newdawn.slick.geom.Rectangle.class);
		kryo.register(float[].class);
		kryo.register(NetworkClasses.PacketUpdateX.class);
		kryo.register(NetworkClasses.PacketUpdateY.class);
		kryo.register(NetworkClasses.PacketAddPlayer.class);
		kryo.register(NetworkClasses.PacketRemovePlayer.class);
		kryo.register(NetworkClasses.PacketUserName.class);
		kryo.register(NetworkClasses.RandomNumber.class);
		kryo.register(NetworkClasses.PacketScore.class);
		kryo.register(NetworkClasses.PacketScoreCubeUpdate.class);
		kryo.register(NetworkClasses.PacketGameOver.class);
		kryo.register(NetworkClasses.PacketSpeedCube.class);
	}



	public static void main(String args[]) {
		Log.set(Log.LEVEL_INFO);
		Random rand = new Random();
		randomFloatNumber = rand.nextFloat();
		MainServer main = new MainServer(1687, 1687);
		main.startServer();
	}

} // end total class
