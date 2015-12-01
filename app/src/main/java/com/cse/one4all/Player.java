package com.cse.one4all;

import org.andengine.extension.multiplayer.protocol.server.connector.ClientConnector;
import org.andengine.extension.multiplayer.protocol.shared.Connector;
import org.andengine.extension.multiplayer.protocol.shared.SocketConnection;

public class Player {

    private String PlayerName;
    private int PlayerHearts = 10;
    private ClientConnector<SocketConnection> PlayerConnector;

    public Player(String pName){

        this.PlayerName = pName;
        //this.PlayerHearts = pHearts;
        //this.PlayerConnector = pConnect;
    }

    public String getPlayerName(){
        return this.PlayerName;
    }
    public synchronized int getPlayerHearts(){
        return this.PlayerHearts;
    }
    public ClientConnector<SocketConnection> getPlayerConnection(){
        return this.PlayerConnector;
    }
    public void setPlayerName(String pn){
        this.PlayerName = pn;
    }
    public synchronized void setPlayerHearts(int ph){
        this.PlayerHearts = ph;
    }
    public void setPlayerConnection(ClientConnector<SocketConnection> pc){
        this.PlayerConnector = pc;
    }

}
