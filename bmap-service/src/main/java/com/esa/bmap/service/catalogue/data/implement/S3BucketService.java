package com.esa.bmap.service.catalogue.data.implement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectRequest;

@Service(value = "S3BucketService")
public class S3BucketService {
	private static final Logger LOG = LoggerFactory.getLogger(S3BucketService.class);

	public static final String S3_PROTOCOL = "s3";

	/**
	 * method downloading a S3 object into a target datadir
	 * 
	 * @param obsClient
	 * @param bucketName
	 * @param objectKey
	 * @param targetFilePath
	 * @return
	 * @throws ObsException
	 * @throws IOException
	 * @throws BmapException
	 */
	public static File downloadS3ObjectToLocalTarget(ObsClient obsClient, String bucketName, String objectKey,
			String targetFilePath, String userId) throws ObsException, IOException, BmapException {
		LOG.info("Downloading S3 object with key " + objectKey + " from bucket " + bucketName + "to target "
				+ targetFilePath);
		ObsObject obsObject = obsClient.getObject(new GetObjectRequest(bucketName, objectKey));

		InputStream reader = new BufferedInputStream(obsObject.getObjectContent());

		// Taking last index with slash split, returning the fileName
		String fileName = objectKey.substring(objectKey.lastIndexOf("/") + 1);

		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// We move the data to this destination
		File finalFile = new File(targetFilePath, userId + '-' + FilenameUtils.removeExtension(fileName) + fileSuffix
				+ "." + FilenameUtils.getExtension(fileName));
		LOG.info(finalFile.getAbsolutePath());
		

		OutputStream writer = new BufferedOutputStream(new FileOutputStream(finalFile));

		int read = -1;

		while ((read = reader.read()) != -1) {
			writer.write(read);
		}

		writer.flush();
		writer.close();
		reader.close();
		return finalFile;
	}

	/**
	 * Method to upload a given file to a S3Bucket
	 * 
	 * @param obsClient
	 * @param file
	 * @param bucketName
	 * @param objectKey
	 */
	public static void uploadFileToS3Bucket(ObsClient obsClient, File file, String bucketName, String objectKey) {

		LOG.info("Uploading S3 object with key " + objectKey + " to bucket " + bucketName);
		obsClient.putObject(new PutObjectRequest(bucketName, objectKey, file));
	}
}
