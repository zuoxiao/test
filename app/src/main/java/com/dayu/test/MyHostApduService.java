package com.dayu.test;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

/**
 * Copyright (C), 2023,
 * Author: zuo
 * Date: 2023-11-27 21:19
 * Description:
 */
public class MyHostApduService extends HostApduService {
    public static String TAG = "MyHostApduService";
    private static final String M1_CARD_AID = "D2760000850101";
    private static final String SUCCESS_RESPONSE = "9000";
    private static final String ERROR_RESPONSE = "6A82";

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        Log.d(TAG, "Received APDU: " + byteArrayToHexString(commandApdu));
        String command = byteArrayToHexString(commandApdu);

        if (command.startsWith("00A40400")) {
            // SELECT command, check if the AID matches M1 card AID
            if (command.endsWith(M1_CARD_AID)) {
                return hexStringToByteArray(SUCCESS_RESPONSE);
            } else {
                return hexStringToByteArray(ERROR_RESPONSE);
            }
        } else if (command.startsWith("B0")) {
            // READ command, implement your logic to read data
            // For simplicity, return a dummy response
            return hexStringToByteArray("AABBCCDDEEFF" + SUCCESS_RESPONSE);
        } else if (command.startsWith("D6")) {
            // WRITE command, implement your logic to write data
            // For simplicity, return a success response
            return hexStringToByteArray(SUCCESS_RESPONSE);
        } else {
            return hexStringToByteArray(ERROR_RESPONSE);
        }
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated: " + reason);
        // Handle deactivation (if needed).
    }

    private String byteArrayToHexString(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);
        for (byte b : array) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
