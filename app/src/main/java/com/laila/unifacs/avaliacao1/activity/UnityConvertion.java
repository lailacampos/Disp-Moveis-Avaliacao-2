package com.laila.unifacs.avaliacao1.activity;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class UnityConvertion {

    // Empty constructor
    public UnityConvertion() {

    }

    public static List<String> convertLatLongGrauMinutoSegundo(double latitude, double longitude) {

        List<String> latLongList = new ArrayList<>();
        StringBuilder latBuilder = new StringBuilder();
        StringBuilder longBuilder = new StringBuilder();

        String latString = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latSplit = latString.split(":");

        latBuilder.append(latSplit[0]);
        latBuilder.append("º");
        latBuilder.append(latSplit[1]);
        latBuilder.append("'");
        latBuilder.append(latSplit[2]);
        latBuilder.append("\"");
        latBuilder.append(" ");

        if (latitude < 0) {latBuilder.append("S");}
        else {latBuilder.append("N");}

        String longString = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longSlit = longString.split(":");

        longBuilder.append(longSlit[0]);
        longBuilder.append("º");
        longBuilder.append(longSlit[1]);
        longBuilder.append("'");
        longBuilder.append(longSlit[2]);
        longBuilder.append("\"");
        longBuilder.append(" ");

        if (longitude < 0) {longBuilder.append("W");}
        else {longBuilder.append("E");}

        latLongList.add(latBuilder.toString());
        latLongList.add(longBuilder.toString());

        return latLongList;
    }

    public static List<String> convertLatLongGrauMinuto(double latitude, double longitude) {

        List<String> latLongList = new ArrayList<>();
        StringBuilder latBuilder = new StringBuilder();
        StringBuilder longBuilder = new StringBuilder();

        String latString = Location.convert(Math.abs(latitude), Location.FORMAT_MINUTES);
        String[] latSplit = latString.split(":");

        latBuilder.append(latSplit[0]);
        latBuilder.append("º");
        latBuilder.append(latSplit[1]);
        latBuilder.append("'");
        latBuilder.append(" ");

        if (latitude < 0) {latBuilder.append("S");}
        else {latBuilder.append("N");}

        String longString = Location.convert(Math.abs(longitude), Location.FORMAT_MINUTES);
        String[] longSlit = longString.split(":");

        longBuilder.append(longSlit[0]);
        longBuilder.append("º");
        longBuilder.append(longSlit[1]);
        longBuilder.append("'");
        longBuilder.append(" ");

        if (longitude < 0) {longBuilder.append("W");}
        else {longBuilder.append("E");}

        latLongList.add(latBuilder.toString());
        latLongList.add(longBuilder.toString());

        return latLongList;
    }

    public static List<String> convertLatLongDecimal(double latitude, double longitude) {

        List<String> latLongList = new ArrayList<>();
        StringBuilder latBuilder = new StringBuilder();
        StringBuilder longBuilder = new StringBuilder();

        String latString = Location.convert(Math.abs(latitude), Location.FORMAT_DEGREES);

        latBuilder.append(latString);
        latBuilder.append(" ");

        if (latitude < 0) {latBuilder.append("S");}
        else {latBuilder.append("N");}

        String longString = Location.convert(Math.abs(longitude), Location.FORMAT_DEGREES);

        longBuilder.append(longString);
        longBuilder.append(" ");

        if (longitude < 0) {longBuilder.append("W");}
        else {longBuilder.append("E");}

        latLongList.add(latBuilder.toString());
        latLongList.add(longBuilder.toString());

        return latLongList;
    }

    public static String convertSpeedKmToMile(float speed) {

        String speedString = String.valueOf(speed / 1.609344f) + " Mph";
        return speedString;
    }

    public static String formatSpeedKmH(float speed) {

        String speedString = String.valueOf(speed) + " Km/h";
        return speedString;
    }

}
