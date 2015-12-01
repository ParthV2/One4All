package com.cse.one4all.messages;

import android.util.Log;

import com.cse.one4all.utils.Logger;

import org.andengine.extension.multiplayer.protocol.adt.message.StringMessage;
import org.andengine.extension.multiplayer.protocol.adt.message.client.IClientMessage;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by chewb on 11/30/2015.
 */
public class ClientStringMessage extends StringMessage implements IClientMessage {

    public ClientStringMessage() {
        super("");
    }

    public ClientStringMessage(String pString) {
        super(pString);
    }

    @Override
    protected void onReadTransmissionData(DataInputStream pDataInputStream) throws IOException {
        Logger.debug("ClientStringMessage: onReadTransmissionData");

    }

    @Override
    public short getFlag() {
        return 1;
    }
}
