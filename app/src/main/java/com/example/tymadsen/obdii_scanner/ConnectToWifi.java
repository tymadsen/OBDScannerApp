package com.example.tymadsen.obdii_scanner;

import android.os.AsyncTask;

import com.github.pires.obd.commands.SpeedCommand;
import com.github.pires.obd.commands.engine.RPMCommand;
import com.github.pires.obd.enums.ObdProtocols;

import java.net.InetAddress;
import java.net.Socket;

import com.github.pires.obd.commands.protocol.*;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;


/**
 * Created by tymadsen on 9/21/16.
 */

public class ConnectToWifi extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Socket socket = null;
    private String result = null;

    protected String doInBackground(String... params) {
        System.out.println(params[0]);
        System.out.println(params[1]);



        try {
            socket = new Socket("192.168.0.10", Integer.parseInt(params[1]));
            //new EchoOffCommand().run(socket.getInputStream(), socket.getOutputStream());

            //new LineFeedOffCommand().run(socket.getInputStream(), socket.getOutputStream());
            //new TimeoutCommand(125).run(socket.getInputStream(), socket.getOutputStream());
            //new SelectProtocolCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
            //new AmbientAirTemperatureCommand().run(socket.getInputStream(), socket.getOutputStream());
            final RPMCommand engineRpmCommand = new RPMCommand();
            final SpeedCommand speedObdCommand = new SpeedCommand();
//            while (!Thread.currentThread().isInterrupted()) {
                engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
                speedObdCommand.run(socket.getInputStream(), socket.getOutputStream());
                result = engineRpmCommand.getFormattedResult();//getResult();

//            }

        } catch (Exception e) {
            // handle errors
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(String res) {
        // TODO: check this.exception
        // TODO: do something with the feed
        System.out.println(res);
    }
}