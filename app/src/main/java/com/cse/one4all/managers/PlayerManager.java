package com.cse.one4all.managers;

import com.cse.one4all.Player;
import com.cse.one4all.messages.MinigameCompleteClientMessage;
import com.cse.one4all.messages.StartGameServerMessage;
import com.cse.one4all.messages.UpdateLivesServerMessage;
import com.cse.one4all.utils.Logger;

import org.andengine.extension.multiplayer.protocol.adt.message.client.IClientMessage;
import org.andengine.extension.multiplayer.protocol.adt.message.server.IServerMessage;
import org.andengine.extension.multiplayer.protocol.client.IServerMessageReader;
import org.andengine.extension.multiplayer.protocol.client.connector.ServerConnector;
import org.andengine.extension.multiplayer.protocol.client.connector.SocketConnectionServerConnector;
import org.andengine.extension.multiplayer.protocol.server.IClientMessageReader;
import org.andengine.extension.multiplayer.protocol.server.Server;
import org.andengine.extension.multiplayer.protocol.server.SocketServer;
import org.andengine.extension.multiplayer.protocol.server.SocketServerDiscoveryServer;
import org.andengine.extension.multiplayer.protocol.server.connector.ClientConnector;
import org.andengine.extension.multiplayer.protocol.server.connector.SocketConnectionClientConnector;
import org.andengine.extension.multiplayer.protocol.shared.SocketConnection;
import org.andengine.extension.multiplayer.protocol.util.WifiUtils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class PlayerManager {

    public static final short FLAG_MESSAGE_CLIENT_MINIGAME_COMPLETE = 1;

    public static final short FLAG_MESSAGE_SERVER_START_GAME = 2;
    public static final short FLAG_MESSAGE_SERVER_UPDATE_LIVES = 3;

    private static final PlayerManager INSTANCE = new PlayerManager();

    private int PORT = 55555;
    private static final Random RANDOM = new Random();

    private ServerConnector serverConnector;
    private Server server;
    private SocketServerDiscoveryServer discoveryServer;

    public Player player;

    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;

    public String getGameCode(){
        try {
            String ip = WifiUtils.getWifiIPv4Address(ResourcesManager.getInstance().activity);
            if(ip.equalsIgnoreCase("0.0.0.0")){
                return "-1";
            }
            return ip.split("\\.")[3];
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String gameCodeToIp(String gameCode){
        if(gameCode.equalsIgnoreCase("-1")){
            return "127.0.0.1";
        }

        try {
            String[] arr = WifiUtils.getWifiIPv4Address(ResourcesManager.getInstance().activity).split("\\.");
            return arr[0] + "." + arr[1] + "." + arr[2] + "." + gameCode;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public boolean isServerRunning(){
        return server != null && server.isRunning();
    }

    public boolean joinGame(String gameCode){

        final String ip = gameCodeToIp(gameCode);
        Logger.debug("[Client] Connecting on: " + ip + ":" + PORT);

        try {
            serverConnector = new SocketConnectionServerConnector(new SocketConnection(new Socket(ip, PORT)), new IServerMessageReader.ServerMessageReader<SocketConnection>() {
                @Override
                public void handleMessage(ServerConnector<SocketConnection> pConnector, IServerMessage pMessage) throws IOException {
                    Logger.debug("[Client] Handle Server Message: " + pMessage.getFlag());
                    switch(pMessage.getFlag()){
                        case FLAG_MESSAGE_SERVER_START_GAME:
                            Logger.debug("[Client] StartGameServerMessage");
                            MinigameManager.getInstance().startGame();
                            break;
                        case FLAG_MESSAGE_SERVER_UPDATE_LIVES:
                            UpdateLivesServerMessage message = (UpdateLivesServerMessage) pMessage;
                            Logger.debug("[Client] UpdateLivesServerMessage: " + message.getPlayer1Lives() + " - " + message.getPlayer2Lives());
                            player1.setPlayerHearts(message.getPlayer1Lives());
                            if(player2 != null){
                                player2.setPlayerHearts(message.getPlayer2Lives());
                            }
                            SceneManager.getInstance().minigameScene.updateHearts();

                            if(PlayerManager.getInstance().player1.getPlayerHearts() <= 0 || (PlayerManager.getInstance().player2 != null && PlayerManager.getInstance().player2.getPlayerHearts() <= 0)){
                                MinigameManager.getInstance().endGame();
                                return;
                            }

                            break;
                    }

                }
            }, new SocketConnectionServerConnector.ISocketConnectionServerConnectorListener() {
                @Override
                public void onStarted(ServerConnector<SocketConnection> pServerConnector) {
                    Logger.debug("[Client] Connected to server");
                }

                @Override
                public void onTerminated(ServerConnector<SocketConnection> pServerConnector) {
                    if(MinigameManager.getInstance().isStarted()){
                        disconnect();
                        MinigameManager.getInstance().endGame();
                    } else {
                        disconnect();

                    }
                    Logger.debug("[Client] Disconnected from server");
                }
            });

            serverConnector.registerServerMessage(FLAG_MESSAGE_SERVER_START_GAME, StartGameServerMessage.class);
            serverConnector.registerServerMessage(FLAG_MESSAGE_SERVER_UPDATE_LIVES, UpdateLivesServerMessage.class);

            serverConnector.start();
            return true;
        } catch(IOException e){
            Logger.debug("No servers available");
            return false;
        }
    }

    public void startMultiplayerGame(){
        Logger.debug("[Server] Starting on: " + gameCodeToIp(getGameCode()) + ":" + PORT);

        server = new SocketServer(PORT, new SocketConnectionClientConnector.ISocketConnectionClientConnectorListener() {
            @Override
            public void onStarted(ClientConnector<SocketConnection> pClientConnector) {
                pClientConnector.registerClientMessage(FLAG_MESSAGE_CLIENT_MINIGAME_COMPLETE, MinigameCompleteClientMessage.class);

                Logger.debug("[Server] Client connected");
                if(player1 == null){
                    player1 = new Player("Player 1");
                    player1.setPlayerConnection(pClientConnector);
                    player = player1;
                    SceneManager.getInstance().multiplayerScene.onPlayerJoined(player1.getPlayerName());
                } else if(player2 == null){
                    player2 = new Player("Player 2");
                    player2.setPlayerConnection(pClientConnector);
                    SceneManager.getInstance().multiplayerScene.onPlayerJoined(player2.getPlayerName());
                }

            }

            @Override
            public void onTerminated(ClientConnector<SocketConnection> pClientConnector) {
                Logger.debug("[Server] Client disconnected");
                if(player1 != null && pClientConnector == player1.getPlayerConnection()){
                    SceneManager.getInstance().multiplayerScene.onPlayerDisconnect("Player 1");
                    player1 = null;
                }
                if(player2 != null && pClientConnector == player2.getPlayerConnection()){
                    SceneManager.getInstance().multiplayerScene.onPlayerDisconnect("Player 2");
                    player2 = null;
                }

            }
        }, new SocketServer.ISocketServerListener<ClientConnector<SocketConnection>>() {
            @Override
            public void onStarted(SocketServer pSocketServer) {
                Logger.debug("[Server] Started");
                SceneManager.getInstance().multiplayerScene.onServerStart(getGameCode());
                joinGame(getGameCode());
            }

            @Override
            public void onTerminated(SocketServer pSocketServer) {
                Logger.debug("[Server] Stopped");
            }

            @Override
            public void onException(SocketServer pSocketServer, Throwable pThrowable) {

            }

        }) {

            @Override
            protected ClientConnector<SocketConnection> newClientConnector(SocketConnection pSocketConnection) throws IOException {
                return new ClientConnector(pSocketConnection, new IClientMessageReader.ClientMessageReader<SocketConnection>() {

                    @Override
                    public void handleMessage(ClientConnector pClientConnector, IClientMessage pClientMessage) throws IOException {
                        Logger.debug("[Server] Handle Client Message: " + pClientMessage.getFlag());
                        switch(pClientMessage.getFlag()){
                            case FLAG_MESSAGE_CLIENT_MINIGAME_COMPLETE:
                                MinigameCompleteClientMessage message = (MinigameCompleteClientMessage) pClientMessage;
                                Logger.debug("[Server] MinigameCompleteClientMessage: " + message.getPlayer());
                                if(message.getPlayer().equalsIgnoreCase("Player 1")){
                                    if(player2 == null){
                                        player1.setPlayerHearts(10);
                                    } else {
                                        player2.setPlayerHearts(10);
                                    }
                                } else if(message.getPlayer().equalsIgnoreCase("Player 2")){
                                    player1.setPlayerHearts(10);
                                }
                                sendUpdateLives();
                                break;
                        }
                    }
                });
            }
        };



        server.start();
    }

    public void sendStartGame(){
        try {
            server.sendBroadcastServerMessage(new StartGameServerMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdateLives(){
        try {
            server.sendBroadcastServerMessage(new UpdateLivesServerMessage(player1.getPlayerHearts(), player2 != null ? player2.getPlayerHearts() : 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMinigameComplete(){
        try {
            serverConnector.sendClientMessage(new MinigameCompleteClientMessage(player.getPlayerName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer(){
        player = null;
        player1 = null;
        player2 = null;

        if(server != null){
            server.terminate();
            server = null;
        }
    }

    public void disconnect(){
        player = null;
        player1 = null;
        player2 = null;

        if(serverConnector != null){
            serverConnector.terminate();
            serverConnector = null;
        }
    }

    public static PlayerManager getInstance() {
        return INSTANCE;
    }
}
