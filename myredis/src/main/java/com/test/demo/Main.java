package com.test.demo;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("./test.txt"));

            System.out.println("世界".getBytes());
            outputStream.write("世界".getBytes());
            outputStream.flush();
            outputStream.close();
            DataInputStream dis = new DataInputStream(new FileInputStream(
                    "test.txt"));
            // 读取字节
            byte[] b = new byte[6];
            dis.read(b);
            System.out.println(new String(b, 0, 6));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
