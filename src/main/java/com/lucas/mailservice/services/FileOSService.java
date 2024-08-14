package com.lucas.mailservice.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.lucas.mailservice.config.OSClientConfiguration;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;

@Service
public class FileOSService {

    String bucketName = "spring-reports";
    String namespace = "graqo0uq5xuv";

    @Autowired
    private OSClientConfiguration clientConfiguration;

    public String getReportFileContent(String filename){
        GetObjectRequest objectRequest = GetObjectRequest.builder()
        .namespaceName(namespace)
        .bucketName(bucketName)
        .objectName(filename)
        .build();

        try {
            GetObjectResponse objectResponse = clientConfiguration.getObjectStorage().getObject(objectRequest);
            InputStream inputStream = objectResponse.getInputStream();

            String content = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
            return content;

        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
