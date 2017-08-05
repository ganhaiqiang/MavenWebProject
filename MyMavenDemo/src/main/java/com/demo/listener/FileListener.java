package com.demo.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

/**
 * 文件、文件夹监听
 * 
 * @author 80001267
 *
 */
public class FileListener implements FileAlterationListener {

	@Override
	public void onStart(FileAlterationObserver observer) {
		// TODO Auto-generated method stub
		// System.out.println("onStart()");

	}

	@Override
	public void onDirectoryCreate(File directory) {
		// TODO Auto-generated method stub
		System.out.println("onDirectoryCreate()，directory=" + directory.getName());
	}

	@Override
	public void onDirectoryChange(File directory) {
		// TODO Auto-generated method stub
		System.out.println("onDirectoryChange()，directory=" + directory.getName());
	}

	@Override
	public void onDirectoryDelete(File directory) {
		// TODO Auto-generated method stub
		System.out.println("onDirectoryDelete()，directory=" + directory.getName());
	}

	@Override
	public void onFileCreate(File file) {
		// TODO Auto-generated method stub
		System.out.println("onFileCreate()，file=" + file.getPath());
	}

	@Override
	public void onFileChange(File file) {
		// TODO Auto-generated method stub
		System.out.println("onFileChange()，file=" + file.getPath());
	}

	@Override
	public void onFileDelete(File file) {
		// TODO Auto-generated method stub
		System.out.println("onFileDelete()，file=" + file.getPath());
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		// TODO Auto-generated method stub
		// System.out.println("onStop()");
	}
}
