package com.mashup6th.preambackend.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor    // final 멤버변수를 생성자 항목에 포함시킨다. (amazonS3Client만 포함)
@Component
public class StorageServiceImpl implements StorageService {

    private AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.endpointurl}")
    private String endpointUrl;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.amazonS3Client = new AmazonS3Client(credentials);
    }

    @Override
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile).orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환 실패하였습니다."));

        return upload(uploadFile, dirName);
    }

    @Override
    public String upload(File file, String dirName) {
        String fileName = dirName + "/" + file.getName();
        String uploadImgUrl = put(file, fileName);
        remove(file);

        return uploadImgUrl;
    }

    @Override
    public String put(File file, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    @Override
    public void remove(File file) {
        if (file.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일 삭제에 실패하였습니다.");
        }
    }

    @Override
    public Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = File.createTempFile(getUuid(), ".jpg");
        try (FileOutputStream fos = new FileOutputStream(convertFile)) {
            fos.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(convertFile);
    }

    public String getUuid() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }
}
