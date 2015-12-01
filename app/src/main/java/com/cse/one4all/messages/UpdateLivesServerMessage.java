package com.cse.one4all.messages;

import com.cse.one4all.managers.PlayerManager;

import org.andengine.extension.multiplayer.protocol.adt.message.server.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by chewb on 11/30/2015.
 */
public class UpdateLivesServerMessage extends ServerMessage {

    private int player1Lives;
    private int player2Lives;

    public UpdateLivesServerMessage(){

    }

    public UpdateLivesServerMessage(int player1Lives, int player2Lives){
        this.player1Lives = player1Lives;
        this.player2Lives = player2Lives;
    }



    @Override
    protected void onReadTransmissionData(DataInputStream pDataInputStream) throws IOException {
        player1Lives = pDataInputStream.readInt();
        player2Lives = pDataInputStream.readInt();
    }

    @Override
    protected void onWriteTransmissionData(DataOutputStream pDataOutputStream) throws IOException {
        pDataOutputStream.writeInt(player1Lives);
        pDataOutputStream.writeInt(player2Lives);
    }

    @Override
    public short getFlag() {
        return PlayerManager.FLAG_MESSAGE_SERVER_UPDATE_LIVES;
    }

    public int getPlayer1Lives() {
        return player1Lives;
    }

    public int getPlayer2Lives() {
        return player2Lives;
    }
}
