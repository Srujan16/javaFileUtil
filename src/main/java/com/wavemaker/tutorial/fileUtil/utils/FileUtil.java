package com.wavemaker.tutorial.fileUtil.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;


//TODO : Create a servlet that sends zipped resource as response to client :DONE



/**
 * Created by srujant on 22/2/17.
 */
public class FileUtil {

    /*
    * public native int read() throws IOException; in fileInputStream is a heavyOperation.
    * BufferedInputStream reduces number of system calls made to OS.
    * */

    public static void copyFileWithoutBuffer(File sourceFile, String destination) {

        InputStream inputStream = getInputStream(sourceFile);
        OutputStream outputStream = getOutPutStream(destination);
        System.out.print("Without Buffer:  ");
        copy(inputStream, outputStream);
    }

    public static void copyFileWithBuffer(File sourceFile, String destination) {
        InputStream inputStream = new BufferedInputStream(getInputStream(sourceFile));
        OutputStream outputStream = new BufferedOutputStream(getOutPutStream(destination));
        System.out.print("With Buffer: ");
        copy(inputStream, outputStream);
    }


    public static void fileCopyWithFileUtil(File sourceFile, String destination) {
        long startTime = System.currentTimeMillis();
        try {
            FileUtils.copyFile(sourceFile, getOutPutStream(destination));
            System.out.println("Using apache-file-util: " + String.valueOf(System.currentTimeMillis() - startTime));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void fileCopyWithChannels(File sourceFile, String destinationFile) {
        FileChannel sourceFileChannel = getInputStream(sourceFile).getChannel();
        FileChannel destinationFileChannel = getOutPutStream(destinationFile).getChannel();
        long startTime;
        try {
            startTime = System.currentTimeMillis();
            destinationFileChannel.transferFrom(sourceFileChannel, 0, sourceFileChannel.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeStream(sourceFileChannel);
            closeStream(destinationFileChannel);
        }

        System.out.println("Using file-channels: " + String.valueOf(System.currentTimeMillis() - startTime));
    }

    public static void createZip(File[] files, String destination) {

        OutputStream outputStream = null;
        ZipOutputStream zipOutputStream = null;
        try {
            outputStream = getOutPutStream(destination);
            zipOutputStream = new ZipOutputStream(outputStream);
            byte[] buffer = new byte[1024];
            int length;
            for (int i = 0; i < files.length; i++) {
                InputStream inputStream = getInputStream(files[i]);
                zipOutputStream.putNextEntry(new ZipEntry(files[i].getName()));
                while ((length = inputStream.read(buffer)) > 0) {
                    zipOutputStream.write(buffer, 0, length);
                }
                zipOutputStream.closeEntry();
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        closeStream(zipOutputStream);
    }


    private static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] fileData = new byte[1024];
        int length;
        long startTime;
        try {
            startTime = System.currentTimeMillis();
            while ((length = inputStream.read(fileData)) > 0) {
                outputStream.write(fileData, 0, length);
            }
        } catch (IOException e) {
            throw new RuntimeException("failed to read data", e);
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
        System.out.println(System.currentTimeMillis() - startTime);

    }

    private static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static FileInputStream getInputStream(File sourceFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            return fileInputStream;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found", e);
        }
    }

    private static FileOutputStream getOutPutStream(String destination) {
        File destinationFile = getFile(destination);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(destinationFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found exception", e);
        }
        return fileOutputStream;

    }

    private static File getFile(String destination) {
        File destinationFile = new File(destination);
        destinationFile.mkdirs();
        return new File(destinationFile, destinationFile.getName());
/*
        try {
             destinationFile.createNewFile();
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return destinationFile;
*/
    }


}
