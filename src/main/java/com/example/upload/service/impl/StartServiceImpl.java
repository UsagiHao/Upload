package com.example.upload.service.impl;

import com.example.upload.service.StartService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;

@Service
public class StartServiceImpl implements StartService {
    @Override
    public void startUpload() {
        File commentFile = new File("/home/hadoop/PycharmProjects/DongQiuDiAPI/finalusers.json");
        try {
            BufferedReader in = new BufferedReader(new FileReader(commentFile));
            String str;
            String fileContent = "";
            String pathPrefix = "hdfs://172.19.241.194:9000/user/hadoop/";
            Configuration conf = new Configuration();
            int num = 0;
            while ((str = in.readLine()) != null){
                num++;
                fileContent = fileContent + str +"\n";
                if(num == 50){
                    try{
                        String fileName =pathPrefix + "file" + System.currentTimeMillis() + ".json";
                        FileSystem fs = FileSystem.get(URI.create(fileName), conf);
                        Path path = new Path(fileName);
                        FSDataOutputStream outputStream = fs.create(path);
                        Thread.sleep(1000);
                        outputStream.write(fileContent.getBytes("UTF-8"));
                        System.out.println(fileContent);
                        outputStream.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        fileContent = "";
                        num = 0;
                    }
                }
            }
            System.out.println(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
