package com.pz.sfile.businessImpl;

import java.util.ArrayList;
import java.util.List;

import pz.jar.unite.supervo.BaseVo;

public class FileUploadStates extends BaseVo{

    private String uploadAddr; //上传用户Ip地址
   
    private List<String> uploadFileUrlList = new ArrayList<String>(); // 上传文件URL列表
    
    private long uploadTotalSize = 0; // 上传总量
    
    private long readTotalSize = 0;// 读取上传总量
   
    private int currentUploadFileNum = 0; // 当前上传文件号
   
    private int successUploadFileCount = 0; // 成功读取上传文件数
    
    private String states = "";// 状态
  
    private long processStartTime = 0l;  // 处理起始时间
   
    private long processEndTime = 0l; // 处理终止时间
    
    private long processRunningTime = 0l;// 处理执行时间
   
    private boolean cancel = false;// 取消上传
   
    private String baseDir = ""; // 上传base目录
    
    public String toXml() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("<FileUploadStates  ");
	buffer.append("uploadAddr ='").append(makeXml(this.uploadAddr)).append("' ");
	buffer.append("uploadFileUrlList ='").append(makeXml(this.uploadFileUrlList)).append("' ");
	buffer.append("uploadTotalSize ='").append(makeXml(this.uploadTotalSize)).append("' ");
	buffer.append("readTotalSize ='").append(makeXml(this.readTotalSize)).append("' ");
	buffer.append("currentUploadFileNum ='").append(makeXml(this.currentUploadFileNum)).append("' ");
	buffer.append("successUploadFileCount ='").append(makeXml(this.successUploadFileCount)).append("' ");
	buffer.append("states ='").append(makeXml(this.states)).append("' ");
	buffer.append("processStartTime ='").append(makeXml(this.processStartTime)).append("' ");
	buffer.append("processEndTime ='").append(makeXml(this.processEndTime)).append("' ");
	buffer.append("processRunningTime ='").append(makeXml(this.processRunningTime)).append("' ");
	buffer.append("cancel ='").append(makeXml(this.cancel)).append("' ");
	buffer.append(">");
	buffer.append("</FileUploadStates>");
	return buffer.toString();
    }
    
    public String toJSon() { 
	StringBuffer strJSon = new StringBuffer(); 
	strJSon.append("{UploadTotalSize:").append(getUploadTotalSize()).append( 
	",") 
	.append("ReadTotalSize:").append(getReadTotalSize()).append(",") 
	.append("CurrentUploadFileNum:").append(getCurrentUploadFileNum()). 
	append(",") 
	.append("SuccessUploadFileCount:").append( 
	getSuccessUploadFileCount()).append(",") 
	.append("States:'").append(getStates()).append("',") 
	.append("ProcessStartTime:").append(getProcessStartTime()). 
	append(",") 
	.append("ProcessEndTime:").append(getProcessEndTime()).append( 
	",") 
	.append("ProcessRunningTime:").append(getProcessRunningTime()). 
	append(",") 
	.append("Cancel:").append(getCancel()).append("}"); 
	return strJSon.toString(); 
	} 

   
    
    public String getUploadAddr() {
	return uploadAddr;
    }
    public void setUploadAddr(String uploadAddr) {
	this.uploadAddr = uploadAddr;
    }
    public long getUploadTotalSize() {
	return uploadTotalSize;
    }
    public void setUploadTotalSize(long uploadTotalSize) {
	this.uploadTotalSize = uploadTotalSize;
    }
    public long getReadTotalSize() {
	return readTotalSize;
    }
    public void setReadTotalSize(long readTotalSize) {
	this.readTotalSize = readTotalSize;
    }
    public int getCurrentUploadFileNum() {
	return currentUploadFileNum;
    }
    public void setCurrentUploadFileNum(int currentUploadFileNum) {
	this.currentUploadFileNum = currentUploadFileNum;
    }
    public int getSuccessUploadFileCount() {
	return successUploadFileCount;
    }
    public void setSuccessUploadFileCount(int successUploadFileCount) {
	this.successUploadFileCount = successUploadFileCount;
    }
    public String getStates() {
	return states;
    }
    public void setStates(String states) {
	this.states = states;
    }
    public long getProcessStartTime() {
	return processStartTime;
    }
    public void setProcessStartTime(long processStartTime) {
	this.processStartTime = processStartTime;
    }
    public long getProcessEndTime() {
	return processEndTime;
    }
    public void setProcessEndTime(long processEndTime) {
	this.processEndTime = processEndTime;
    }
    public long getProcessRunningTime() {
	return processRunningTime;
    }
    public void setProcessRunningTime(long processRunningTime) {
	this.processRunningTime = processRunningTime;
    }
    public List<String> getUploadFileUrlList() {
	return uploadFileUrlList;
    }
    public void setUploadFileUrlList(List<String> uploadFileUrlList) {
	this.uploadFileUrlList = uploadFileUrlList;
    }
    public boolean getCancel() {
	return cancel;
    }
    public void setCancel(boolean cancel) {
	this.cancel = cancel;
    }
    public String getBaseDir() {
	return baseDir;
    }
    public void setBaseDir(String baseDir) {
	this.baseDir = baseDir;
    }
}
