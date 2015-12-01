package com.cse.one4all.messages;

import com.cse.one4all.managers.PlayerManager;

import org.andengine.extension.multiplayer.protocol.adt.message.client.ClientMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by chewb on 11/30/2015.
 */
public class MinigameCompleteClientMessage extends ClientMessage {

    private String player;

    public MinigameCompleteClientMessage(){

    }

    public MinigameCompleteClientMessage(String player){
        this.player = player;
    }

    @Override
    protected void onReadTransmissionData(DataInputStream pDataInputStream) throws IOException {
        player = pDataInputStream.readUTF();
    }

    @Override
    protected void onWriteTransmissionData(DataOutputStream pDataOutputStream) throws IOException {
        pDataOutputStream.writeUTF(getPlayer());
    }

    @Override
    public short getFlag() {
        return PlayerManager.FLAG_MESSAGE_CLIENT_MINIGAME_COMPLETE;
    }

    public String getPlayer() {
        return player;
    }
}
