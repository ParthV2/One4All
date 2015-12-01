package com.cse.one4all.messages;

import com.cse.one4all.managers.PlayerManager;

import org.andengine.extension.multiplayer.protocol.adt.message.server.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by chewb on 11/30/2015.
 */
public class StartGameServerMessage extends ServerMessage {

    @Override
    protected void onReadTransmissionData(DataInputStream pDataInputStream) throws IOException {

    }

    @Override
    protected void onWriteTransmissionData(DataOutputStream pDataOutputStream) throws IOException {

    }

    @Override
    public short getFlag() {
        return PlayerManager.FLAG_MESSAGE_SERVER_START_GAME;
    }
}
