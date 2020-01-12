package com.amazonaws.lambda.sftp;

import java.util.Map;

import com.amazonaws.lambda.ftp.util.FTPTransfer;
import com.amazonaws.lambda.sftp.constants.FTPConstants;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class LambdaFunctionHandler implements RequestHandler<Map<String,String>, String> {



	public LambdaFunctionHandler() {}



	@Override
	public String handleRequest(Map<String,String> event, Context context) {
		context.getLogger().log("Received event: " + event);

		String ftpPassword= event.get(FTPConstants.FTP_PASSWORD);
		String ftpUserName= event.get(FTPConstants.FTP_USERNAME);
		String ftPHost= event.get(FTPConstants.HOST_NAME);
		String srcFile= event.get(FTPConstants.SOURCE_FILE);
		String remotePath= event.get(FTPConstants.DESTINATION_PATH);
		String port= event.get(FTPConstants.PORT);
		
		FTPTransfer ftp = new FTPTransfer(ftPHost, Integer.parseInt(port), ftpUserName, ftpPassword);
		ftp.upload(srcFile, remotePath);

		return "Success";
	}
}