package com.javarush.task.task31.task3113;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

   static  long numberOfDir = 0;
   static long numberOfFiles = 0;
   static long totalSize = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path  = Paths.get(reader.readLine());

        if(!Files.isDirectory(path)){
            System.out.println(path.toAbsolutePath().toString() + " - не папка ");
            return;
        }
        else{
            Files.walkFileTree(path,new MyVisitorFile());
            System.out.println("Всего папок - " + (numberOfDir-1));
            System.out.println("Всего файлов - " + numberOfFiles);
            System.out.println("Общий размер - " + totalSize);
        }

    }

    private static class MyVisitorFile extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            numberOfDir += 1;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            numberOfFiles+=1;
            totalSize+=attrs.size();
            return FileVisitResult.CONTINUE;
        }
    }
}
