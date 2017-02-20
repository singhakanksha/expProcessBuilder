package io.cotiviti;

import java.io.*;

public class ProcessBuilderEchoExample {
    public static void main(String[] args) throws InterruptedException,
            IOException {
        ProcessBuilder pb = new ProcessBuilder("echo","asmkd");
        System.out.println(pb.environment() );
       // System.out.print(pb.directory()+ "\n");
        System.out.print(pb.directory(new File("/Users/asingh/workdir")).directory() + "\n");
        System.out.println("Run echo command");

        Process process = pb.start();
        process.getOutputStream().write(1);
        int errCode = process.waitFor();

     //   System.out.println("Echo output stream " + new BufferedWriter(new OutputStreamWriter(process.getOutputStream())));
        System.out.println("Echo command executed, any errors? " + (errCode == 0 ? "No" : "Yes"));
        System.out.println("Echo Output:\n" + output(process.getInputStream()));
    }

    private static String output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + System.getProperty("line.separator"));
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}