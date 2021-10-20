package com.welog.www.classObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachImage {
	
	private String pathFilename;
	
	private String original;

	public AttachImage(String pathFilename, String original) {
		this.pathFilename = pathFilename;
		this.original = original;
	}

}
